package ios.objectal.struct;


import com.intel.inde.moe.natj.c.StructObject;
import com.intel.inde.moe.natj.c.ann.Structure;
import com.intel.inde.moe.natj.c.ann.StructureField;
import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.Generated;

@Generated
@Structure()
public final class ALPoint extends StructObject {
	static {
		NatJ.register();
	}
	private static long __natjCache;

	@Generated
	public ALPoint() {
		super(ALPoint.class);
	}

	@Generated
	protected ALPoint(Pointer peer) {
		super(peer);
	}

	@Generated
	public ALPoint(float x, float y, float z) {
		super(ALPoint.class);
		setX(x);
		setY(y);
		setZ(z);
	}

	@Generated
	@StructureField(order = 0, isGetter = true)
	public native float x();

	@Generated
	@StructureField(order = 0, isGetter = false)
	public native void setX(float value);

	@Generated
	@StructureField(order = 1, isGetter = true)
	public native float y();

	@Generated
	@StructureField(order = 1, isGetter = false)
	public native void setY(float value);

	@Generated
	@StructureField(order = 2, isGetter = true)
	public native float z();

	@Generated
	@StructureField(order = 2, isGetter = false)
	public native void setZ(float value);
}