package motherlode.block;

import motherlode.Motherlode;
import motherlode.item.MotherlodeItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = Motherlode.MOD_ID)
@GameRegistry.ObjectHolder(Motherlode.MOD_ID)
public class MotherlodeBlocks {

	//Util
	private static final Block DUMMY = Blocks.AIR;
	private static RegistryEvent.Register<Block> event;
	private static List<ItemBlock> itemBlocks = new ArrayList<>();

	//Ore
	public static final Block AMETHYST_ORE = DUMMY;
	public static final Block BONE_ORE = DUMMY;
	public static final Block COPPER_ORE = DUMMY;
	public static final Block NETHERITE_ORE = DUMMY;
	public static final Block PLATINUM_ORE = DUMMY;
	public static final Block RUBY_ORE = DUMMY;
	public static final Block SALTPETER_ORE = DUMMY;
	public static final Block SAPPHIRE_ORE = DUMMY;
	public static final Block SILVER_ORE = DUMMY;
	public static final Block SULPHUR_ORE = DUMMY;
	public static final Block TITANIUM_ORE = DUMMY;
	public static final Block TOPAZ_ORE = DUMMY;

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> e) {
		event = e;

		register(new BlockMotherlodeOre("amethyst_ore", () -> MotherlodeItems.AMETHYST, 3F, 5F, 2, 3, 7));
		register(new BlockMotherlodeOre("bone_ore", () -> Items.BONE, 2F, 8F, 0, 0, 1).setDropQuantity(1, 3));
		register(new BlockMotherlodeOre("copper_ore", () -> MotherlodeItems.COPPER, 3F, 5F, 1, 1, 4));
		register(new BlockMotherlodeOre("netherite_ore", () -> MotherlodeItems.NETHERITE, 4F, 6F, 3, 2, 6));
		register(new BlockMotherlodeOre("platinum_ore", () -> MotherlodeItems.PLATINUM, 3F, 5F, 2, 2, 5));
		register(new BlockMotherlodeOre("ruby_ore", () -> MotherlodeItems.RUBY, 3F, 5F, 2, 3, 7));
		register(new BlockMotherlodeOre("saltpeter_ore", () -> MotherlodeItems.SALTPETER, 2.5F, 4F, 1, 0, 3).setDropQuantity(1, 2));
		register(new BlockMotherlodeOre("sapphire_ore", () -> MotherlodeItems.SAPPHIRE, 3F, 5F, 2, 3, 7));
		register(new BlockMotherlodeOre("silver_ore", () -> MotherlodeItems.SILVER, 3F, 5F, 2, 1, 4));
		register(new BlockMotherlodeOre("sulphur_ore", () -> MotherlodeItems.SULPHUR, 3.5F, 5F, 1, 3, 4));
		register(new BlockMotherlodeOre("titanium_ore", () -> MotherlodeItems.TITANIUM, 3F, 5F, 2, 1, 7));
		register(new BlockMotherlodeOre("topaz_ore", () -> MotherlodeItems.TOPAZ, 3F, 5F, 2, 3, 7));
	}

	private static void register(Block block) {
		register(block, true);
	}

	private static void register(Block block, boolean itemBlock) {
		event.getRegistry().register(block);
		if (itemBlock) {
			ItemBlock item = new ItemBlock(block);
			item.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
			itemBlocks.add(item);
		}
	}

	private static void register(Block block, ItemBlock itemBlock) {
		event.getRegistry().register(block);
		itemBlocks.add(itemBlock);
	}

	public static List<ItemBlock> getItemBlocks() {
		return itemBlocks;
	}
}
