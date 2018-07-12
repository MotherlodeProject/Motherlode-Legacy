package motherlode.item;

import motherlode.Motherlode;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.List;

@Mod.EventBusSubscriber(modid = Motherlode.MOD_ID)
public class ItemWitherSword extends ItemMotherlodeSword {

	private static final PotionEffect WITHER_EFFECT = new PotionEffect(MobEffects.WITHER, 200);

	public ItemWitherSword() {
		super("wither_sword", ToolMaterial.STONE);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if (!(attacker instanceof EntityWitherSkeleton)) {
			target.addPotionEffect(new PotionEffect(WITHER_EFFECT.getPotion(), WITHER_EFFECT.getDuration()));
		}
		return super.hitEntity(stack, target, attacker);
	}

	@Override
	public void addInformation(ItemStack stack,
	                           @Nullable
		                           World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.RED + I18n.translateToLocal(WITHER_EFFECT.getEffectName()).trim() + " (" + Potion.getPotionDurationString(WITHER_EFFECT, 1F) + ")");
	}

	@SubscribeEvent
	public static void onEntitySpawn(EntityJoinWorldEvent event) {
		if (event.getEntity() instanceof EntityWitherSkeleton) {
			if (((EntityWitherSkeleton) event.getEntity()).getHeldItemMainhand().getItem().equals(Items.STONE_SWORD)) {
				((EntityWitherSkeleton) event.getEntity()).setHeldItem(EnumHand.MAIN_HAND, new ItemStack(MotherlodeItems.WITHER_SWORD));
			}
		}
	}
}
