package motherlode.api;

import motherlode.Motherlode;
import motherlode.api.recipe.IMotherlodeRecipe;
import motherlode.api.recipe.IRecipeTable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.RegistryManager;

/**
 * Access point for certain things in Motherlode.
 * @author Shadows
 */
public class MotherlodeAPI {

	private static ForgeRegistry<IMotherlodeRecipe> recipeRegistry;
	private static ForgeRegistry<IRecipeTable> tableRegistry;

	/**
	 * @return The recipe registry.  May return null if invoked before the {@link RegistryEvent.NewRegistry}
	 */
	public static ForgeRegistry<IMotherlodeRecipe> getRecipeRegistry() {
		return recipeRegistry == null ? recipeRegistry = RegistryManager.ACTIVE.getRegistry(new ResourceLocation(Motherlode.MOD_ID, "recipes")) : recipeRegistry;
	}

	/**
	 * @return The table registry.  May return null if invoked before the {@link RegistryEvent.NewRegistry}
	 */
	public static ForgeRegistry<IRecipeTable> getTableRegistry() {
		return tableRegistry == null ? tableRegistry = RegistryManager.ACTIVE.getRegistry(new ResourceLocation(Motherlode.MOD_ID, "recipe_tables")) : tableRegistry;
	}
}
