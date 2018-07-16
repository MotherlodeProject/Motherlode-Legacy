package contrivitive.gui.element.sprite;

import contrivitive.gui.IContrivitiveGui;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemStackSprite extends Sprite {
	public ItemStack itemStack;

	public ItemStackSprite(ItemStack itemStack) {
		super(0, 0, 16, 16);
		this.itemStack = itemStack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void draw(IContrivitiveGui gui, int x, int y, float elapsedTicks) {

	}
}
