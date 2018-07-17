package motherlode.gui;

import contrivitive.gui.IContrivitiveGui;
import contrivitive.gui.element.Element;
import contrivitive.util.RenderUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CraftingSlotElement extends Element {
	public CraftingSlotElement() {
		super(55, 18);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void draw(IContrivitiveGui gui, int x, int y, int mouseX, int mouseY, float elapsedTicks) {
		super.draw(gui, x, y, mouseX, mouseY, elapsedTicks);
		if (isHovering) {
			MLSprites.HOVERED_CRAFTING_SLOT.draw(gui, x, y, elapsedTicks);
			MLSprites.CRAFTING_TEXT_BACKGROUND.draw(gui, x + 18, y, elapsedTicks);
		} else {
			MLSprites.CRAFTING_SLOT.draw(gui, x, y, elapsedTicks);
		}
		RenderUtils.drawString(gui, "Gold Ingot", x + 20, y + 2, 0.5F, 0xFFFFFF);

	}
}
