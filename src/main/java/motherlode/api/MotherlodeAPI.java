package motherlode.api;

import motherlode.Motherlode;
import motherlode.api.recipe.IMotherlodeRecipe;
import motherlode.api.recipe.IRecipeTable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.RegistryManager;

/**
 * Main entry point to add things to Motherlode without messing with internal classes.
 * @author Shadows
 *
 */
public class MotherlodeAPI {

	/**
	 * The API version.  If this changes, the API has changed.  API changes should be documented.
	 */
	public static final String API_VERSION = "1.0.0";

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
