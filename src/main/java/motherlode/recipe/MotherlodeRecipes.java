package motherlode.recipe;

import motherlode.Motherlode;
import motherlode.api.MotherlodeAPI;
import motherlode.api.recipe.IIngredient;
import motherlode.api.recipe.IMotherlodeRecipe;
import motherlode.api.recipe.IRecipeTable;
import motherlode.recipe.ingredient.NBTIngredient;
import motherlode.recipe.ingredient.OreIngredient;
import motherlode.recipe.ingredient.SimpleIngredient;
import motherlode.recipe.table.RecipeTables;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = Motherlode.MOD_ID)
public class MotherlodeRecipes {

    @SubscribeEvent
    public static void addCraftingRecipes(RegistryEvent.Register<IMotherlodeRecipe> e) {
        //Wood
        addRecipe("oak_planks", new ItemStack(Blocks.PLANKS, 4, 0), new SimpleIngredient(Blocks.LOG, 1, 0));
        addRecipe("spruce_planks", new ItemStack(Blocks.PLANKS, 4, 1), new SimpleIngredient(Blocks.LOG, 1, 1));
        addRecipe("birch_planks", new ItemStack(Blocks.PLANKS, 4, 2), new SimpleIngredient(Blocks.LOG, 1, 2));
        addRecipe("jungle_planks", new ItemStack(Blocks.PLANKS, 4, 3), new SimpleIngredient(Blocks.LOG, 1, 3));
        addRecipe("acacia_planks", new ItemStack(Blocks.PLANKS, 4, 4), new SimpleIngredient(Blocks.LOG2, 1, 0));
        addRecipe("dark_oak_planks", new ItemStack(Blocks.PLANKS, 4, 5), new SimpleIngredient(Blocks.LOG2, 1, 1));

        //Building Blocks
        addRecipe("glowstone_block", new ItemStack(Blocks.GLOWSTONE, 1), new SimpleIngredient(Items.GLOWSTONE_DUST, 4));
        addRecipe("snow_block", new ItemStack(Blocks.SNOW, 1), new SimpleIngredient(Items.SNOWBALL, 4));
        addRecipe("clay_block", new ItemStack(Blocks.CLAY, 1), new SimpleIngredient(Items.CLAY_BALL, 4));
        addRecipe("brick_block", new ItemStack(Blocks.BRICK_BLOCK, 1), new SimpleIngredient(Items.BRICK, 4));
        addRecipe("sandstone", new ItemStack(Blocks.SANDSTONE, 1, 0), new SimpleIngredient(Blocks.SAND, 4));
        addRecipe("chiseled_sandstone", new ItemStack(Blocks.SANDSTONE, 1, 1), new SimpleIngredient(Blocks.STONE_SLAB, 2, 1));
        addRecipe("smooth_sandstone", new ItemStack(Blocks.SANDSTONE, 1, 2), new SimpleIngredient(Blocks.SANDSTONE, 4, 0));
        addRecipe("stone_bricks", new ItemStack(Blocks.STONEBRICK, 4), new SimpleIngredient(Blocks.STONE, 4));
        addRecipe("nether_brick", new ItemStack(Blocks.NETHER_BRICK, 1), new SimpleIngredient(Items.NETHERBRICK, 4));

        //Slabs
        //addRecipe("stone_bricks_slab", new ItemStack(Blocks.STONE_SLAB, 2, 5), new SimpleIngredient(Blocks.STONEBRICK, 1));

        //Utility
        addRecipe("crafting_table", new ItemStack(Blocks.CRAFTING_TABLE), new OreIngredient("plankWood", 4));
        addRecipe("torch", new ItemStack(Blocks.TORCH, 4), new SimpleIngredient(Items.COAL, 1, OreDictionary.WILDCARD_VALUE), new OreIngredient("stickWood"));
        addRecipe("jack-o-lantern", new ItemStack(Blocks.LIT_PUMPKIN, 1), new SimpleIngredient(Blocks.PUMPKIN, 1), new SimpleIngredient(Blocks.TORCH, 1));

        //Crafting Materials
        addRecipe("sticks", new ItemStack(Items.STICK, 4), new OreIngredient("plankWood", 2));

        //Redstone
        addRecipe("wooden_pressure_plate", new ItemStack(Blocks.WOODEN_PRESSURE_PLATE), new OreIngredient("plankWood", 2));
        addRecipe("wooden_button", new ItemStack(Blocks.WOODEN_BUTTON), new OreIngredient("plankWood", 1));
        addRecipe("stone_pressure_plate", new ItemStack(Blocks.STONE_PRESSURE_PLATE), new SimpleIngredient(Blocks.STONE, 2));
        addRecipe("stone_button", new ItemStack(Blocks.STONE_BUTTON), new SimpleIngredient(Blocks.STONE, 1));

        //Test
        addRecipe("test1", new ItemStack(Items.NETHER_STAR), RecipeTables.CRAFTING_TABLE, new OreIngredient("oreDiamond", 6));
        addRecipe("armor_test", new ItemStack(Items.IRON_HELMET), RecipeTables.ANVIL, new SimpleIngredient(Items.IRON_INGOT, 10));

        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("beef", "soup");
        addRecipe("test2", new ItemStack(Items.BEEF), RecipeTables.CRAFTING_TABLE, new NBTIngredient(Items.STICK, tag, false));
        addRecipe("test3", new ItemStack(Items.COOKED_BEEF), RecipeTables.CRAFTING_TABLE, new NBTIngredient(Items.STICK, tag, true));

        tag = new NBTTagCompound();
        tag.setString("chicken", "true");
        addRecipe("test4", new ItemStack(Items.CHICKEN), RecipeTables.STONE, new NBTIngredient(Items.MAGMA_CREAM, tag, false), new OreIngredient("blockEmerald", 80));
        addRecipe("test5", new ItemStack(Items.COOKED_CHICKEN), RecipeTables.STONE, new NBTIngredient(Items.MAGMA_CREAM, tag, true), new OreIngredient("blockEmerald", 80));
        addRecipe("test6", new ItemStack(Items.QUARTZ, 64), RecipeTables.STONE, new OreIngredient("blockDiamond", 128), new SimpleIngredient(Items.STICK, 630));
        addRecipe("test7", new ItemStack(Items.ACACIA_BOAT), new SimpleIngredient(Items.WATER_BUCKET, 4));
    }

