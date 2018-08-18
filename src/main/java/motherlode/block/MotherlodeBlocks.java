package motherlode.block;

import motherlode.item.ItemLantern;
import motherlode.item.ItemPot;
import motherlode.item.MotherlodeItems;
import motherlode.registry.MotherlodeRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemColored;
import net.minecraft.item.ItemSlab;

import java.util.Objects;

public class MotherlodeBlocks {
	private static final String STONE = "stone";

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

	public static final Block POT;
	public static final Block LANTERN;

	public static final Block THORNS = register(new BlockThorns("thorns"));

	public static final Block THICK_VINE = register(new BlockThickVine(false));
	public static final Block FLOWERED_THICK_VINE;
	public static final Block ROPE = register(new BlockHangingClimbable("rope", Material.CARPET));
	public static final Block OAK_WOOD_PLATFORM = register(new BlockPlatform("oak_wood"));
	public static final Block OAK_WOOD_PLATFORM_STEP = register(new BlockPlatformStep("oak_wood"));

	public static final Block POLISHED_GRANITE_SLAB = registerSlab(Blocks.STONE, "polished_granite", STONE);
	public static final Block POLISHED_GRANITE_STAIRS = registerStairs("polished_granite", Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE_SMOOTH), STONE);
	public static final Block POLISHED_DIORITE_SLAB = registerSlab(Blocks.STONE, "polished_diorite", STONE);
	public static final Block POLISHED_DIORITE_STAIRS = registerStairs("polished_diorite", Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE_SMOOTH), STONE);
	public static final Block POLISHED_ANDESITE_SLAB = registerSlab(Blocks.STONE, "polished_andesite", STONE);
	public static final Block POLISHED_ANDESITE_STAIRS = registerStairs("polished_andesite", Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE_SMOOTH), STONE);
	public static final Block MOSSY_COBBLESTONE_SLAB = registerSlab(Blocks.MOSSY_COBBLESTONE, "mossy_cobblestone", STONE);
	public static final Block MOSSY_COBBLESTONE_STAIRS = registerStairs(Blocks.MOSSY_COBBLESTONE.getDefaultState(), STONE);
	public static final Block MOSSY_STONE_BRICKS_SLAB = registerSlab(Blocks.STONEBRICK, "mossy_stone_bricks", STONE);
	public static final Block MOSSY_STONE_BRICKS_STAIRS = registerStairs("mossy_stone_bricks", Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.MOSSY), STONE);
	public static final Block CRACKED_STONE_BRICKS_SLAB = registerSlab(Blocks.STONEBRICK, "cracked_stone_bricks", STONE);
	public static final Block CRACKED_STONE_BRICKS_STAIRS = registerStairs("cracked_stone_bricks", Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.CRACKED), STONE);

	public static final Block POLISHED_STONE = register(new BlockMotherlodeSimple("polished_stone", STONE, Material.ROCK));
	public static final Block POLISHED_STONE_SLAB = registerSlab(POLISHED_STONE, STONE);
	public static final Block POLISHED_STONE_STAIRS = registerStairs(POLISHED_STONE.getDefaultState(), STONE);
	public static final Block CHISELED_STONE_FACE = register(new BlockMotherlodeSimple("chiseled_stone_face", STONE, Material.ROCK));
	public static final Block CHISELED_STONE_WITHER = register(new BlockMotherlodeSimple("chiseled_stone_wither", STONE, Material.ROCK));
	public static final Block STONE_TILES = register(new BlockMotherlodeSimple("stone_tiles", STONE, Material.ROCK));
	public static final Block STONE_TILES_SLAB = registerSlab(STONE_TILES, STONE);
	public static final Block STONE_TILES_STAIRS = registerStairs(STONE_TILES.getDefaultState(), STONE);
	public static final Block SMALL_STONE_TILES = register(new BlockMotherlodeSimple("small_stone_tiles", STONE, Material.ROCK));
	public static final Block SMALL_STONE_TILES_SLAB = registerSlab(SMALL_STONE_TILES, STONE);
	public static final Block SMALL_STONE_TILES_STAIRS = registerStairs(SMALL_STONE_TILES.getDefaultState(), STONE);
	public static final Block SMALL_STONE_BRICKS = register(new BlockMotherlodeSimple("small_stone_bricks", STONE, Material.ROCK));
	public static final Block SMALL_STONE_BRICKS_SLAB = registerSlab(SMALL_STONE_BRICKS, STONE);
	public static final Block SMALL_STONE_BRICKS_STAIRS = registerStairs(SMALL_STONE_BRICKS.getDefaultState(), STONE);
	public static final Block MOSSY_SMALL_STONE_BRICKS = register(new BlockMotherlodeSimple("mossy_small_stone_bricks", STONE, Material.ROCK));
	public static final Block MOSSY_SMALL_STONE_BRICKS_SLAB = registerSlab(MOSSY_SMALL_STONE_BRICKS, STONE);
	public static final Block MOSSY_SMALL_STONE_BRICKS_STAIRS = registerStairs(MOSSY_SMALL_STONE_BRICKS.getDefaultState(), STONE);
	public static final Block GRANITE_BRICKS = register(new BlockMotherlodeSimple("granite_bricks", STONE, Material.ROCK));
	public static final Block GRANITE_BRICKS_SLAB = registerSlab(GRANITE_BRICKS, STONE);
	public static final Block GRANITE_BRICKS_STAIRS = registerStairs(GRANITE_BRICKS.getDefaultState(), STONE);
	public static final Block GRANITE_COBBLESTONE = register(new BlockMotherlodeSimple("granite_cobblestone", STONE, Material.ROCK));
	public static final Block GRANITE_COBBLESTONE_SLAB = registerSlab(GRANITE_COBBLESTONE, STONE);
	public static final Block GRANITE_COBBLESTONE_STAIRS = registerStairs(GRANITE_COBBLESTONE.getDefaultState(), STONE);

	static {
		POT = new BlockPot();
		register(POT, new ItemPot(POT));
		LANTERN = new BlockLantern();
		register(LANTERN, new ItemLantern(LANTERN));

		FLOWERED_THICK_VINE = new BlockThickVine(true);
		register(FLOWERED_THICK_VINE, new ItemColored(FLOWERED_THICK_VINE, true).setSubtypeNames(BlockThickVine.EnumFlower.getAllNames()));
	}

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
		if (block instanceof IModeledBlock) {
			MotherlodeRegistry.BLOCKS_MARKED_FOR_MODELS.add((IModeledBlock) block);
		}
		return block;
	}

	private static Block register(Block block, ItemBlock itemBlock) {
		MotherlodeRegistry.BLOCKS.add(block);
		itemBlock.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
		MotherlodeRegistry.ITEMS.add(itemBlock);
		if (block instanceof IModeledBlock) {
			MotherlodeRegistry.BLOCKS_MARKED_FOR_MODELS.add((IModeledBlock) block);
		}
		return block;
	}

	private static Block registerStairs(IBlockState state, String blockState) {
		return registerStairs(state.getBlock().getRegistryName().getResourcePath(), state, blockState);
	}

	private static Block registerStairs(String name, IBlockState state, String blockState) {
		return register(new BlockMotherlodeStairs(name, blockState, state));
	}

	private static Block registerSlab(Block block, String blockState) {
		return registerSlab(block, block.getRegistryName().getResourcePath(), blockState);
	}

	private static Block registerSlab(Block block, String name, String blockState) {
		BlockSlab halfSlab = new BlockMotherlodeSlab.Half(name, blockState, block);
		BlockSlab doubleSlab = new BlockMotherlodeSlab.Double(name, blockState, block, halfSlab);
		register(halfSlab, new ItemSlab(halfSlab, halfSlab, doubleSlab));
		register(doubleSlab, false);
		return halfSlab;
	}

	public static void load() {}
}
