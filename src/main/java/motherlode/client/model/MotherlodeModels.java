package motherlode.client.model;

import motherlode.block.IModeledBlock;
import motherlode.item.IModeledItem;
import motherlode.registry.MotherlodeRegistry;
import motherlode.util.ModelUtil;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
					List list = definition.itemVariantGetter.getItemVariants(new ArrayList<>());
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
		for (BlockModelDefinition definition : BLOCK_MODELS.values()) {
			Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(definition.iBlockColor, definition.block);
		}
		for (ItemModelDefinition definition : ITEM_MODELS.values()) {
			Minecraft.getMinecraft().getItemColors().registerItemColorHandler(definition.iItemColor, definition.item);
		}
	}

}
