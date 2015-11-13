package ios.objectal.c;


import com.intel.inde.moe.natj.c.CRuntime;
import com.intel.inde.moe.natj.c.ann.CFunction;
import com.intel.inde.moe.natj.c.ann.CVariable;
import com.intel.inde.moe.natj.c.ann.Inline;
import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.ann.ByValue;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.MappedReturn;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.objc.map.ObjCStringMapper;
import ios.objectal.OALSimpleAudio;
import ios.objectal.struct.ALOrientation;
import ios.objectal.struct.ALPoint;
import ios.objectal.struct.ALVector;
import java.lang.String;

@Generated
@Runtime(CRuntime.class)
public final class Globals {
	static {
		NatJ.register();
	}

	@Generated
	private Globals() {
	}

	@Generated
	@CFunction
	public static native int SYNTHESIZE_SINGLETON_FOR_CLASS_HEADER(
			OALSimpleAudio arg1);

	@Generated
	@Inline
	@CFunction
	@ByValue
	public static native ALPoint alpoint(float x, float y, float z);

	@Generated
	@Inline
	@CFunction
	@ByValue
	public static native ALVector alvector(float x, float y, float z);

	@Generated
	@Inline
	@CFunction
	@ByValue
	public static native ALOrientation alorientation(float atX, float atY,
			float atZ, float upX, float upY, float upZ);

	@Generated
	@Inline
	@CFunction
	@ByValue
	public static native ALPoint ALPointMake(float x, float y, float z);

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String OALAudioTrackSourceChangedNotification();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String OALAudioTrackStartedPlayingNotification();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String OALAudioTrackStoppedPlayingNotification();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String OALAudioTrackFinishedPlayingNotification();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String OALAudioTrackLoopedNotification();
}