package motherlode.item;

import motherlode.Motherlode;
import motherlode.entity.EntityBomb;
import motherlode.util.ItemUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class ItemBomb extends Item {

	public static final int FUSE_TICKS = 50; // Maximum fuse lifetime
	public static final float EXPLOSION_STRENGTH = 1.5F;

	public ItemBomb() {
		super();
		ItemUtil.setupItem(this, "bomb");
		this.setMaxStackSize(10);
		this.setHasSubtypes(false);
		this.setMaxDamage(FUSE_TICKS);

		// Add override to change texture depending on fuseState, see json
		if (!Motherlode.proxy.isDedicatedServer()) {
			this.addPropertyOverride(new ResourceLocation("fuseState"), new ItemBombAnimation());
		}
	}

	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		if (stack.getTagCompound() == null) {
			stack.setTagCompound(new NBTTagCompound());
		}
		stack.getTagCompound().setBoolean("isLit", false);
	}

	// Light the fuse on first right click, throw the BOMB on second right click
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		if (stack.getTagCompound() == null) {
			stack.setTagCompound(new NBTTagCompound());
		}

		if (!stack.getTagCompound().getBoolean("isLit")) {
			stack.getTagCompound().setBoolean("isLit", true);
		} else {
			stack.getTagCompound().setBoolean("isLit", false);

			if (!worldIn.isRemote) {
				EntityBomb entitybomb = new EntityBomb(worldIn, playerIn);
				entitybomb.setRotationYaw(playerIn.rotationYaw);
				entitybomb.setFuseTicksAlive(stack.getItemDamage());
				entitybomb.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.3F, 1.0F);
				worldIn.spawnEntity(entitybomb);
			}
			stack.setItemDamage(0);
			stack.shrink(1);
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	// Item damage acts as the fuse's timer, takes 1 dmg/tick, until the BOMB explodes in player's hand
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if ((stack.getTagCompound() != null) && stack.getTagCompound().getBoolean("isLit")) {

			if (stack.getItemDamage() < stack.getMaxDamage()) {
				stack.setItemDamage(stack.getItemDamage() + 1);
			} else {
				stack.shrink(1);
				stack.setItemDamage(0);
				worldIn.createExplosion(entityIn, entityIn.posX, entityIn.posY, entityIn.posZ, EXPLOSION_STRENGTH, true);
				stack.getTagCompound().setBoolean("isLit", false);
				entityIn.attackEntityFrom(DamageSource.GENERIC, 20);
			}
		}
	}

	// Stop reequipanimation from being played each time the item takes damage
	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		if ((newStack.getTagCompound() != null) && newStack.getTagCompound().getBoolean("isLit")) {
			return false;
		}
		return super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged);
	}

	public static class ItemBombAnimation implements IItemPropertyGetter {

		// Returns the index for the "override" in item json
		@Override
		public float apply(ItemStack stack, World worldIn, EntityLivingBase entityIn) {
			if ((stack.getTagCompound() != null) && stack.getTagCompound().getBoolean("isLit")) {
				return 1;
			}
			return 0;
		}
	}
}
