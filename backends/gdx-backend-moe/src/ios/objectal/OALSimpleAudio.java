package ios.objectal;


import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Mapped;
import com.intel.inde.moe.natj.general.ann.MappedReturn;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.general.ptr.IntPtr;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCClassBinding;
import com.intel.inde.moe.natj.objc.ann.Selector;
import com.intel.inde.moe.natj.objc.map.ObjCObjectMapper;
import ios.NSObject;
import ios.foundation.NSURL;
import java.lang.String;

@Generated
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class OALSimpleAudio extends NSObject {
	static {
		NatJ.register();
	}

	@Generated
	protected OALSimpleAudio(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native OALSimpleAudio alloc();

	@Generated
	@Selector("allowIpod")
	public native boolean allowIpod();

	@Generated
	@Selector("backgroundTrack")
	public native IntPtr backgroundTrack();

	@Generated
	@Selector("backgroundTrackURL")
	public native NSURL backgroundTrackURL();

	@Generated
	@Selector("bgMuted")
	public native boolean bgMuted();

	@Generated
	@Selector("bgPaused")
	public native boolean bgPaused();

	@Generated
	@Selector("bgPlaying")
	public native boolean bgPlaying();

	@Generated
	@Selector("bgVolume")
	public native float bgVolume();

	@Generated
	@Selector("channel")
	public native IntPtr channel();

	@Generated
	@Selector("context")
	public native IntPtr context();

	@Generated
	@Selector("device")
	public native IntPtr device();

	@Generated
	@Selector("effectsMuted")
	public native boolean effectsMuted();

	@Generated
	@Selector("effectsPaused")
	public native boolean effectsPaused();

	@Generated
	@Selector("effectsVolume")
	public native float effectsVolume();

	@Generated
	@Selector("honorSilentSwitch")
	public native boolean honorSilentSwitch();

	@Generated
	@Selector("init")
	public native OALSimpleAudio init();

	@Generated
	@Selector("initWithReservedSources:monoSources:stereoSources:")
	public native OALSimpleAudio initWithReservedSourcesMonoSourcesStereoSources(
			int reservedSources, int monoSources, int stereoSources);

	@Generated
	@Selector("initWithSources:")
	public native OALSimpleAudio initWithSources(int reservedSources);

	@Generated
	@Selector("interrupted")
	public native boolean interrupted();

	@Generated
	@Selector("manuallySuspended")
	public native boolean manuallySuspended();

	@Generated
	@Selector("muted")
	public native boolean muted();

	@Generated
	@Selector("paused")
	public native boolean paused();

	@Generated
	@Selector("playBg")
	public native boolean playBg();

	@Generated
	@Selector("playBg:")
	public native boolean playBg(String path);

	@Generated
	@Selector("playBg:loop:")
	public native boolean playBgLoop(String path, boolean loop);

	@Generated
	@Selector("playBg:volume:pan:loop:")
	public native boolean playBgVolumePanLoop(String filePath, float volume,
			float pan, boolean loop);

	@Generated
	@Selector("playBgWithLoop:")
	public native boolean playBgWithLoop(boolean loop);

	@Generated
	@Selector("playBuffer:volume:pitch:pan:loop:")
	@MappedReturn(ObjCObjectMapper.class)
	public native Object playBufferVolumePitchPanLoop(
			@Mapped(ObjCObjectMapper.class) Object buffer, float volume,
			float pitch, float pan, boolean loop);

	@Generated
	@Selector("playEffect:")
	@MappedReturn(ObjCObjectMapper.class)
	public native Object playEffect(String filePath);

	@Generated
	@Selector("playEffect:loop:")
	@MappedReturn(ObjCObjectMapper.class)
	public native Object playEffectLoop(String filePath, boolean loop);

	@Generated
	@Selector("playEffect:volume:pitch:pan:loop:")
	@MappedReturn(ObjCObjectMapper.class)
	public native ALSource playEffectVolumePitchPanLoop(String filePath,
			float volume, float pitch, float pan, boolean loop);

	@Generated
	@Selector("preloadBg:")
	public native boolean preloadBg(String path);

	@Generated
	@Selector("preloadBg:seekTime:")
	public native boolean preloadBgSeekTime(String path, double seekTime);

	@Generated
	@Selector("preloadCacheCount")
	public native int preloadCacheCount();

	@Generated
	@Selector("preloadCacheEnabled")
	public native boolean preloadCacheEnabled();

	@Generated
	@Selector("preloadEffect:")
	@MappedReturn(ObjCObjectMapper.class)
	public native ALBuffer preloadEffect(String filePath);

	@Generated
	@Selector("preloadEffect:reduceToMono:")
	@MappedReturn(ObjCObjectMapper.class)
	public native Object preloadEffectReduceToMono(String filePath,
			boolean reduceToMono);

	@Generated
	@Selector("reservedSources")
	public native int reservedSources();

	@Generated
	@Selector("resetToDefault")
	public native void resetToDefault();

	@Generated
	@Selector("setAllowIpod:")
	public native void setAllowIpod(boolean value);

	@Generated
	@Selector("setBgMuted:")
	public native void setBgMuted(boolean value);

	@Generated
	@Selector("setBgPaused:")
	public native void setBgPaused(boolean value);

	@Generated
	@Selector("setBgVolume:")
	public native void setBgVolume(float value);

	@Generated
	@Selector("setEffectsMuted:")
	public native void setEffectsMuted(boolean value);

	@Generated
	@Selector("setEffectsPaused:")
	public native void setEffectsPaused(boolean value);

	@Generated
	@Selector("setEffectsVolume:")
	public native void setEffectsVolume(float value);

	@Generated
	@Selector("setHonorSilentSwitch:")
	public native void setHonorSilentSwitch(boolean value);

	@Generated
	@Selector("setManuallySuspended:")
	public native void setManuallySuspended(boolean value);

	@Generated
	@Selector("setMuted:")
	public native void setMuted(boolean value);

	@Generated
	@Selector("setPaused:")
	public native void setPaused(boolean value);

	@Generated
	@Selector("setPreloadCacheEnabled:")
	public native void setPreloadCacheEnabled(boolean value);

	@Generated
	@Selector("setReservedSources:")
	public native void setReservedSources(int value);

	@Generated
	@Selector("setUseHardwareIfAvailable:")
	public native void setUseHardwareIfAvailable(boolean value);

	@Generated
	@Selector("sharedInstanceWithReservedSources:monoSources:stereoSources:")
	public static native OALSimpleAudio sharedInstanceWithReservedSourcesMonoSourcesStereoSources(
			int reservedSources, int monoSources, int stereoSources);

	@Generated
	@Selector("sharedInstanceWithSources:")
	public static native OALSimpleAudio sharedInstanceWithSources(int sources);

	@Generated
	@Selector("stopAllEffects")
	public native void stopAllEffects();

	@Generated
	@Selector("stopBg")
	public native void stopBg();

	@Generated
	@Selector("stopEverything")
	public native void stopEverything();

	@Generated
	@Selector("suspended")
	public native boolean suspended();

	@Generated
	@Selector("unloadAllEffects")
	public native void unloadAllEffects();

	@Generated
	@Selector("unloadEffect:")
	public native boolean unloadEffect(String filePath);

	@Generated
	@Selector("useHardwareIfAvailable")
	public native boolean useHardwareIfAvailable();
	
	/** Start OALSimpleAudio with the specified number of reserved sources.
	 * Call this initializer if you want to use OALSimpleAudio, but keep some of the device's
	 * audio sources (there are 32 in total) for your own use. <br>
	 * <strong>Note:</strong> This method must be called ONLY ONCE, <em>BEFORE</em>
	 * any attempt is made to access the shared instance.
	 * To change the reserved sources after instantiation, modify reservedSources.
	 *
	 * @param sources the number of sources OALSimpleAudio will reserve for itself.
	 * @return The shared instance.
	 */
	
	private static int reservedSources = 16;
	
	static OALSimpleAudio instance = null;
	
	public static OALSimpleAudio sharedInstance() {
		if (instance == null) {
			instance = sharedInstanceWithSources(reservedSources);
		}
		return instance;
	}
}