package motherlode.item;

import motherlode.Motherlode;
import motherlode.entity.EntityDynamite;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class ItemDynamite extends ItemMotherlode {

	public static final int FUSE_TICKS = 50;
	public static final float EXPLOSION_STRENGTH = 1.5F;

	public ItemDynamite() {
		super("dynamite");

		this.setMaxStackSize(10);
		this.setHasSubtypes(false);
		this.setMaxDamage(FUSE_TICKS);

		// Add override to change texture depending on fuseState, see json
		if (!Motherlode.proxy.isDedicatedServer()) {
			this.addPropertyOverride(new ResourceLocation("fuseState"), new ItemBomb.ItemBombAnimation());
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
				EntityDynamite entityDynamite = new EntityDynamite(worldIn, playerIn);
				entityDynamite.setFuseTicksAlive(stack.getItemDamage());
				entityDynamite.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.3F, 1.0F);
				worldIn.spawnEntity(entityDynamite);
			}
			stack.setItemDamage(0);
			stack.shrink(1);
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
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
}
