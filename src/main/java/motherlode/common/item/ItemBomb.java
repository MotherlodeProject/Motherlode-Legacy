package motherlode.common.item;

import motherlode.MotherlodeMod;
import motherlode.common.entity.EntityBomb;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.item.Item;

public class ItemBomb extends Item {
	
	public static final int FUSE_TICKS = 50;
	public static final float EXPLOSION_STRENGTH = 1.5F;
	
	public ItemBomb() {
		this.setMaxStackSize(10);
		this.setHasSubtypes(false);
		this.setMaxDamage(FUSE_TICKS);
		this.setCreativeTab(CreativeTabs.MISC);
		
		// Add override to change texture depending on fuseState, see json
//		if (!MotherlodeMod.proxy.isDedicatedServer()) {
//			this.addPropertyOverride(new ResourceLocation("fuseState"), new ItemBombAnimation());
//		}
	}
	
	@Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		if (stack.getTagCompound() == null) {
			stack.setTagCompound(new NBTTagCompound());
		}
		stack.getTagCompound().setBoolean("isLit", false);
	}

     // Light the fuse on first right click, throw the bomb on second right click
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
    		stack.setItemDamage(0);
    		stack.shrink(1);

    		if (!worldIn.isRemote) {
                EntityBomb entitybomb = new EntityBomb(worldIn, playerIn);
                entitybomb.setRotationYaw(playerIn.rotationYaw);
                entitybomb.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.3F, 1.0F);
                worldIn.spawnEntity(entitybomb);
            }
    	}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    }
    
	// Item damage acts as the fuse's timer, takes 1 dmg/tick, until the bomb explodes in player's hand
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
