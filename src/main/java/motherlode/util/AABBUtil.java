package motherlode.util;

import net.minecraft.util.math.AxisAlignedBB;

public class AABBUtil {
	public static AxisAlignedBB makeAABB(double x1, double y1, double z1, double x2, double y2, double z2) {
		return new AxisAlignedBB(1D / 16D * x1, 1D / 16D * y1, 1D / 16D * z1, 1D / 16D * x2, 1D / 16D * y2, 1D / 16D * z2);
	}
}
