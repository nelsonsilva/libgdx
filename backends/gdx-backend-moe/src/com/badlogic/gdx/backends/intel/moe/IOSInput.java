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

import com.badlogic.gdx.graphics.Pixmap;

import ios.NSObject;
import ios.coregraphics.struct.CGPoint;
import ios.foundation.NSArray;
import ios.foundation.NSSet;
import ios.uikit.UIAcceleration;
import ios.uikit.UIAccelerometer;
import ios.uikit.protocol.UIAccelerometerDelegate;
import ios.uikit.UIAlertView;
import ios.uikit.protocol.UIAlertViewDelegate;
import ios.uikit.enums.UIAlertViewStyle;
import ios.uikit.UIApplication;
import ios.uikit.UIEvent;
import ios.uikit.enums.UIInterfaceOrientation;
import ios.uikit.UITextField;
import ios.uikit.UITouch;
import ios.uikit.enums.UITouchPhase;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Pool;
import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.objc.ann.IsOptional;
import com.intel.inde.moe.natj.objc.ann.NotImplemented;
import com.intel.inde.moe.natj.objc.ann.Selector;

public class IOSInput extends NSObject implements Input, UIAccelerometerDelegate, UIAlertViewDelegate {
	
	static final int MAX_TOUCHES = 20;
	
	IOSApplication app;
	IOSApplicationConfiguration config;
	int[] deltaX = new int[MAX_TOUCHES];
	int[] deltaY = new int[MAX_TOUCHES];
	int[] touchX = new int[MAX_TOUCHES];
	int[] touchY = new int[MAX_TOUCHES];
	// we store the pointer to the UITouch struct here, or 0
	long[] touchDown = new long[MAX_TOUCHES];
	int numTouched = 0;
	boolean justTouched = false;
	Pool<TouchEvent> touchEventPool = new Pool<TouchEvent>() {
		@Override
		protected TouchEvent newObject() {
			return new TouchEvent();
		}
	};
	Array<TouchEvent> touchEvents = new Array<TouchEvent>();
	TouchEvent currentEvent = null;
	float[] acceleration = new float[3];
	InputProcessor inputProcessor = null;

	void iOSInput(IOSApplication app) {
		this.app = app;
		this.config = app.config;
	}
	
	void setupPeripherals() {
		setupAccelerometer();
		setupCompass();
	}

	private void setupCompass () {
		if(config.useCompass) {
			// FIXME implement compass
		}
	}

	private void setupAccelerometer() {
		if(config.useAccelerometer) {
			UIAccelerometer.sharedAccelerometer().setDelegate(this);
			UIAccelerometer.sharedAccelerometer().setUpdateInterval(config.accelerometerUpdate);
		}
	}

	@Override
	public float getAccelerometerX() {
		return acceleration[0];
	}

	@Override
	public float getAccelerometerY() {
		return acceleration[1];
	}

	@Override
	public float getAccelerometerZ() {
		return acceleration[2];
	}

	@Override
	public int getX() {
		return touchX[0];
	}

	@Override
	public int getX(int pointer) {
		return touchX[pointer];
	}

	@Override
	public int getDeltaX() {
		return deltaX[0];
	}

	@Override
	public int getDeltaX(int pointer) {
		return deltaX[pointer];
	}

	@Override
	public int getY() {
		return touchY[0];
	}

	@Override
	public int getY(int pointer) {
		return touchY[pointer];
	}

	@Override
	public int getDeltaY() {
		return deltaY[0];
	}

	@Override
	public int getDeltaY(int pointer) {
		return deltaY[pointer];
	}

