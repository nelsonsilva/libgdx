package ios.openal.c;


import com.intel.inde.moe.natj.c.CRuntime;
import com.intel.inde.moe.natj.c.ann.CFunction;
import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.general.ann.UncertainArgument;
import com.intel.inde.moe.natj.general.ann.UncertainReturn;
import com.intel.inde.moe.natj.general.ptr.BytePtr;
import com.intel.inde.moe.natj.general.ptr.ConstFloatPtr;
import com.intel.inde.moe.natj.general.ptr.ConstIntPtr;
import com.intel.inde.moe.natj.general.ptr.ConstVoidPtr;
import com.intel.inde.moe.natj.general.ptr.DoublePtr;
import com.intel.inde.moe.natj.general.ptr.FloatPtr;
import com.intel.inde.moe.natj.general.ptr.IntPtr;
import com.intel.inde.moe.natj.general.ptr.VoidPtr;
import java.lang.String;

@Generated
@Library("OpenAL")
@Runtime(CRuntime.class)
public final class OpenAL {
    @Generated
    private OpenAL() {
    }

    @CFunction
    @Generated
    public static native void alEnable(int var0);

    @CFunction
    @Generated
    public static native void alDisable(int var0);

    @CFunction
    @Generated
    public static native byte alIsEnabled(int var0);

    @CFunction
    @Generated
    public static native String alGetString(int var0);

    @CFunction
    @Generated
    public static native void alGetBooleanv(int var0, BytePtr var1);

    @CFunction
    @Generated
    public static native void alGetIntegerv(int var0, IntPtr var1);

    @CFunction
    @Generated
    public static native void alGetFloatv(int var0, FloatPtr var1);

    @CFunction
    @Generated
    public static native void alGetDoublev(int var0, DoublePtr var1);

    @CFunction
    @Generated
    public static native byte alGetBoolean(int var0);

    @CFunction
    @Generated
    public static native int alGetInteger(int var0);

    @CFunction
    @Generated
    public static native float alGetFloat(int var0);

    @CFunction
    @Generated
    public static native double alGetDouble(int var0);

    @CFunction
    @Generated
    public static native int alGetError();

    @CFunction
    @Generated
    public static native byte alIsExtensionPresent(String var0);

    @CFunction
    @Generated
    public static native VoidPtr alGetProcAddress(String var0);

    @CFunction
    @Generated
    public static native int alGetEnumValue(String var0);

    @CFunction
    @Generated
    public static native void alListenerf(int var0, float var1);

    @CFunction
    @Generated
    public static native void alListener3f(int var0, float var1, float var2, float var3);

    @CFunction
    @Generated
    public static native void alListenerfv(int var0, ConstFloatPtr var1);

    @CFunction
    @Generated
    public static native void alListeneri(int var0, int var1);

    @CFunction
    @Generated
    public static native void alListener3i(int var0, int var1, int var2, int var3);

    @CFunction
    @Generated
    public static native void alListeneriv(int var0, ConstIntPtr var1);

    @CFunction
    @Generated
    public static native void alGetListenerf(int var0, FloatPtr var1);

    @CFunction
    @Generated
    public static native void alGetListener3f(int var0, FloatPtr var1, FloatPtr var2, FloatPtr var3);

    @CFunction
    @Generated
    public static native void alGetListenerfv(int var0, FloatPtr var1);

    @CFunction
    @Generated
    public static native void alGetListeneri(int var0, IntPtr var1);

    @CFunction
    @Generated
    public static native void alGetListener3i(int var0, IntPtr var1, IntPtr var2, IntPtr var3);

    @CFunction
    @Generated
    public static native void alGetListeneriv(int var0, IntPtr var1);

    @CFunction
    @Generated
    public static native void alGenSources(int var0, IntPtr var1);

    @CFunction
    @Generated
    public static native void alDeleteSources(int var0, ConstIntPtr var1);

    @CFunction
    @Generated
    public static native byte alIsSource(int var0);

    @CFunction
    @Generated
    public static native void alSourcef(int var0, int var1, float var2);

    @CFunction
    @Generated
    public static native void alSource3f(int var0, int var1, float var2, float var3, float var4);

    @CFunction
    @Generated
    public static native void alSourcefv(int var0, int var1, ConstFloatPtr var2);

    @CFunction
    @Generated
    public static native void alSourcei(int var0, int var1, int var2);

    @CFunction
    @Generated
    public static native void alSource3i(int var0, int var1, int var2, int var3, int var4);

    @CFunction
    @Generated
    public static native void alSourceiv(int var0, int var1, ConstIntPtr var2);

    @CFunction
    @Generated
    public static native void alGetSourcef(int var0, int var1, FloatPtr var2);

    @CFunction
    @Generated
    public static native void alGetSource3f(int var0, int var1, FloatPtr var2, FloatPtr var3, FloatPtr var4);

    @CFunction
    @Generated
    public static native void alGetSourcefv(int var0, int var1, FloatPtr var2);

    @CFunction
    @Generated
    public static native void alGetSourcei(int var0, int var1, IntPtr var2);

    @CFunction
    @Generated
    public static native void alGetSource3i(int var0, int var1, IntPtr var2, IntPtr var3, IntPtr var4);

    @CFunction
    @Generated
    public static native void alGetSourceiv(int var0, int var1, IntPtr var2);

