package ios.objectal.protocol;


import com.intel.inde.moe.natj.general.ann.ByValue;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Mapped;
import com.intel.inde.moe.natj.general.ann.MappedReturn;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.SEL;
import com.intel.inde.moe.natj.objc.ann.ObjCProtocolName;
import com.intel.inde.moe.natj.objc.ann.Selector;
import com.intel.inde.moe.natj.objc.map.ObjCObjectMapper;
import ios.objectal.ALBuffer;
import ios.objectal.struct.ALPoint;
import ios.objectal.struct.ALVector;

@Generated
@Runtime(ObjCRuntime.class)
@ObjCProtocolName("ALSoundSource")
public interface ALSoundSource {
	@Generated
	@Selector("clear")
	public void clear();

	@Generated
	@Selector("coneInnerAngle")
	public float coneInnerAngle();

	@Generated
	@Selector("coneOuterAngle")
	public float coneOuterAngle();

	@Generated
	@Selector("coneOuterGain")
	public float coneOuterGain();

	@Generated
	@Selector("direction")
	@ByValue
	public ALVector direction();

	@Generated
	@Selector("fadeTo:duration:target:selector:")
	public void fadeToDurationTargetSelector(float gain, float duration,
			@Mapped(ObjCObjectMapper.class) Object target, SEL selector);

	@Generated
	@Selector("gain")
	public float gain();

	@Generated
	@Selector("interruptible")
	public boolean interruptible();

	@Generated
	@Selector("looping")
	public boolean looping();

	@Generated
	@Selector("maxDistance")
	public float maxDistance();

	@Generated
	@Selector("maxGain")
	public float maxGain();

	@Generated
	@Selector("minGain")
	public float minGain();

	@Generated
	@Selector("muted")
	public boolean muted();

	@Generated
	@Selector("pan")
	public float pan();

	@Generated
	@Selector("panTo:duration:target:selector:")
	public void panToDurationTargetSelector(float pan, float duration,
			@Mapped(ObjCObjectMapper.class) Object target, SEL selector);

	@Generated
	@Selector("paused")
	public boolean paused();

	@Generated
	@Selector("pitch")
	public float pitch();

	@Generated
	@Selector("pitchTo:duration:target:selector:")
	public void pitchToDurationTargetSelector(float pitch, float duration,
			@Mapped(ObjCObjectMapper.class) Object target, SEL selector);

	@Generated
	@Selector("play:")
	@MappedReturn(ObjCObjectMapper.class)
	public Object play(ALBuffer buffer);

	@Generated
	@Selector("play:gain:pitch:pan:loop:")
	@MappedReturn(ObjCObjectMapper.class)
	public Object playGainPitchPanLoop(ALBuffer buffer, float gain,
			float pitch, float pan, boolean loop);

	@Generated
	@Selector("play:loop:")
	@MappedReturn(ObjCObjectMapper.class)
	public Object playLoop(ALBuffer buffer, boolean loop);

	@Generated
	@Selector("playing")
	public boolean playing();

	@Generated
	@Selector("position")
	@ByValue
	public ALPoint position();

	@Generated
	@Selector("referenceDistance")
	public float referenceDistance();

	@Generated
	@Selector("reverbObstruction")
	public float reverbObstruction();

	@Generated
	@Selector("reverbOcclusion")
	public float reverbOcclusion();

	@Generated
	@Selector("reverbSendLevel")
	public float reverbSendLevel();

	@Generated
	@Selector("rewind")
	public void rewind();

	@Generated
	@Selector("rolloffFactor")
	public float rolloffFactor();

	@Generated
	@Selector("setConeInnerAngle:")
	public void setConeInnerAngle(float value);

	@Generated
	@Selector("setConeOuterAngle:")
	public void setConeOuterAngle(float value);

	@Generated
	@Selector("setConeOuterGain:")
	public void setConeOuterGain(float value);

	@Generated
	@Selector("setDirection:")
	public void setDirection(@ByValue ALVector value);

	@Generated
	@Selector("setGain:")
	public void setGain(float value);

	@Generated
	@Selector("setInterruptible:")
	public void setInterruptible(boolean value);

	@Generated
	@Selector("setLooping:")
	public void setLooping(boolean value);

	@Generated
	@Selector("setMaxDistance:")
	public void setMaxDistance(float value);

	@Generated
	@Selector("setMaxGain:")
	public void setMaxGain(float value);

	@Generated
	@Selector("setMinGain:")
	public void setMinGain(float value);

	@Generated
	@Selector("setMuted:")
	public void setMuted(boolean value);

	@Generated
	@Selector("setPan:")
	public void setPan(float value);

	@Generated
	@Selector("setPaused:")
	public void setPaused(boolean value);

	@Generated
	@Selector("setPitch:")
	public void setPitch(float value);

	@Generated
	@Selector("setPosition:")
	public void setPosition(@ByValue ALPoint value);

	@Generated
	@Selector("setReferenceDistance:")
	public void setReferenceDistance(float value);

	@Generated
	@Selector("setReverbObstruction:")
	public void setReverbObstruction(float value);

	@Generated
	@Selector("setReverbOcclusion:")
	public void setReverbOcclusion(float value);

	@Generated
	@Selector("setReverbSendLevel:")
	public void setReverbSendLevel(float value);

	@Generated
	@Selector("setRolloffFactor:")
	public void setRolloffFactor(float value);

	@Generated
	@Selector("setSourceRelative:")
	public void setSourceRelative(int value);

	@Generated
	@Selector("setVelocity:")
	public void setVelocity(@ByValue ALVector value);

	@Generated
	@Selector("setVolume:")
	public void setVolume(float value);

	@Generated
	@Selector("sourceRelative")
	public int sourceRelative();

	@Generated
	@Selector("sourceType")
	public int sourceType();

	@Generated
	@Selector("stop")
	public void stop();

	@Generated
	@Selector("stopActions")
	public void stopActions();

	@Generated
	@Selector("stopFade")
	public void stopFade();

	@Generated
	@Selector("stopPan")
	public void stopPan();

	@Generated
	@Selector("stopPitch")
	public void stopPitch();

	@Generated
	@Selector("velocity")
	@ByValue
	public ALVector velocity();

	@Generated
	@Selector("volume")
	public float volume();
}