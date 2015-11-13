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

import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import ios.NSObject;
import ios.coregraphics.struct.CGPoint;
import ios.coregraphics.struct.CGRect;
import ios.coregraphics.struct.CGSize;
import ios.glkit.GLKView;
import ios.glkit.GLKViewController;
import ios.glkit.protocol.GLKViewControllerDelegate;
import ios.glkit.protocol.GLKViewDelegate;
import ios.glkit.enums.GLKViewDrawableColorFormat;
import ios.glkit.enums.GLKViewDrawableDepthFormat;
import ios.glkit.enums.GLKViewDrawableMultisample;
import ios.glkit.enums.GLKViewDrawableStencilFormat;
import ios.opengles.EAGLContext;
//import ios.opengles.enums.Enums;
import ios.uikit.UIDevice;
import ios.uikit.UIScreen;
import ios.uikit.enums.UIUserInterfaceIdiom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.LifecycleListener;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.utils.Array;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.objc.ann.IsOptional;
import com.intel.inde.moe.natj.objc.ann.Selector;
import com.intel.inde.moe.natj.general.ann.ByValue;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.NatJ;

public class IOSGraphics extends NSObject implements Graphics, GLKViewDelegate, GLKViewControllerDelegate {

	private static final String tag = "IOSGraphics";

	IOSApplication app;
	IOSInput input;
	GL20 gl20;
	int width;
	int height;
	long lastFrameTime;
	float deltaTime;
	long framesStart;
	int frames;
	int fps;
	BufferFormat bufferFormat;
	String extensions;

	private float ppiX = 0;
	private float ppiY = 0;
	private float ppcX = 0;
	private float ppcY = 0;
	private float density = 1;

	volatile boolean paused;
	private long frameId = -1;
	boolean wasPaused;

	IOSApplicationConfiguration config;
	EAGLContext context;
	IOSGLKView view;
	IOSUIViewController viewController;

	static {
		NatJ.register();
	}

	@Generated("NatJ")
	@Selector("alloc")
	public static native IOSGraphics alloc();

	@Generated("NatJ")
	protected IOSGraphics(Pointer peer) {
		super(peer);
	}
	
	@Selector("init")
	public native IOSGraphics init();
	
	public void iOSGraphics(CGSize bounds, float scale, IOSApplication app, IOSApplicationConfiguration config, IOSInput input, GL20 gl20) {
		this.config = config;
		// setup view and OpenGL
		width = (int) bounds.width();
		height = (int) bounds.height();
		app.debug(tag, bounds.width() + "x" + bounds.height() + ", " + UIScreen.mainScreen().scale());
		this.gl20 = gl20;

		//context = EAGLContext.alloc().initWithAPI(Enums.kEAGLRenderingAPIOpenGLES2);
		context = EAGLContext.alloc().initWithAPI(2);

		view = IOSGLKView.alloc();
		CGRect rect = new CGRect(new CGPoint(0, 0), bounds);
		view.initWithFrameContext(rect, context);
		view.initIOSGLKView(this);
		
		view.setDelegate(this);
		view.setDrawableColorFormat(config.colorFormat);
		view.setDrawableDepthFormat(config.depthFormat);
		view.setDrawableStencilFormat(config.stencilFormat);
		view.setDrawableMultisample(config.multisample);
		view.setMultipleTouchEnabled(true);
		
		viewController = IOSUIViewController.alloc().init();
		viewController.iOSUIViewController(app, this);
		
		viewController.setView(view);
		viewController.setDelegate(this);
		viewController.setPreferredFramesPerSecond(config.preferredFramesPerSecond);

		this.app = app;
		this.input = input;

		int r = 0, g = 0, b = 0, a = 0, depth = 0, stencil = 0, samples = 0;
		if(config.colorFormat == GLKViewDrawableColorFormat.RGB565) {
			r = 5; g = 6; b = 5; a = 0;
		} else {
			r = g = b = a = 8;
		}
		if(config.depthFormat == GLKViewDrawableDepthFormat.Format16) {
			depth = 16;
		} else if (config.depthFormat == GLKViewDrawableDepthFormat.Format24) {
			depth = 24;
		} else {
			depth = 0;
		}
		if(config.stencilFormat == GLKViewDrawableStencilFormat.Format8) {
			stencil = 8;
		}
		if(config.multisample == GLKViewDrawableMultisample.Multisample4X) {
			samples = 4;
		}
		bufferFormat = new BufferFormat(r, g, b, a, depth, stencil, samples, false);
		this.gl20 = gl20;

		// determine display density and PPI (PPI values via Wikipedia!)
		density = 1f;

		//float scale = UIScreen.mainScreen().scale();
		app.debug(tag, "Calculating density, UIScreen.mainScreen.scale: " + scale);
		if (scale == 2) density = 2f;
		if (scale == 3) density = 3f;

		int ppi;
		if (UIDevice.currentDevice().userInterfaceIdiom() == UIUserInterfaceIdiom.Pad) {
			// iPad
			ppi = Math.round(density * 132);
		} else {
			// iPhone or iPodTouch
			ppi = Math.round(density * 163);
		}
		ppiX = ppi;
		ppiY = ppi;
		ppcX = ppiX / 2.54f;
		ppcY = ppiY / 2.54f;
		app.debug(tag, "Display: ppi=" + ppi + ", density=" + density);

		// time + FPS
		lastFrameTime = System.nanoTime();
		framesStart = lastFrameTime;

		paused = false;
		wasPaused = true;
	}

