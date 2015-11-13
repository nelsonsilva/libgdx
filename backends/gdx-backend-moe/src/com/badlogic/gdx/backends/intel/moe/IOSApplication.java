/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.badlogic.gdx.backends.intel.moe;

import java.io.File;

import ios.coregraphics.struct.CGSize;
import ios.foundation.NSDictionary;
import ios.foundation.NSMutableDictionary;
import ios.uikit.UIApplication;
import ios.uikit.UIDevice;
import ios.uikit.enums.UIInterfaceOrientation;
import ios.uikit.UIScreen;
import ios.uikit.enums.UIUserInterfaceIdiom;
import ios.uikit.UIViewController;
import ios.uikit.UIWindow;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.LifecycleListener;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.iosrobovm.IOSGLES20;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Clipboard;

public class IOSApplication implements Application {

	UIApplication uiApp;
	UIWindow uiWindow;
	ApplicationListener listener;
	IOSApplicationConfiguration config;
	IOSGraphics graphics;
	IOSAudio audio;
	IOSFiles files;
	IOSInput input;
	IOSNet net;
	int logLevel = Application.LOG_DEBUG;

	/** The display scale factor (1.0f for normal; 2.0f to use retina coordinates/dimensions). */
	float displayScaleFactor;

	Array<Runnable> runnables = new Array<Runnable>();
	Array<Runnable> executedRunnables = new Array<Runnable>();
	Array<LifecycleListener> lifecycleListeners = new Array<LifecycleListener>();

	public IOSApplication (ApplicationListener listener, IOSApplicationConfiguration config) {
		this.listener = listener;
		this.config = config;
	}
	
	public final boolean didFinishLaunching (UIApplication uiApp, NSDictionary options) {
		Gdx.app = this;
		this.uiApp = uiApp;

		// enable or disable screen dimming
		UIApplication.sharedApplication().setIdleTimerDisabled(config.preventScreenDimming);

		Gdx.app.debug("IOSApplication", "iOS version: " + UIDevice.currentDevice().systemVersion());
		// fix the scale factor if we have a retina device (NOTE: iOS screen sizes are in "points" not pixels by default!)

		float scale = (float)(getVersion() >= 8 ? UIScreen.mainScreen().nativeScale() : UIScreen.mainScreen()
			.scale());
		if (scale >= 2.0f) {
			Gdx.app.debug("IOSApplication", "scale: " + scale);
			if (UIDevice.currentDevice().userInterfaceIdiom() == UIUserInterfaceIdiom.Pad) {
				// it's an iPad!
				displayScaleFactor = config.displayScaleLargeScreenIfRetina * scale;
			} else {
				// it's an iPod or iPhone
				displayScaleFactor = config.displayScaleSmallScreenIfRetina * scale;
			}
		} else {
			// no retina screen: no scaling!
			if (UIDevice.currentDevice().userInterfaceIdiom() == UIUserInterfaceIdiom.Pad) {
				// it's an iPad!
				displayScaleFactor = config.displayScaleLargeScreenIfNonRetina;
			} else {
				// it's an iPod or iPhone
				displayScaleFactor = config.displayScaleSmallScreenIfNonRetina;
			}
		}
		GL20 gl20 = new IOSGLES20();
		
		Gdx.gl = gl20;
		Gdx.gl20 = gl20;
		
		// setup libgdx
		this.input = IOSInput.alloc().init();
		this.input.iOSInput(this);
		
		this.graphics = IOSGraphics.alloc().init();
		this.graphics.iOSGraphics(getBounds(null), scale, this, config, input, gl20);
		
		this.files = new IOSFiles();
		this.audio = new IOSAudio(config);
		this.net = new IOSNet(this);

		Gdx.files = this.files;
		Gdx.graphics = this.graphics;
		Gdx.audio = this.audio;
		Gdx.input = this.input;
		Gdx.net = this.net;

		this.input.setupPeripherals();

		this.uiWindow = UIWindow.alloc().initWithFrame(
				UIScreen.mainScreen().bounds());
		this.uiWindow.setRootViewController(this.graphics.viewController);
		this.uiWindow.makeKeyAndVisible();
		Gdx.app.debug("IOSApplication", "created");
		return true;
	}
	
	/**
	 * Return the UI view controller of IOSApplication
	 * @return the view controller of IOSApplication
	 */
	public UIViewController getUIViewController(){
		return graphics.viewController;
	}

	/** Returns our real display dimension based on screen orientation.
	 * 
	 * @param viewController The view controller.
	 * @return Or real display dimension. */
	CGSize getBounds (UIViewController viewController) {
		// or screen size (always portrait)
		CGSize bounds = UIScreen.mainScreen().bounds().size();

		// determine orientation and resulting width + height
		int orientation;
		if (viewController != null) {
			orientation = (int) viewController.interfaceOrientation();
		} else if (config.orientationLandscape == config.orientationPortrait) {
			/*
			 * if the app has orientation in any side then we can only check status bar orientation
			 */
			orientation = (int) uiApp.statusBarOrientation();
		} else if (config.orientationLandscape) {// is landscape true and portrait false
			orientation = (int) UIInterfaceOrientation.LandscapeRight;
		} else {// is portrait true and landscape false
			orientation = (int) UIInterfaceOrientation.Portrait;
		}
		
		int width;
		int height;
		switch (orientation) {
		case (int) UIInterfaceOrientation.LandscapeLeft:
		case (int) UIInterfaceOrientation.LandscapeRight:
			height = (int)bounds.width();
			width = (int)bounds.height();
			if (width < height) {
				width = (int)bounds.width();
				height = (int)bounds.height();
			}
			break;
		default:
			// assume portrait
			width = (int)bounds.width();
			height = (int)bounds.height();
		}

		// update width/height depending on display scaling selected 
		width *= displayScaleFactor;
		height *= displayScaleFactor;

		// log screen dimensions
		Gdx.app.debug("IOSApplication", "View: " + orientation + " " + width + "x" + height);

		// return resulting view size (based on orientation)
		return new CGSize(width, height);
	}

