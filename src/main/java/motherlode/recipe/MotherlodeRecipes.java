package motherlode.recipe;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Pair;

import motherlode.Motherlode;
import motherlode.recipe.ingredient.IIngredient;
import motherlode.recipe.ingredient.OreIngredient;
import motherlode.recipe.ingredient.SimpleIngredient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

@EventBusSubscriber(modid = Motherlode.MOD_ID)
public class MotherlodeRecipes {

	private static ForgeRegistry<IMotherlodeRecipe> recipes;

	@SubscribeEvent
	public static void createRegistry(RegistryEvent.NewRegistry e) {
		recipes = (ForgeRegistry<IMotherlodeRecipe>) new RegistryBuilder<IMotherlodeRecipe>().setName(new ResourceLocation(Motherlode.MOD_ID, "recipes")).allowModification().setType(IMotherlodeRecipe.class).disableSaving().create();
	}

	@SubscribeEvent
	public static void addCraftingRecipes(RegistryEvent.Register<IMotherlodeRecipe> e) {
		addRecipe("oak_planks", new ItemStack(Blocks.PLANKS, 4, 0), new SimpleIngredient(Blocks.LOG, 1, 0));
		addRecipe("crafting_table", new ItemStack(Blocks.CRAFTING_TABLE), new SimpleIngredient(Blocks.PLANKS, 4, 0));
		addRecipe("sticks", new ItemStack(Items.STICK, 4), new OreIngredient("plankWood", 2));
		addRecipe("wooden_pressure_plate", new ItemStack(Blocks.WOODEN_PRESSURE_PLATE), new OreIngredient("plankWood", 1));
		addRecipe("wooden_button", new ItemStack(Blocks.WOODEN_BUTTON), new OreIngredient("plankWood", 2));
		addRecipe("torch", new ItemStack(Blocks.TORCH, 4), new SimpleIngredient(Items.COAL, 1, OreDictionary.WILDCARD_VALUE), new OreIngredient("stickWood"));
		addRecipe("stone_bricks", new ItemStack(Blocks.STONEBRICK, 4), new SimpleIngredient(Blocks.STONE, 4));
		addRecipe("stone_bricks_slab", new ItemStack(Blocks.STONE_SLAB, 2, 5), new SimpleIngredient(Blocks.STONEBRICK, 1));
		addRecipe("stone_pressure_plate", new ItemStack(Blocks.STONE_PRESSURE_PLATE), new SimpleIngredient(Blocks.STONE, 2));
		addRecipe("stone_button", new ItemStack(Blocks.STONE_BUTTON), new SimpleIngredient(Blocks.STONE, 1));
	}

	private static void addRecipe(String name, ItemStack output, IIngredient... input) {
		recipes.register(new DefaultRecipe(output, null, input).setRegistryName(Motherlode.MOD_ID, name));
	}

	@Nullable
	public static Pair<IMotherlodeRecipe, Map<IIngredient, ItemStack[]>> findMatchingRecipe(NonNullList<ItemStack> stacks, EntityPlayer player, List<IRecipeTable> tables) {
		for (IMotherlodeRecipe r : recipes) {
			Map<IIngredient, ItemStack[]> m = r.matches(stacks, player, tables);
			if (m != null) return Pair.of(r, m);
		}
		return null;
	}

	public static ForgeRegistry<IMotherlodeRecipe> getRecipeRegistry() {
		return recipes;
	}

}
