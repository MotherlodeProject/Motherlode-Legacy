package motherlode.registry;

import motherlode.Motherlode;
import motherlode.block.IModeledBlock;
import motherlode.item.IModeledItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Motherlode.MOD_ID)
public class MotherlodeRegistry {

	public static final List<Block> BLOCKS = new ArrayList<>();
	public static final List<IModeledBlock> BLOCKS_MARKED_FOR_MODELS = new ArrayList<>();
	public static final List<Item> ITEMS = new ArrayList<>();
	public static final List<IModeledItem> ITEMS_MARKED_FOR_MODELS = new ArrayList<>();
	public static final List<Biome> BIOMES = new ArrayList<>();

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		for (Block block : BLOCKS) {
			event.getRegistry().register(block);
		}
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		for (Item item : ITEMS) {
			event.getRegistry().register(item);
		}
	}

	@SubscribeEvent
	public static void registerBiomes(RegistryEvent.Register<Biome> event) {
		for (Biome biome : BIOMES) {
			event.getRegistry().register(biome);
		}
	}
}
