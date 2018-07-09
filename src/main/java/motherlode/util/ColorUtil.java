package motherlode.util;

import java.awt.*;

public class ColorUtil {
	public static int desaturate(int color, float amount) {
		Color c = new Color(color);
		float[] hsb = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
		hsb[1] = hsb[1] * amount;
		return Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
	}
}