    private static void addRecipe(String name, ItemStack output, IIngredient... input) {
        MotherlodeAPI.getRecipeRegistry().register(new MotherlodeRecipe(output, null, input).setRegistryName(Motherlode.MOD_ID, name));
    }

    private static void addRecipe(String name, ItemStack output, IRecipeTable table, IIngredient... input) {
        MotherlodeAPI.getRecipeRegistry().register(new MotherlodeRecipe(output, table, input).setRegistryName(Motherlode.MOD_ID, name));
    }

    public static List<IMotherlodeRecipe> getAllPossibleRecipes(NonNullList<ItemStack> stacks, EntityPlayer player, List<IRecipeTable> tables) {
        List<IMotherlodeRecipe> matches = new ArrayList<>();
        for (IMotherlodeRecipe recipe : MotherlodeAPI.getRecipeRegistry())
            if (recipe.matches(stacks, player, tables) != null) matches.add(recipe);
        return matches;
    }

    /**
     * Gets all valid {@link IRecipeTable}s near a player.  Might be laggy?  Needs testing.
     * No lag on client side, server side testing required.
     */
    public static List<IRecipeTable> getTables(EntityPlayer player) {
        BlockPos pos = player.getPosition();
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        List<IBlockState> states = new ArrayList<>(216);
        for (BlockPos p : BlockPos.getAllInBox(x - 3, y - 3, z - 3, x + 3, y + 3, z + 3)) {
            states.add(player.world.getBlockState(p));
        }

        List<IRecipeTable> tables = new ArrayList<>();
        for (IRecipeTable t : MotherlodeAPI.getTableRegistry()) {
            for (IBlockState state : states) {
                if (t.apply(state)) {
                    tables.add(t);
                    break;
                }
            }
        }
        return tables;
    }

}
