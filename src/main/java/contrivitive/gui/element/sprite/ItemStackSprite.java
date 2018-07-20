package contrivitive.gui.element.sprite;

import contrivitive.gui.IContrivitiveGui;
import contrivitive.util.RenderUtil;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemStackSprite extends Sprite {
	public ItemStack itemStack;
	public float scale;

	public ItemStackSprite(ItemStack itemStack) {
		this(itemStack, 1);
	}

	public ItemStackSprite(ItemStack itemStack, float scale) {
		super(0, 0, (int) (16 / scale), (int) (16 / scale));
		this.itemStack = itemStack;
		this.scale = scale;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void draw(IContrivitiveGui gui, int x, int y, float elapsedTicks) {
		x = RenderUtil.adjustX(gui, x);
		y = RenderUtil.adjustY(gui, y);
		RenderHelper.enableGUIStandardItemLighting();
		GlStateManager.pushMatrix();
		gui.getRenderItem().zLevel = 0;
		gui.getRenderItem().renderItemAndEffectIntoGUI(itemStack, (int) (x / scale), (int) (y / scale));
		GlStateManager.popMatrix();
	}
}
