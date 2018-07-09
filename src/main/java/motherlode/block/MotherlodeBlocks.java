package motherlode.block;

import motherlode.item.MotherlodeItems;
import motherlode.registry.MotherlodeRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;

import java.util.Objects;

public class MotherlodeBlocks {

	public static final Block AMETHYST_ORE = register(new BlockMotherlodeOre("amethyst", MotherlodeItems.AMETHYST, 3F, 5F, 2, 3, 7));
	public static final Block BONE_ORE = register(new BlockMotherlodeOre("bone", Items.BONE, 2F, 8F, 0, 0, 1).setDropQuantity(1, 3));
	public static final Block COPPER_ORE = register(new BlockMotherlodeOre("copper", MotherlodeItems.COPPER, 3F, 5F, 1, 1, 4));
	public static final Block NETHERITE_ORE = register(new BlockMotherlodeOre("netherite", MotherlodeItems.NETHERITE, 4F, 6F, 3, 2, 6));
	public static final Block PLATINUM_ORE = register(new BlockMotherlodeOre("platinum", MotherlodeItems.PLATINUM, 3F, 5F, 2, 2, 5));
	public static final Block RUBY_ORE = register(new BlockMotherlodeOre("ruby", MotherlodeItems.RUBY, 3F, 5F, 2, 3, 7));
	public static final Block SALTPETER_ORE = register(new BlockMotherlodeOre("saltpeter", MotherlodeItems.SALTPETER, 2.5F, 4F, 1, 0, 3).setDropQuantity(1, 2));
	public static final Block SAPPHIRE_ORE = register(new BlockMotherlodeOre("sapphire", MotherlodeItems.SAPPHIRE, 3F, 5F, 2, 3, 7));
	public static final Block SILVER_ORE = register(new BlockMotherlodeOre("silver", MotherlodeItems.SILVER, 3F, 5F, 2, 1, 4));
	public static final Block SULPHUR_ORE = register(new BlockMotherlodeOre("sulphur", MotherlodeItems.SULPHUR, 3.5F, 5F, 1, 3, 4));
	public static final Block TITANIUM_ORE = register(new BlockMotherlodeOre("titanium", MotherlodeItems.TITANIUM, 3F, 5F, 2, 1, 7));
	public static final Block TOPAZ_ORE = register(new BlockMotherlodeOre("topaz", MotherlodeItems.TOPAZ, 3F, 5F, 2, 3, 7));

	private static Block register(Block block) {
		return register(block, true);
	}

	private static Block register(Block block, boolean itemBlock) {
		MotherlodeRegistry.BLOCKS.add(block);
		if (itemBlock) {
			ItemBlock item = new ItemBlock(block);
			item.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
			MotherlodeRegistry.ITEMS.add(item);
		}
		return block;
	}

	private static Block register(Block block, ItemBlock itemBlock) {
		MotherlodeRegistry.BLOCKS.add(block);
		MotherlodeRegistry.ITEMS.add(itemBlock);
		return block;
	}

	public static void load() {}
}
