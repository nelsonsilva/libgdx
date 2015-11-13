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

import ios.coregraphics.struct.CGSize;
import ios.glkit.GLKViewController;
import ios.uikit.enums.UIInterfaceOrientation;

import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.objc.ann.Selector;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.NatJ;

public class IOSUIViewController extends GLKViewController {
	
	IOSApplication app;
	IOSGraphics graphics;
	boolean created = false;

	static {
		NatJ.register();
	}
	
	@Selector("init")
	public native IOSUIViewController init();
	
	void iOSUIViewController(IOSApplication app, IOSGraphics graphics) {
		this.app = app;
		this.graphics = graphics;
	}

	@Selector("didRotateFromInterfaceOrientation:")
	public void didRotateFromInterfaceOrientation(
			int fromInterfaceOrientation) {
		CGSize bounds = app.getBounds(this);
		graphics.width = (int) bounds.width();
		graphics.height = (int) bounds.height();
		graphics.makeCurrent();
		app.listener.resize(graphics.width, graphics.height);
	}

	@Selector("shouldAutorotateToInterfaceOrientation:")
	public boolean shouldAutorotateToInterfaceOrientation(
			int orientation) {
		switch (orientation) {
		case (int) UIInterfaceOrientation.LandscapeLeft:
		case (int) UIInterfaceOrientation.LandscapeRight:
			return app.config.orientationLandscape;
		default:
			return app.config.orientationPortrait;
		}
	}

	@Generated("NatJ")
	@Selector("alloc")
	public static native IOSUIViewController alloc();

	@Generated("NatJ")
	protected IOSUIViewController(Pointer peer) {
		super(peer);
	}

}