	public void resume() {
		paused = false;
	}

	public void pause() {
		paused = true;
	}

	boolean created = false;
	
	@Override
	@Selector("glkView:drawInRect:")
	public void glkViewDrawInRect(GLKView view, @ByValue CGRect rect) {
		if(!created) {
			app.graphics.makeCurrent();
			app.listener.create();
			app.listener.resize(width, height);
			created = true;
		}
		if (paused) {
			if (!wasPaused) {
				Array<LifecycleListener> listeners = app.lifecycleListeners;
				synchronized (listeners) {
					for (LifecycleListener listener : listeners) {
						listener.pause();
					}
				}
				app.listener.pause();
				wasPaused = true;
			}
			return;
		} else {
			if (wasPaused) {
				Array<LifecycleListener> listeners = app.lifecycleListeners;
				synchronized (listeners) {
					for (LifecycleListener listener : listeners) {
						listener.resume();
					}
				}
				app.listener.resume();
				wasPaused = false;
			}
		}

		long time = System.nanoTime();
		deltaTime = (time - lastFrameTime) / 1000000000.0f;
		lastFrameTime = time;

		frames++;
		if (time - framesStart >= 1000000000l) {
			framesStart = time;
			fps = frames;
			frames = 0;
		}

		makeCurrent();
		input.processEvents();
		frameId ++;
		app.listener.render();
	}

	void makeCurrent() {
		EAGLContext.setCurrentContext(context);
	}

	@Selector("glkViewController:willPause:")
	@IsOptional
	public void glkViewControllerWillPause(GLKViewController controller,
			boolean pause) {
		if (pause) {
			pause();
		} else {
			resume();
		}
	}

	@Selector("glkViewControllerUpdate:")
	public void glkViewControllerUpdate(GLKViewController controller) {
		makeCurrent();
		app.processRunnables();
	}

	@Override
	public GL20 getGL20() {
		return gl20;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public float getDeltaTime() {
		return deltaTime;
	}

	@Override
	public float getRawDeltaTime() {
		return deltaTime;
	}

	@Override
	public int getFramesPerSecond() {
		return fps;
	}

	@Override
	public GraphicsType getType() {
		return GraphicsType.iOSGL;
	}

	@Override
	public float getPpiX() {
		return ppiX;
	}

	@Override
	public float getPpiY() {
		return ppiY;
	}

	@Override
	public float getPpcX() {
		return ppcX;
	}

	@Override
	public float getPpcY() {
		return ppcY;
	}

	/**
	 * Returns the display density.
	 * 
	 * @return 1.0f for non-retina devices, 2.0f for retina devices.
	 */
	@Override
	public float getDensity() {
		return density;
	}

	@Override
	public boolean supportsDisplayModeChange() {
		return false;
	}

	@Override
	public DisplayMode[] getDisplayModes() {
		return new DisplayMode[] { getDesktopDisplayMode() };
	}

	@Override
	public DisplayMode getDesktopDisplayMode() {
		return new IOSDisplayMode(getWidth(), getHeight(), config.preferredFramesPerSecond, bufferFormat.r + bufferFormat.g + bufferFormat.b + bufferFormat.a);
	}

	private class IOSDisplayMode extends DisplayMode {
		protected IOSDisplayMode(int width, int height, int refreshRate,
				int bitsPerPixel) {
			super(width, height, refreshRate, bitsPerPixel);
		}
	}

	@Override
	public boolean setDisplayMode(DisplayMode displayMode) {
		return false;
	}

	@Override
	public boolean setDisplayMode(int width, int height, boolean fullscreen) {
		return false;
	}

	@Override
	public void setTitle(String title) {
	}

	@Override
	public void setVSync(boolean vsync) {
	}

	@Override
	public BufferFormat getBufferFormat() {
		return bufferFormat;
	}

	@Override
	public boolean supportsExtension(String extension) {
		if (extensions == null) {
			extensions = Gdx.gl.glGetString(GL20.GL_EXTENSIONS);
		}
		return extensions.contains(extension);
	}

	@Override
	public boolean isFullscreen() {
		return true;
	}

	@Override
	public Cursor newCursor(Pixmap pixmap, int xHotspot, int yHotspot) {
		return null;
	}

	@Override
	public void setCursor(Cursor cursor) {

	}

	@Override
	public boolean isGL30Available () {
		return false;
	}

	@Override
	public GL30 getGL30 () {
		return null;
	}

	@Override
	public void setContinuousRendering(boolean isContinuous) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isContinuousRendering() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void requestRendering() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getFrameId() {
		return frameId;
	}
	
}

