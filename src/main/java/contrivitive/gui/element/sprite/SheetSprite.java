package contrivitive.gui.element.sprite;

import contrivitive.gui.GuiBlueprint;
import contrivitive.gui.IContrivitiveGui;
import contrivitive.util.RenderUtil;
import net.minecraft.client.gui.GuiScreen;
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
	public <G extends GuiScreen, B extends GuiBlueprint> void draw(IContrivitiveGui<G,B> gui, int x, int y, float elapsedTicks) {
		RenderUtil.drawRectFromSheet(gui, sheet, x + offsetX, y + offsetY, this.textureX, this.textureY, width, height);
	}
}
