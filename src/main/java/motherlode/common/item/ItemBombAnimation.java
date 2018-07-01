package motherlode.common.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBombAnimation implements IItemPropertyGetter {

	// Returns the index for the "override" in item json
	@Override
	public float apply(ItemStack stack, World worldIn, EntityLivingBase entityIn) {
		if ((stack.getTagCompound() != null) && stack.getTagCompound().getBoolean("isLit")) {
			return 1;
		}
		return 0;
	}
}
