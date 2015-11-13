package ios.objectal.struct;


import com.intel.inde.moe.natj.c.StructObject;
import com.intel.inde.moe.natj.c.ann.Structure;
import com.intel.inde.moe.natj.c.ann.StructureField;
import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.ByValue;
import com.intel.inde.moe.natj.general.ann.Generated;

@Generated
@Structure()
public final class ALOrientation extends StructObject {
	static {
		NatJ.register();
	}
	private static long __natjCache;

	@Generated
	public ALOrientation() {
		super(ALOrientation.class);
	}

	@Generated
	protected ALOrientation(Pointer peer) {
		super(peer);
	}

	@Generated
	public ALOrientation(@ByValue ALVector at, @ByValue ALVector up) {
		super(ALOrientation.class);
		setAt(at);
		setUp(up);
	}

	@Generated
	@StructureField(order = 0, isGetter = true)
	@ByValue
	public native ALVector at();

	@Generated
	@StructureField(order = 0, isGetter = false)
	public native void setAt(@ByValue ALVector value);

	@Generated
	@StructureField(order = 1, isGetter = true)
	@ByValue
	public native ALVector up();

	@Generated
	@StructureField(order = 1, isGetter = false)
	public native void setUp(@ByValue ALVector value);
}