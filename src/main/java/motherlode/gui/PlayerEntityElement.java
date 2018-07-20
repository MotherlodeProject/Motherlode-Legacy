package motherlode.gui;

import contrivitive.gui.IContrivitiveGui;
import contrivitive.gui.element.Element;
import contrivitive.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PlayerEntityElement extends Element {
	int scale;
	private float oldMouseX;
	private float oldMouseY;

	public PlayerEntityElement(int scale) {
		this.scale = scale;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void draw(IContrivitiveGui gui, int x, int y, int mouseX, int mouseY, float elapsedTicks) {
		RenderUtil.drawEntityFacingMouse(gui, x, y, scale, mouseX, mouseY, Minecraft.getMinecraft().player, 0);
	}
}
