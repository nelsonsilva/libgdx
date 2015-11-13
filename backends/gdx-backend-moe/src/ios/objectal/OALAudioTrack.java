package ios.objectal;


import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Mapped;
import com.intel.inde.moe.natj.general.ann.MappedReturn;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.SEL;
import com.intel.inde.moe.natj.objc.ann.IsOptional;
import com.intel.inde.moe.natj.objc.ann.ObjCClassBinding;
import com.intel.inde.moe.natj.objc.ann.Selector;
import com.intel.inde.moe.natj.objc.map.ObjCObjectMapper;
import ios.NSObject;
import ios.avfoundation.AVAudioPlayer;
import ios.avfoundation.protocol.AVAudioPlayerDelegate;
import ios.foundation.NSError;
import ios.foundation.NSURL;
import java.lang.String;

@Generated
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class OALAudioTrack extends NSObject implements AVAudioPlayerDelegate {
	static {
		NatJ.register();
	}

	@Generated
	protected OALAudioTrack(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native OALAudioTrack alloc();

	@Generated
	@IsOptional
	@Deprecated
	@Selector("audioPlayerBeginInterruption:")
	public native void audioPlayerBeginInterruption(AVAudioPlayer player);

	@Generated
	@IsOptional
	@Selector("audioPlayerDecodeErrorDidOccur:error:")
	public native void audioPlayerDecodeErrorDidOccurError(
			AVAudioPlayer player, NSError error);

	@Generated
	@IsOptional
	@Selector("audioPlayerDidFinishPlaying:successfully:")
	public native void audioPlayerDidFinishPlayingSuccessfully(
			AVAudioPlayer player, boolean flag);

	@Generated
	@IsOptional
	@Deprecated
	@Selector("audioPlayerEndInterruption:")
	public native void audioPlayerEndInterruption(AVAudioPlayer player);

	@Generated
	@IsOptional
	@Deprecated
	@Selector("audioPlayerEndInterruption:withFlags:")
	public native void audioPlayerEndInterruptionWithFlags(
			AVAudioPlayer player, int flags);

	@Generated
	@IsOptional
	@Deprecated
	@Selector("audioPlayerEndInterruption:withOptions:")
	public native void audioPlayerEndInterruptionWithOptions(
			AVAudioPlayer player, int flags);

	@Generated
	@Selector("autoPreload")
	public native boolean autoPreload();

	@Generated
	@Selector("averagePowerForChannel:")
	public native float averagePowerForChannel(int channelNumber);

	@Generated
	@Selector("clear")
	public native void clear();

	@Generated
	@Selector("currentTime")
	public native double currentTime();

	@Generated
	@Selector("currentlyLoadedUrl")
	public native NSURL currentlyLoadedUrl();

	@Generated
	@Selector("delegate")
	@MappedReturn(ObjCObjectMapper.class)
	public native Object delegate();

	@Generated
	@Selector("deviceCurrentTime")
	public native double deviceCurrentTime();

	@Generated
	@Selector("duration")
	public native double duration();

	@Generated
	@Selector("fadeTo:duration:target:selector:")
	public native void fadeToDurationTargetSelector(float gain, float duration,
			@Mapped(ObjCObjectMapper.class) Object target, SEL selector);

	@Generated
	@Selector("gain")
	public native float gain();

	@Generated
	@Selector("init")
	public native OALAudioTrack init();

	@Generated
	@Selector("meteringEnabled")
	public native boolean meteringEnabled();

	@Generated
	@Selector("muted")
	public native boolean muted();

	@Generated
	@Selector("numberOfChannels")
	public native int numberOfChannels();

	@Generated
	@Selector("numberOfLoops")
	public native int numberOfLoops();

	@Generated
	@Selector("pan")
	public native float pan();

	@Generated
	@Selector("panTo:duration:target:selector:")
	public native void panToDurationTargetSelector(float pan, float duration,
			@Mapped(ObjCObjectMapper.class) Object target, SEL selector);

	@Generated
	@Selector("paused")
	public native boolean paused();

	@Generated
	@Selector("peakPowerForChannel:")
	public native float peakPowerForChannel(int channelNumber);

	@Generated
	@Selector("play")
	public native boolean play();

	@Generated
	@Selector("playAfterTrack:")
	public native boolean playAfterTrack(OALAudioTrack track);

	@Generated
	@Selector("playAfterTrack:timeAdjust:")
	public native boolean playAfterTrackTimeAdjust(OALAudioTrack track,
			double timeAdjust);

	@Generated
	@Selector("playAtTime:")
	public native boolean playAtTime(double time);

	@Generated
	@Selector("playFile:")
	public native boolean playFile(String path);

	@Generated
	@Selector("playFile:loops:")
	public native boolean playFileLoops(String path, int loops);

	@Generated
	@Selector("playFileAsync:loops:target:selector:")
	public native void playFileAsyncLoopsTargetSelector(String path, int loops,
			@Mapped(ObjCObjectMapper.class) Object target, SEL selector);

	@Generated
	@Selector("playFileAsync:target:selector:")
	public native void playFileAsyncTargetSelector(String path,
			@Mapped(ObjCObjectMapper.class) Object target, SEL selector);

	@Generated
	@Selector("playUrl:")
	public native boolean playUrl(NSURL url);

	@Generated
	@Selector("playUrl:loops:")
	public native boolean playUrlLoops(NSURL url, int loops);

	@Generated
	@Selector("playUrlAsync:loops:target:selector:")
	public native void playUrlAsyncLoopsTargetSelector(NSURL url, int loops,
			@Mapped(ObjCObjectMapper.class) Object target, SEL selector);

	@Generated
	@Selector("playUrlAsync:target:selector:")
	public native void playUrlAsyncTargetSelector(NSURL url,
			@Mapped(ObjCObjectMapper.class) Object target, SEL selector);

	@Generated
	@Selector("player")
	public native AVAudioPlayer player();

	@Generated
	@Selector("playing")
	public native boolean playing();

	@Generated
	@Selector("preloadFile:")
	public native boolean preloadFile(String path);

	@Generated
	@Selector("preloadFile:seekTime:")
	public native boolean preloadFileSeekTime(String path, double seekTime);

	@Generated
	@Selector("preloadFileAsync:seekTime:target:selector:")
	public native boolean preloadFileAsyncSeekTimeTargetSelector(String path,
			double seekTime, @Mapped(ObjCObjectMapper.class) Object target,
			SEL selector);

	@Generated
	@Selector("preloadFileAsync:target:selector:")
	public native boolean preloadFileAsyncTargetSelector(String path,
			@Mapped(ObjCObjectMapper.class) Object target, SEL selector);

	@Generated
	@Selector("preloadUrl:")
	public native boolean preloadUrl(NSURL url);

	@Generated
	@Selector("preloadUrl:seekTime:")
	public native boolean preloadUrlSeekTime(NSURL url, double seekTime);

	@Generated
	@Selector("preloadUrlAsync:seekTime:target:selector:")
	public native boolean preloadUrlAsyncSeekTimeTargetSelector(NSURL url,
			double seekTime, @Mapped(ObjCObjectMapper.class) Object target,
			SEL selector);

	@Generated
	@Selector("preloadUrlAsync:target:selector:")
	public native boolean preloadUrlAsyncTargetSelector(NSURL url,
			@Mapped(ObjCObjectMapper.class) Object target, SEL selector);

	@Generated
	@Selector("preloaded")
	public native boolean preloaded();

	@Generated
	@Selector("setAutoPreload:")
	public native void setAutoPreload(boolean value);

	@Generated
	@Selector("setCurrentTime:")
	public native void setCurrentTime(double value);

	@Generated
	@Selector("setDelegate:")
	public native void setDelegate_unsafe(
			@Mapped(ObjCObjectMapper.class) Object value);

	@Generated
	public void setDelegate(@Mapped(ObjCObjectMapper.class) Object value) {
		com.intel.inde.moe.natj.objc.ObjCObject __old = (com.intel.inde.moe.natj.objc.ObjCObject) delegate();
		if (value != null) {
			com.intel.inde.moe.natj.objc.ObjCRuntime.associateObjCObject(this, value);
		}
		setDelegate_unsafe(value);
		if (__old != null) {
			com.intel.inde.moe.natj.objc.ObjCRuntime.dissociateObjCObject(this, __old);
		}
	}

	@Generated
	@Selector("setGain:")
	public native void setGain(float value);

	@Generated
	@Selector("setMeteringEnabled:")
	public native void setMeteringEnabled(boolean value);

	@Generated
	@Selector("setMuted:")
	public native void setMuted(boolean value);

	@Generated
	@Selector("setNumberOfLoops:")
	public native void setNumberOfLoops(int value);

	@Generated
	@Selector("setPan:")
	public native void setPan(float value);

	@Generated
	@Selector("setPaused:")
	public native void setPaused(boolean value);

	@Generated
	@Selector("setVolume:")
	public native void setVolume(float value);

	@Generated
	@Selector("stop")
	public native void stop();

	@Generated
	@Selector("stopActions")
	public native void stopActions();

	@Generated
	@Selector("stopFade")
	public native void stopFade();

	@Generated
	@Selector("stopPan")
	public native void stopPan();

	@Generated
	@Selector("track")
	public static native OALAudioTrack track();

	@Generated
	@Selector("updateMeters")
	public native void updateMeters();

	@Generated
	@Selector("volume")
	public native float volume();
}