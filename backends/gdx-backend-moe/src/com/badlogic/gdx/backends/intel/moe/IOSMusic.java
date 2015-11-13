/*******************************************************************************
 * Copyright 2013 See AUTHORS file.
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
import ios.avfoundation.protocol.AVAudioPlayerDelegate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.objc.ann.NotImplemented;

import ios.foundation.NSError;
import ios.objectal.OALAudioTrack;

import com.intel.inde.moe.natj.objc.ann.IsOptional;
import com.intel.inde.moe.natj.objc.ann.Selector;

import ios.avfoundation.AVAudioPlayer;

public class IOSMusic extends NSObject implements Music, AVAudioPlayerDelegate {
	
	private OALAudioTrack track;
	private OnCompletionListener onCompletionListener;
	
	static {
		NatJ.register();
	}
	
	@Selector("init")
	public native IOSMusic init();
	
	public void iOSMusic(OALAudioTrack track) {
		this.track = track;
		this.track.setDelegate(this);
	}

	/** AVAudioPlayerDelegate **/
	@Selector("audioPlayerDidFinishPlaying:successfully:")
	public void audioPlayerDidFinishPlayingSuccessfully(AVAudioPlayer player,
			boolean flag) {
		
		final OnCompletionListener listener = onCompletionListener;
		if (listener != null) {
			Gdx.app.postRunnable(new Runnable() {
				@Override
				public void run () {
					listener.onCompletion(IOSMusic.this);
				}
			});
		}
		
	}
	
	@Override
	public void play () {
		if (track.paused()) {
			track.setPaused(false);
		} else {
			track.play();
		}
	}

	@Override
	public void pause () {
		if (track.playing()) {
			track.setPaused(true);
		}
	}

	@Override
	public void stop () {
		track.stop();
	}

	@Override
	public boolean isPlaying () {
		return track.playing();
	}

	@Override
	public void setLooping (boolean isLooping) {
		track.setNumberOfLoops(isLooping ? -1 : 0);
	}

	@Override
	public boolean isLooping () {
		return track.numberOfLoops() == -1;
	}

	@Override
	public void setVolume (float volume) {
		track.setVolume(volume);
	}

	public void setPosition (float position) {
		track.setCurrentTime(position / 1000);
	}

	@Override
	public float getPosition () {
		return (float) (track.currentTime() * 1000.0);
	}

	@Override
	public void dispose () {
		track.clear();
	}

	@Override
	public float getVolume() {
		return track.volume();
	}

	@Override
	public void setPan(float pan, float volume) {
		track.setPan(pan);
		track.setVolume(volume);
	}

	@Override
	public void setOnCompletionListener (OnCompletionListener listener) {
		this.onCompletionListener = listener;
	}

	@NotImplemented
	@Override
	@IsOptional
	@Selector("audioPlayerBeginInterruption:")
	public native void audioPlayerBeginInterruption(AVAudioPlayer player);

	@NotImplemented
	@Override
	@IsOptional
	@Selector("audioPlayerDecodeErrorDidOccur:error:")
	public native void audioPlayerDecodeErrorDidOccurError(
			AVAudioPlayer player, NSError error);

	@NotImplemented
	@Override
	@IsOptional
	@Selector("audioPlayerEndInterruption:")
	@Deprecated
	public native void audioPlayerEndInterruption(AVAudioPlayer player);

	@NotImplemented
	@IsOptional
	@Selector("audioPlayerEndInterruption:withFlags:")
	@Deprecated
	public native void audioPlayerEndInterruptionWithFlags(
			AVAudioPlayer player, int flags);

	@NotImplemented
	@IsOptional
	@Selector("audioPlayerEndInterruption:withOptions:")
	public native void audioPlayerEndInterruptionWithOptions(
			AVAudioPlayer player, int flags);

	@Generated("NatJ")
	@Selector("alloc")
	public static native IOSMusic alloc();

	@Generated("NatJ")
	protected IOSMusic(Pointer peer) {
		super(peer);
	}
	
}