package motherlode.gui;

import contrivitive.gui.IContrivitiveGui;
import contrivitive.gui.element.Element;
import contrivitive.gui.element.sprite.ItemStackSprite;
import contrivitive.util.RenderUtils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CraftingSlotElement extends Element {
	public CraftingSlotElement() {
		super(MLSprites.CRAFTING_SLOT.width, MLSprites.CRAFTING_SLOT.height);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void initClient() {
		super.initClient();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void draw(IContrivitiveGui gui, int x, int y, int mouseX, int mouseY, float elapsedTicks) {
		super.draw(gui, x, y, mouseX, mouseY, elapsedTicks);
		GlStateManager.color(1, 1, 1, 1);
		if (isHovering) {
			MLSprites.HOVERED_CRAFTING_SLOT.draw(gui, x, y, elapsedTicks);
			RenderUtils.drawString(gui, "Golden Sword", x + 25, y + 4, 0.5F, 0xFFFFA0);
		} else {
			MLSprites.CRAFTING_SLOT.draw(gui, x, y, elapsedTicks);
			RenderUtils.drawString(gui, "Golden Sword", x + 25, y + 4, 0.5F, 0xFFFFFF);
		}
		new ItemStackSprite(new ItemStack(Items.GOLDEN_SWORD)).draw(gui, x + 16, y + 36, elapsedTicks);
		GlStateManager.pushMatrix();
		double scale = 0.5;
		GlStateManager.scale(scale, scale, 0.0000000000000000001);
		new ItemStackSprite(new ItemStack(Items.GOLD_INGOT)).draw(gui, (int) ((x + 35) / scale), (int) ((y + 43) / scale), elapsedTicks);
		GlStateManager.popMatrix();
		RenderUtils.drawString(gui, "2", x + 30.5F, y + 13.5F, 0.5F, 0xFFFFFF, true);
		//		RenderUtils.drawString(gui, "64", x + 27.5F, y + 13.5F, 0.5F, 0xFFFFFF, true);
		GlStateManager.pushMatrix();
		GlStateManager.scale(scale, scale, 0.0000000000000000001);
		new ItemStackSprite(new ItemStack(Items.STICK)).draw(gui, (int) ((x + 35 + 10) / scale), (int) ((y + 43) / scale), elapsedTicks);
		GlStateManager.popMatrix();
	}
}
