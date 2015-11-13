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

import ios.coregraphics.struct.CGRect;
import ios.foundation.NSSet;
import ios.glkit.GLKView;
import ios.opengles.EAGLContext;
import ios.uikit.UIEvent;

import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.objc.ann.Selector;
import com.intel.inde.moe.natj.general.ann.ByValue;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.NatJ;

public class IOSGLKView extends GLKView {

	private IOSGraphics graphics = null;

	static {
		NatJ.register();
	}
	
	@Generated
	@Selector("init")
	public native IOSGLKView init();
	
	@Override
	@Selector("initWithFrame:context:")
	public IOSGLKView initWithFrameContext(CGRect frame,
			EAGLContext context) {
		super.initWithFrameContext(frame, context);
		return this;
	}

	void initIOSGLKView(IOSGraphics graphics) {
		this.graphics = graphics;
	}
	
	@Override
	@Selector("touchesBegan:withEvent:")
	public void touchesBeganWithEvent(NSSet touches, UIEvent event) {
		super.touchesBeganWithEvent(touches, event);
		graphics.input.touchDown(touches, event);
	}

	@Override
	@Selector("touchesCancelled:withEvent:")
	public void touchesCancelledWithEvent(NSSet touches, UIEvent event) {
		super.touchesCancelledWithEvent(touches, event);
		graphics.input.touchUp(touches, event);
	}

	@Override
	@Selector("touchesEnded:withEvent:")
	public void touchesEndedWithEvent(NSSet touches, UIEvent event) {
		super.touchesEndedWithEvent(touches, event);
		graphics.input.touchUp(touches, event);
	}

	@Override
	@Selector("touchesMoved:withEvent:")
	public void touchesMovedWithEvent(NSSet touches, UIEvent event) {
		super.touchesMovedWithEvent(touches, event);
		graphics.input.touchMoved(touches, event);
	}

	@Override
	@Selector("drawRect:")
	public void drawRect(@ByValue CGRect rect) {
		graphics.glkViewDrawInRect(this, rect);
	}

	@Generated("NatJ")
	@Selector("alloc")
	public static native IOSGLKView alloc();

	@Generated("NatJ")
	protected IOSGLKView(Pointer peer) {
		super(peer);
	}

}
