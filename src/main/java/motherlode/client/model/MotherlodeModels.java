package motherlode.client.model;

import motherlode.block.IModeledBlock;
import motherlode.item.IModeledItem;
import motherlode.registry.MotherlodeRegistry;
import motherlode.util.ModelUtil;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.*;

@SideOnly(Side.CLIENT)
public class MotherlodeModels {

	public static final Map<Block, BlockModelDefinition> BLOCK_MODELS = new HashMap<>();
	public static final Map<Item, ItemModelDefinition> ITEM_MODELS = new HashMap<>();

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		for (IModeledBlock block : MotherlodeRegistry.BLOCKS_MARKED_FOR_MODELS) {
			BLOCK_MODELS.put((Block) block, block.getBlockModelDefinition());
			ItemModelDefinition itemModelDefinition = block.getItemModelDefinition();
			if (itemModelDefinition != null) {
				itemModelDefinition.item = Item.getItemFromBlock((Block) block);
				ITEM_MODELS.put(itemModelDefinition.item, itemModelDefinition);
			}
		}
		for (IModeledItem item : MotherlodeRegistry.ITEMS_MARKED_FOR_MODELS) {
			ITEM_MODELS.put((Item) item, item.getItemModelDefinition());
		}

		for (BlockModelDefinition definition : BLOCK_MODELS.values()) {
			ModelLoader.setCustomStateMapper(definition.block, definition);
		}
		for (ItemModelDefinition definition : ITEM_MODELS.values()) {
			for (int i = 0; i <= definition.maxMeta; i++) {
				if (definition.meshDefinition != null) {
					ModelLoader.setCustomMeshDefinition(definition.item, definition.meshDefinition);
					List<ModelResourceLocation> list = new ArrayList<>();
					definition.itemVariantGetter.addItemVariants(list);
					ModelResourceLocation[] array = new ModelResourceLocation[list.size()];
					//noinspection SuspiciousToArrayCall
					list.toArray(array);
					ModelLoader.registerItemVariants(definition.item, array);
				} else {
					if (definition instanceof ItemBlockModelDefinition) {
						ModelLoader.setCustomModelResourceLocation(definition.item, i, new ModelResourceLocation(definition.fileLocation, ModelUtil.addPrependsAndAppends(definition.variant.getVariantForMeta(i), ((ItemBlockModelDefinition) definition).prepends, ((ItemBlockModelDefinition) definition).appends)));
					} else {
						ModelLoader.setCustomModelResourceLocation(definition.item, i, new ModelResourceLocation(definition.fileLocation, definition.variant.getVariantForMeta(i)));
					}
				}
			}
		}
	}

	public static void registerColorHandlers() {
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, worldIn, pos, tintIndex) -> {
			int[] colors = new int[] { 0x7B5A11, 0x785B11, 0x755C12, 0x715E12, 0x6E5F13, 0x6A6013, 0x656014, 0x54561B, 0x3D3E1C, 0x383F28, 0x3D3E1C, 0x49571D, 0x506517, 0x4F6B17, 0x4F6B17, 0x4C6C17, 0x486D17, 0x456F18, 0x417018, 0x3E7119, 0x3A7319,
				0x3A7A1E, 0x43902C, 0x50A53A, 0x44962F, 0x328322, 0x267A1C, 0x227C1C, 0x1F7D1D, 0x1B7B1D, 0x197222 };
			if (tintIndex == 0) {
				Random rand = new Random(pos.getX() + pos.getZ() * 2141);
				return colors[rand.nextInt(colors.length)];
			}
			return 0xFFFFFF;
		}, Blocks.CACTUS);
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
			if (tintIndex == 0) {
				return 0x87B728;
			}
			return 0xFFFFFF;
		}, Blocks.CACTUS);

		for (BlockModelDefinition definition : BLOCK_MODELS.values()) {
			Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(definition.iBlockColor, definition.block);
		}
		for (ItemModelDefinition definition : ITEM_MODELS.values()) {
			Minecraft.getMinecraft().getItemColors().registerItemColorHandler(definition.iItemColor, definition.item);
		}
	}

}
