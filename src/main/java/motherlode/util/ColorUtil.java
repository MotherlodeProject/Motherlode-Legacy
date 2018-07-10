package motherlode.util;

import java.awt.*;

public class ColorUtil {
	public static int desaturate(int color, float amount) {
		Color c = new Color(color);
		float[] hsb = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
		hsb[1] = hsb[1] * amount;
		return Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
	}

	public static int desaturateAndBrighten(int color, float amount) {
		Color c = new Color(color);
		float[] hsb = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
		hsb[1] = hsb[1] * amount;
		hsb[2] = hsb[2] * 1.2F;
		return Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
	}
}
