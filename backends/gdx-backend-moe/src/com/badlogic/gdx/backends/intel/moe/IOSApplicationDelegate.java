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

import ios.NSObject;
import ios.uikit.protocol.UIApplicationDelegate;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.objc.ann.Selector;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.objc.ann.NotImplemented;
import ios.uikit.UILocalNotification;
import ios.foundation.NSArray;
import ios.foundation.NSData;
import com.intel.inde.moe.natj.objc.ann.IsOptional;
import ios.uikit.UIApplication;
import ios.coregraphics.struct.CGRect;
import com.intel.inde.moe.natj.general.ann.ByValue;
import ios.foundation.NSDictionary;
import ios.uikit.UIWindow;
import ios.foundation.NSError;
import ios.uikit.UIViewController;
import ios.foundation.NSCoder;
import ios.foundation.NSURL;

public abstract class IOSApplicationDelegate extends NSObject implements UIApplicationDelegate {

	private IOSApplication app;
	
	protected abstract IOSApplication createApplication();
	
	@Override
	@Selector("application:didFinishLaunchingWithOptions:")
	public boolean applicationDidFinishLaunchingWithOptions(
			UIApplication application, NSDictionary launchOptions) {
		this.app = createApplication();
		return app.didFinishLaunching(application, launchOptions);
	}

	@Override
	@IsOptional
	@Selector("applicationDidBecomeActive:")
	public void applicationDidBecomeActive(UIApplication application) {
		app.didBecomeActive(application);
	}

	@Override
	@IsOptional
	@Selector("applicationWillResignActive:")
	public void applicationWillResignActive(UIApplication application) {
		app.willResignActive(application);
	}

	@Override
	@IsOptional
	@Selector("applicationWillTerminate:")
	public void applicationWillTerminate(UIApplication application) {
		app.willTerminate(application);
	}
	
	static {
		NatJ.register();
	}

	@NotImplemented
	@Override
	@IsOptional
	@Selector("application:didChangeStatusBarFrame:")
	public native void applicationDidChangeStatusBarFrame(
			UIApplication application, @ByValue CGRect oldStatusBarFrame);

	@NotImplemented
	@IsOptional
	@Selector("application:didChangeStatusBarOrientation:")
	public native void applicationDidChangeStatusBarOrientation(
			UIApplication application, int oldStatusBarOrientation);

	@NotImplemented
	@Override
	@IsOptional
	@Selector("application:didDecodeRestorableStateWithCoder:")
	public native void applicationDidDecodeRestorableStateWithCoder(
			UIApplication application, NSCoder coder);

	@NotImplemented
	@Override
	@IsOptional
	@Selector("application:didFailToRegisterForRemoteNotificationsWithError:")
	public native void applicationDidFailToRegisterForRemoteNotificationsWithError(
			UIApplication application, NSError error);

	@NotImplemented
	@Override
	@IsOptional
	@Selector("application:didReceiveLocalNotification:")
	public native void applicationDidReceiveLocalNotification(
			UIApplication application, UILocalNotification notification);

	@NotImplemented
	@Override
	@IsOptional
	@Selector("application:didReceiveRemoteNotification:")
	public native void applicationDidReceiveRemoteNotification(
			UIApplication application, NSDictionary userInfo);

	@NotImplemented
	@Override
	@IsOptional
	@Selector("application:didRegisterForRemoteNotificationsWithDeviceToken:")
	public native void applicationDidRegisterForRemoteNotificationsWithDeviceToken(
			UIApplication application, NSData deviceToken);

	@NotImplemented
	@Override
	@IsOptional
	@Selector("application:handleOpenURL:")
	public native boolean applicationHandleOpenURL(UIApplication application,
			NSURL url);

	@NotImplemented
	@Override
	@IsOptional
	@Selector("application:openURL:sourceApplication:annotation:")
	public native boolean applicationOpenURLSourceApplicationAnnotation(
			UIApplication application, NSURL url, String sourceApplication,
			Object annotation);

	@NotImplemented
	@Override
	@IsOptional
	@Selector("application:shouldRestoreApplicationState:")
	public native boolean applicationShouldRestoreApplicationState(
			UIApplication application, NSCoder coder);

	@NotImplemented
	@Override
	@IsOptional
	@Selector("application:shouldSaveApplicationState:")
	public native boolean applicationShouldSaveApplicationState(
			UIApplication application, NSCoder coder);

	@NotImplemented
	@Override
	@IsOptional
	@Selector("application:supportedInterfaceOrientationsForWindow:")
	public native long applicationSupportedInterfaceOrientationsForWindow(
			UIApplication application, UIWindow window);

	@NotImplemented
	@Override
	@IsOptional
	@Selector("application:viewControllerWithRestorationIdentifierPath:coder:")
	public native UIViewController applicationViewControllerWithRestorationIdentifierPathCoder(
			UIApplication application, NSArray identifierComponents,
			NSCoder coder);

	@NotImplemented
	@Override
	@IsOptional
	@Selector("application:willChangeStatusBarFrame:")
	public native void applicationWillChangeStatusBarFrame(
			UIApplication application, @ByValue CGRect newStatusBarFrame);

	@NotImplemented
	@IsOptional
	@Selector("application:willChangeStatusBarOrientation:duration:")
	public native void applicationWillChangeStatusBarOrientationDuration(
			UIApplication application, int newStatusBarOrientation,
			double duration);

	@NotImplemented
	@Override
	@IsOptional
	@Selector("application:willEncodeRestorableStateWithCoder:")
	public native void applicationWillEncodeRestorableStateWithCoder(
			UIApplication application, NSCoder coder);

	@NotImplemented
	@Override
	@IsOptional
	@Selector("application:willFinishLaunchingWithOptions:")
	public native boolean applicationWillFinishLaunchingWithOptions(
			UIApplication application, NSDictionary launchOptions);

	@NotImplemented
	@Override
	@IsOptional
	@Selector("applicationDidEnterBackground:")
	public native void applicationDidEnterBackground(UIApplication application);

	@NotImplemented
	@Override
	@IsOptional
	@Selector("applicationDidFinishLaunching:")
	public native void applicationDidFinishLaunching(UIApplication application);

	@NotImplemented
	@Override
	@IsOptional
	@Selector("applicationDidReceiveMemoryWarning:")
	public native void applicationDidReceiveMemoryWarning(
			UIApplication application);

	@NotImplemented
	@Override
	@IsOptional
	@Selector("applicationProtectedDataDidBecomeAvailable:")
	public native void applicationProtectedDataDidBecomeAvailable(
			UIApplication application);

	@NotImplemented
	@Override
	@IsOptional
	@Selector("applicationProtectedDataWillBecomeUnavailable:")
	public native void applicationProtectedDataWillBecomeUnavailable(
			UIApplication application);

	@NotImplemented
	@Override
	@IsOptional
	@Selector("applicationSignificantTimeChange:")
	public native void applicationSignificantTimeChange(
			UIApplication application);

	@NotImplemented
	@Override
	@IsOptional
	@Selector("applicationWillEnterForeground:")
	public native void applicationWillEnterForeground(UIApplication application);

	@NotImplemented
	@Override
	@IsOptional
	@Selector("setWindow:")
	public native void setWindow(UIWindow value);

	@NotImplemented
	@Override
	@IsOptional
	@Selector("window")
	public native UIWindow window();

	@Generated("NatJ")
	@Selector("alloc")
	public static native IOSApplicationDelegate alloc();

	@Generated("NatJ")
	protected IOSApplicationDelegate(Pointer peer) {
		super(peer);
	}

}