    @CFunction
    @Generated
    public static native void alSourcePlayv(int var0, ConstIntPtr var1);

    @CFunction
    @Generated
    public static native void alSourceStopv(int var0, ConstIntPtr var1);

    @CFunction
    @Generated
    public static native void alSourceRewindv(int var0, ConstIntPtr var1);

    @CFunction
    @Generated
    public static native void alSourcePausev(int var0, ConstIntPtr var1);

    @CFunction
    @Generated
    public static native void alSourcePlay(int var0);

    @CFunction
    @Generated
    public static native void alSourceStop(int var0);

    @CFunction
    @Generated
    public static native void alSourceRewind(int var0);

    @CFunction
    @Generated
    public static native void alSourcePause(int var0);

    @CFunction
    @Generated
    public static native void alSourceQueueBuffers(int var0, int var1, ConstIntPtr var2);

    @CFunction
    @Generated
    public static native void alSourceUnqueueBuffers(int var0, int var1, IntPtr var2);

    @CFunction
    @Generated
    public static native void alGenBuffers(int var0, IntPtr var1);

    @CFunction
    @Generated
    public static native void alDeleteBuffers(int var0, ConstIntPtr var1);

    @CFunction
    @Generated
    public static native byte alIsBuffer(int var0);

    @CFunction
    @Generated
    public static native void alBufferData(int var0, int var1, ConstVoidPtr var2, int var3, int var4);

    @CFunction
    @Generated
    public static native void alBufferf(int var0, int var1, float var2);

    @CFunction
    @Generated
    public static native void alBuffer3f(int var0, int var1, float var2, float var3, float var4);

    @CFunction
    @Generated
    public static native void alBufferfv(int var0, int var1, ConstFloatPtr var2);

    @CFunction
    @Generated
    public static native void alBufferi(int var0, int var1, int var2);

    @CFunction
    @Generated
    public static native void alBuffer3i(int var0, int var1, int var2, int var3, int var4);

    @CFunction
    @Generated
    public static native void alBufferiv(int var0, int var1, ConstIntPtr var2);

    @CFunction
    @Generated
    public static native void alGetBufferf(int var0, int var1, FloatPtr var2);

    @CFunction
    @Generated
    public static native void alGetBuffer3f(int var0, int var1, FloatPtr var2, FloatPtr var3, FloatPtr var4);

    @CFunction
    @Generated
    public static native void alGetBufferfv(int var0, int var1, FloatPtr var2);

    @CFunction
    @Generated
    public static native void alGetBufferi(int var0, int var1, IntPtr var2);

    @CFunction
    @Generated
    public static native void alGetBuffer3i(int var0, int var1, IntPtr var2, IntPtr var3, IntPtr var4);

    @CFunction
    @Generated
    public static native void alGetBufferiv(int var0, int var1, IntPtr var2);

    @CFunction
    @Generated
    public static native void alDopplerFactor(float var0);

    @CFunction
    @Generated
    public static native void alDopplerVelocity(float var0);

    @CFunction
    @Generated
    public static native void alSpeedOfSound(float var0);

    @CFunction
    @Generated
    public static native void alDistanceModel(int var0);

    @CFunction
    @Generated
    public static native VoidPtr alcCreateContext(VoidPtr var0, ConstIntPtr var1);

    @CFunction
    @Generated
    public static native byte alcMakeContextCurrent(VoidPtr var0);

    @CFunction
    @Generated
    public static native void alcProcessContext(VoidPtr var0);

    @CFunction
    @Generated
    public static native void alcSuspendContext(VoidPtr var0);

    @CFunction
    @Generated
    public static native void alcDestroyContext(VoidPtr var0);

    @CFunction
    @Generated
    public static native VoidPtr alcGetCurrentContext();

    @CFunction
    @Generated
    public static native VoidPtr alcGetContextsDevice(VoidPtr var0);

    @CFunction
    @Generated
    public static native VoidPtr alcOpenDevice(String var0);

    @CFunction
    @Generated
    public static native byte alcCloseDevice(VoidPtr var0);

    @CFunction
    @Generated
    public static native int alcGetError(VoidPtr var0);

    @CFunction
    @Generated
    public static native byte alcIsExtensionPresent(VoidPtr var0, String var1);

    @CFunction
    @Generated
    public static native VoidPtr alcGetProcAddress(VoidPtr var0, String var1);

    @CFunction
    @Generated
    public static native int alcGetEnumValue(VoidPtr var0, String var1);

    @CFunction
    @Generated
    public static native String alcGetString(VoidPtr var0, int var1);

    @CFunction
    @Generated
    public static native void alcGetIntegerv(VoidPtr var0, int var1, int var2, IntPtr var3);

    @CFunction
    @Generated
    public static native VoidPtr alcCaptureOpenDevice(String var0, int var1, int var2, int var3);

    @CFunction
    @Generated
    public static native byte alcCaptureCloseDevice(VoidPtr var0);

    @CFunction
    @Generated
    public static native void alcCaptureStart(VoidPtr var0);

    @CFunction
    @Generated
    public static native void alcCaptureStop(VoidPtr var0);

    @CFunction
    @Generated
    public static native void alcCaptureSamples(VoidPtr var0, VoidPtr var1, int var2);

    static {
        NatJ.register();
    }
}