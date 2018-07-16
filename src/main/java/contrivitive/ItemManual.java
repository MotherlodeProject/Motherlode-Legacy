package contrivitive;

import contrivitive.lib.ContrivitiveConstants;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemManual extends Item {
	public ItemManual() {
		setRegistryName(ContrivitiveConstants.MOD_ID, "manual");
		setUnlocalizedName(ContrivitiveConstants.MOD_ID + ".manual");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		playerIn.openGui(Contrivitive.instance, 0, worldIn, playerIn.getPosition().getX(), playerIn.getPosition().getY(), playerIn.getPosition().getZ());
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
}
