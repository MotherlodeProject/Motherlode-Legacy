package contrivitive.gui.element.background;

import contrivitive.gui.GuiBlueprint;
import contrivitive.gui.IContrivitiveGui;
import contrivitive.gui.element.Element;
import contrivitive.util.RenderUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DefaultBackgroundElement extends Element {

	@SideOnly(Side.CLIENT)
	@Override
	public <G extends GuiScreen, B extends GuiBlueprint> void draw(IContrivitiveGui<G,B> gui, int x, int y, int mouseX, int mouseY, float elapsedTicks) {
		RenderUtil.drawDefaultGuiBackground(gui);
	}
}