	public final void didBecomeActive (UIApplication uiApp) {
		Gdx.app.debug("IOSApplication", "resumed");
		graphics.makeCurrent();
		graphics.resume();
	}

	public final void willResignActive (UIApplication uiApp) {
		Gdx.app.debug("IOSApplication", "paused");
		graphics.makeCurrent();
		graphics.pause();
		Gdx.gl.glFlush();
	}

	public final void willTerminate (UIApplication uiApp) {
		Gdx.app.debug("IOSApplication", "disposed");
		graphics.makeCurrent();
		Array<LifecycleListener> listeners = lifecycleListeners;
		synchronized(listeners) {
			for(LifecycleListener listener: listeners) {
				listener.pause();
			}
		}
		listener.dispose();
		Gdx.gl.glFlush();
	}

	@Override
	public ApplicationListener getApplicationListener () {
		return listener;
	}
	
	@Override
	public Graphics getGraphics () {
		return graphics;
	}

	@Override
	public Audio getAudio () {
		return audio;
	}

	@Override
	public Input getInput () {
		return input;
	}

	@Override
	public Files getFiles () {
		return files;
	}

	@Override
	public Net getNet () {
		return net;
	}

	@Override
	public void log (String tag, String message) {
		if (logLevel > LOG_NONE) {
			System.out.println("[info] " + tag + ": " + message);
		}
	}

	@Override
	public void log (String tag, String message, Throwable exception) {
		if (logLevel > LOG_NONE) {
			System.out.println("[info] " + tag + ": " + message);
			exception.printStackTrace();
		}
	}

	@Override
	public void error (String tag, String message) {
		if (logLevel >= LOG_ERROR) {
			System.out.println("[error] " + tag + ": " + message);
		}
	}

	@Override
	public void error (String tag, String message, Throwable exception) {
		if (logLevel >= LOG_ERROR) {
			System.out.println("[error] " + tag + ": " + message);
			exception.printStackTrace();
		}
	}

	@Override
	public void debug (String tag, String message) {
		if (logLevel >= LOG_DEBUG) {
			System.out.println("[debug] " + tag + ": " + message);
		}
	}

	@Override
	public void debug (String tag, String message, Throwable exception) {
		if (logLevel >= LOG_DEBUG) {
			System.out.println("[error] " + tag + ": " + message);
			exception.printStackTrace();
		}
	}

	@Override
	public void setLogLevel (int logLevel) {
		this.logLevel = logLevel;
	}

	@Override
	public int getLogLevel() {
		return logLevel;
	}

	@Override
	public ApplicationType getType () {
		return ApplicationType.iOS;
	}

	@Override
	public int getVersion () {
		return Integer.parseInt(UIDevice.currentDevice().systemVersion().split("\\.")[0]);
	}

	@Override
	public long getJavaHeap () {
		return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	}

	@Override
	public long getNativeHeap () {
		return getJavaHeap();
	}

	@Override
	public Preferences getPreferences (String name) {
		File libraryPath = new File(System.getenv("HOME"), "Library");
		String finalPath = new File(libraryPath, name + ".plist").getAbsolutePath();
		
		Gdx.app.debug("IOSApplication", "Loading NSDictionary from file " + finalPath);
		NSMutableDictionary nsDictionary = NSMutableDictionary.alloc().initWithContentsOfFile(finalPath);

		// if it fails to get an existing dictionary, create a new one.
		if (nsDictionary == null) {
			Gdx.app.debug("IOSApplication", "NSDictionary not found, creating a new one");
			nsDictionary = NSMutableDictionary.alloc().init();
			boolean fileWritten = nsDictionary.writeToFileAtomically(finalPath, false);
			if (fileWritten)
				Gdx.app.debug("IOSApplication", "NSDictionary file written");
			else 
				Gdx.app.debug("IOSApplication", "Failed to write NSDictionary to file " + finalPath);
		}
		return new IOSPreferences(nsDictionary, finalPath);
	}

	@Override
	public void postRunnable (Runnable runnable) {
		synchronized (runnables) {
			runnables.add(runnable);
		}
	}

	public void processRunnables () {
		synchronized (runnables) {
			executedRunnables.clear();
			executedRunnables.addAll(runnables);
			runnables.clear();
		}
		for (int i = 0; i < executedRunnables.size; i++) {
			try {
				executedRunnables.get(i).run();
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
	}

	@Override
	public void exit () {

	}
	
	@Override
	public Clipboard getClipboard () {
		return new IOSClipboard();
	}
	
	@Override
	public void addLifecycleListener (LifecycleListener listener) {
		synchronized(lifecycleListeners) {
			lifecycleListeners.add(listener);
		}
	}

	@Override
	public void removeLifecycleListener (LifecycleListener listener) {
		synchronized(lifecycleListeners) {
			lifecycleListeners.removeValue(listener, true);
		}		
	}
	
}