	@Override
	public boolean isTouched() {
		for (int pointer = 0; pointer < MAX_TOUCHES; pointer++) {
			if (touchDown[pointer] != 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean justTouched() {
		return justTouched;
	}

	@Override
	public boolean isTouched(int pointer) {
		return touchDown[pointer] != 0;
	}

	@Override
	public boolean isButtonPressed(int button) {
		return button == Buttons.LEFT && numTouched > 0;
	}

	@Override
	public boolean isKeyPressed(int key) {
		return false;
	}

	@Override
	public void getTextInput(TextInputListener listener, String title, String text, String hint) {
		final UIAlertView uiAlertView = buildUIAlertView(listener, title, text, hint);
		uiAlertView.show();
	}

	TextInputListener alertViewListener = null;
	
	private UIAlertView buildUIAlertView (final TextInputListener listener, String title, String text, String placeholder) {
		// build the view
		alertViewListener = listener;
		final UIAlertView uiAlertView = UIAlertView.alloc().init();
		uiAlertView.setTitle(title);
		uiAlertView.addButtonWithTitle("Cancel");
		uiAlertView.addButtonWithTitle("Ok");
		uiAlertView.setAlertViewStyle(UIAlertViewStyle.PlainTextInput);
		uiAlertView.setDelegate(this);

		UITextField textField = uiAlertView.textFieldAtIndex(0);
		textField.setPlaceholder(placeholder);
		textField.setText(text);

		return uiAlertView;
	}

	public void getPlaceholderTextInput(TextInputListener listener, String title, String placeholder) {
		final UIAlertView uiAlertView = buildUIAlertView(listener, title, null, placeholder);
		uiAlertView.show();
	}

	@Override
	public void setOnscreenKeyboardVisible(boolean visible) {
	}

	@Override
	public long getCurrentEventTime() {
		return currentEvent.timestamp;
	}

	@Override
	public void setCatchBackKey(boolean catchBack) {
	}

	@Override
	public void setCatchMenuKey(boolean catchMenu) {
	}

	@Override
	public boolean isCatchMenuKey() {
		return false;
	}

	@Override
	public void setInputProcessor(InputProcessor processor) {
		this.inputProcessor = processor;
	}

	@Override
	public InputProcessor getInputProcessor() {
		return inputProcessor;
	}

	@Override
	public boolean isPeripheralAvailable(Peripheral peripheral) {
		if(peripheral == Peripheral.Accelerometer && config.useAccelerometer) return true;
		if(peripheral == Peripheral.MultitouchScreen) return true;
		return false;
	}

	@Override
	public int getRotation() {
		int orientation = (int) (app.graphics.viewController != null
                            ? app.graphics.viewController.interfaceOrientation()
                            : UIApplication.sharedApplication().statusBarOrientation());
		// we measure orientation counter clockwise, just like on Android
		if(orientation == UIInterfaceOrientation.Portrait) return 0;
		if(orientation == UIInterfaceOrientation.LandscapeLeft) return 270;
		if(orientation == UIInterfaceOrientation.PortraitUpsideDown) return 180;
		if(orientation == UIInterfaceOrientation.LandscapeRight) return 90;
		return 0;
	}

	@Override
	public Orientation getNativeOrientation() {
		return Orientation.Portrait;
	}

	@Override
	public void setCursorCatched(boolean catched) {
	}

	@Override
	public boolean isCursorCatched() {
		return false;
	}

	@Override
	public void setCursorPosition(int x, int y) {
	}

  public void setCursorImage(Pixmap pixmap, int xHotspot, int yHotspot) {
  }

  public void touchDown(NSSet touches, UIEvent event) {
		toTouchEvents(touches, event);
		Gdx.graphics.requestRendering();
	}

	public void touchUp(NSSet touches, UIEvent event) {
		toTouchEvents(touches, event);
		Gdx.graphics.requestRendering();
	}

	public void touchMoved(NSSet touches, UIEvent event) {
		toTouchEvents(touches, event);
		Gdx.graphics.requestRendering();
	}
	
	void processEvents() {
		synchronized(touchEvents) {
			justTouched = false;
			for(TouchEvent event: touchEvents) {
				currentEvent = event;
				switch(event.phase) {
				case (int) UITouchPhase.Began:
					if(inputProcessor != null) inputProcessor.touchDown(event.x, event.y, event.pointer, Buttons.LEFT);
					if(numTouched == 1)
						justTouched = true;
					break;
				case (int) UITouchPhase.Cancelled:
				case (int) UITouchPhase.Ended:
					if(inputProcessor != null) inputProcessor.touchUp(event.x, event.y, event.pointer, Buttons.LEFT);
					break;
				case (int) UITouchPhase.Moved:
				case (int) UITouchPhase.Stationary:
					if(inputProcessor != null) inputProcessor.touchDragged(event.x, event.y, event.pointer);
					break;
				}
			}
			touchEventPool.freeAll(touchEvents);
			touchEvents.clear();
		}
	}
	
	private void toTouchEvents(NSSet touches, UIEvent uiEvent) {
		NSArray list = touches.allObjects();
		for(Object o : list) {
			UITouch touch = (UITouch)o;
			CGPoint loc = touch.locationInView(touch.view());
			synchronized(touchEvents) {
				TouchEvent event = touchEventPool.obtain();
				event.x = (int)(loc.x() * app.displayScaleFactor);
				event.y = (int)(loc.y() * app.displayScaleFactor);
				event.phase = (int) touch.phase();
				event.timestamp = (long)(touch.timestamp() * 1000000000);
				touchEvents.add(event);
				
				if(touch.phase() == UITouchPhase.Began) {					
					event.pointer = getFreePointer();
					touchDown[event.pointer] = (int)touch.getPeerPointer();
					touchX[event.pointer] = event.x;
					touchY[event.pointer] = event.y;
					deltaX[event.pointer] = 0;
					deltaY[event.pointer] = 0; 
					numTouched++;
				}
				
				if(touch.phase() == UITouchPhase.Moved ||
					touch.phase() == UITouchPhase.Stationary) {
					event.pointer = getFreePointer();
					deltaX[event.pointer] = event.x - touchX[event.pointer];
					deltaY[event.pointer] = event.y - touchY[event.pointer]; 
					touchX[event.pointer] = event.x;
					touchY[event.pointer] = event.y;
				}
				
				if(touch.phase() == UITouchPhase.Cancelled ||
					touch.phase() == UITouchPhase.Ended) {					
					event.pointer = findPointer(touch);
					touchDown[event.pointer] = 0;
					touchX[event.pointer] = event.x;
					touchY[event.pointer] = event.y;
					deltaX[event.pointer] = 0;
					deltaY[event.pointer] = 0;
					numTouched--;
				}
			}
		}	
	}
	
	static class TouchEvent {
		int phase;
		
		long timestamp;
		int x, y;
		int pointer;
	}
	
	
	/** UIAccelerometerDelegate **/
	
	@Override
	@Selector("accelerometer:didAccelerate:")
	public void accelerometerDidAccelerate(
			UIAccelerometer accelerometer, UIAcceleration values) {
		
		float x = (float)values.x() * 10;
		float y = (float)values.y() * 10;
		float z = (float)values.z() * 10;
			
		acceleration[0] = -x;
		acceleration[1] = -y;
		acceleration[2] = -z;
		
	}
	
	/** UIAlertViewDelegate **/
	@Selector("alertView:clickedButtonAtIndex:")
	public void alertViewClickedButtonAtIndex(UIAlertView alertView,
			int buttonIndex) {
		
		if (buttonIndex == 0) {
			// user clicked "Cancel" button
			alertViewListener.canceled();
		} else if (buttonIndex == 1) {
			// user clicked "Ok" button
			UITextField textField = alertView.textFieldAtIndex(0);
			alertViewListener.input(textField.text());
		}

	}
	
	@Override
	@Selector("alertViewCancel:")
	public void alertViewCancel(UIAlertView alertView) {
		
		alertViewListener.canceled();

	}

	@NotImplemented
	@IsOptional
	@Selector("alertView:didDismissWithButtonIndex:")
	public native void alertViewDidDismissWithButtonIndex(
			UIAlertView alertView, int buttonIndex);

	@NotImplemented
	@IsOptional
	@Selector("alertView:willDismissWithButtonIndex:")
	public native void alertViewWillDismissWithButtonIndex(
			UIAlertView alertView, int buttonIndex);

	@NotImplemented
	@Override
	@IsOptional
	@Selector("alertViewShouldEnableFirstOtherButton:")
	public native boolean alertViewShouldEnableFirstOtherButton(
			UIAlertView alertView);

	@NotImplemented
	@Override
	@IsOptional
	@Selector("didPresentAlertView:")
	public native void didPresentAlertView(UIAlertView alertView);

	@NotImplemented
	@Override
	@IsOptional
	@Selector("willPresentAlertView:")
	public native void willPresentAlertView(UIAlertView alertView);
	
	
	static {
		NatJ.register();
	}


	@Generated("NatJ")
	@Selector("alloc")
	public static native IOSInput alloc();
	
	@Selector("init")
	public native IOSInput init();

	@Generated("NatJ")
	protected IOSInput(Pointer peer) {
		super(peer);
	}

	@Override
	public void vibrate(int milliseconds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vibrate(long[] pattern, int repeat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelVibrate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getAzimuth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getPitch() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getRoll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void getRotationMatrix(float[] matrix) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isKeyJustPressed(int key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCatchBackKey() {
		// TODO Auto-generated method stub
		return false;
	}
	
	private int findPointer (UITouch touch) {
		long ptr = touch.getPeerPointer();
		for (int i = 0; i < touchDown.length; i++) {
			if (touchDown[i] == ptr) return i;
		}
		throw new GdxRuntimeException("Couldn't find pointer id for touch event!");
	}
	
	private int getFreePointer () {
		for (int i = 0; i < touchDown.length; i++) {
			if (touchDown[i] == 0) return i;
		}
		throw new GdxRuntimeException("Couldn't find free pointer id!");
	}

}