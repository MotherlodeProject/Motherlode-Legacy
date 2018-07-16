package contrivitive.gui.element.sprite;

import contrivitive.gui.IContrivitiveGui;
import contrivitive.util.RenderUtils;
import net.minecraft.util.ResourceLocation;

public class SheetSprite extends Sprite {
	public final ResourceLocation sheet;
	public final int textureX;
	public final int textureY;

	public SheetSprite(ResourceLocation sheet, int textureX, int textureY, int width, int height, int offsetX, int offsetY) {
		super(width, height, offsetX, offsetY);
		this.textureX = textureX;
		this.textureY = textureY;
		this.sheet = sheet;
	}

	public SheetSprite(ResourceLocation textureLocation, int textureX, int textureY, int width, int height) {
		this(textureLocation, textureX, textureY, width, height, 0, 0);
	}

	@Override
	public void draw(IContrivitiveGui gui, int x, int y, float elapsedTicks) {
		RenderUtils.drawRectFromSheet(gui, sheet, x + offsetX, y + offsetY, this.textureX, this.textureY, width, height);
	}
}
