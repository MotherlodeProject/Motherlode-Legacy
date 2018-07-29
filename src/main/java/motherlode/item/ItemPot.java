package motherlode.item;

import net.minecraft.block.Block;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

@SuppressWarnings("deprecation")
public class ItemPot extends ItemBlock {
	public ItemPot(Block block) {
		super(block);
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Color")) {
			return I18n.translateToLocal("item.fireworksCharge." + EnumDyeColor.byMetadata(stack.getTagCompound().getInteger("Color")).getUnlocalizedName()) + " " + super.getItemStackDisplayName(stack);
		}
		return super.getItemStackDisplayName(stack);
	}
}
