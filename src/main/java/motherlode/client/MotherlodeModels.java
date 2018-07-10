package motherlode.client;

import motherlode.Motherlode;
import motherlode.block.BlockPot;
import motherlode.block.MotherlodeBlocks;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Motherlode.MOD_ID)
public class MotherlodeModels {

	public static final List<ModelCompound> MODELS = new ArrayList<>();

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {

		for (ModelCompound compound : MODELS) {
			if (compound.isBlock()) {
				if (compound.getFileName().equals("shootingstar.undefinedfilename")) {
					//Checks if block has an item
					if (Item.getItemFromBlock(compound.getBlock()) != Items.AIR && compound.shouldRegisterItemModel()) {
						ModelMethods.registerItemModel(compound.getItem(), compound.getMeta(), compound.getBlockStatePath(), compound.getInventoryVariant());
					}
					if (shouldDoCustomVariant(compound)) {
						ModelMethods.setBlockStateMapper(compound.getBlock(), compound.getBlockStatePath(), compound.getVariant());
					} else {
						ModelMethods.setBlockStateMapper(compound.getBlock(), compound.getBlockStatePath(), compound.getIgnoreProperties());
					}
				} else {
					//Checks if block has an item
					if (Item.getItemFromBlock(compound.getBlock()) != Items.AIR && compound.shouldRegisterItemModel()) {
						ModelMethods.registerItemModel(compound.getItem(), compound.getMeta(), compound.getFileName(), compound.getBlockStatePath(), compound.getInventoryVariant());
					}
					if (shouldDoCustomVariant(compound)) {
						ModelMethods.setBlockStateMapper(compound.getBlock(), compound.getFileName(), compound.getBlockStatePath(), compound.getVariant());
					} else {
						ModelMethods.setBlockStateMapper(compound.getBlock(), compound.getFileName(), compound.getBlockStatePath(), compound.getIgnoreProperties());
					}
				}
			}
			if (compound.isItem()) {
				if (compound.getFileName().equals("shootingstar.undefinedfilename")) {
					ModelMethods.registerItemModel(compound.getItem(), compound.getMeta(), compound.getBlockStatePath(), compound.getInventoryVariant());
				} else {
					ModelMethods.registerItemModel(compound.getItem(), compound.getMeta(), compound.getFileName(), compound.getBlockStatePath(), compound.getInventoryVariant());
				}
			}
		}
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MotherlodeBlocks.POT), (stack -> {
			if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Pattern")) {
				return new ModelResourceLocation(Motherlode.MOD_ID + ":pot_item", "pattern=" + stack.getTagCompound().getInteger("Pattern"));
			}
			return new ModelResourceLocation(Motherlode.MOD_ID + ":pot_item", "pattern=random");
		}));
		for (Integer i : BlockPot.PATTERN.getAllowedValues()) {
			ModelLoader.registerItemVariants(Item.getItemFromBlock(MotherlodeBlocks.POT), new ModelResourceLocation(Motherlode.MOD_ID + ":pot_item", "pattern=" + i));
		}
		ModelLoader.registerItemVariants(Item.getItemFromBlock(MotherlodeBlocks.POT), new ModelResourceLocation(Motherlode.MOD_ID + ":pot_item", "pattern=random"));
	}

	private static boolean shouldDoCustomVariant(ModelCompound compound) {
		if (!compound.getVariant().equals("shootingstar.undefinedvariant")) {
			return true;
		} else {
			return false;
		}
	}
}
