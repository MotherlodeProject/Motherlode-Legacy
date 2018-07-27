package motherlode.recipe;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import contrivitive.gui.element.sprite.ItemStackSprite;
import contrivitive.gui.element.sprite.Sprite;
import motherlode.Motherlode;
import motherlode.recipe.ingredient.IIngredient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.registries.IForgeRegistryEntry;

/**
 * Represents a recipe.
 * @author Shadows
 *
 */
public interface IMotherlodeRecipe extends IForgeRegistryEntry<IMotherlodeRecipe> {

	/**
	 * @return The output stack of this recipe.  This is not copied during return, so DO NOT modify the return from this method.
	 */
	public ItemStack getOutput();

	/**
	 * @return The list of ingredients for this recipe.  This list should be immutable.
	 */
	public List<IIngredient> getInputs();

	/**
	 * @return The table required for this recipe, or null, if no table is required.
	 */
	@Nullable
	public IRecipeTable getRecipeTable();

	/**
	 * If this recipe can craft in the given context.
	 * @return A map of ingredients to matched stacks to be shrunk in onCrafted, or null, if this recipe cannot be crafted.  Arrays must not be empty, null, or contain empty stacks.
	 * @param stacks Stacks that are available for crafting.  DO NOT mutate any of these at this step.  These stacks will be all of those in the crafting container, and can be empty.
	 * @param player The crafting player.
	 * @param table The nearby crafting tables.  If you require a specific table (other than {@link RecipeTable.NONE}) check with these.  May be empty, but not null.
	 */
	@Nullable
	public Map<IIngredient, ItemStack[]> matches(NonNullList<ItemStack> stacks, EntityPlayer player, List<IRecipeTable> tables);

	/**
	 * The callback for when crafting actually happens.  Stacks should be shrunk in this method.
	 * @param stacks Stacks that are available for crafting.
	 * @param matched The matched stacks, as returned by matches.
	 * @param player The crafting player.
	 * @param table The nearby crafting tables.  If you require a specific table (other than {@link RecipeTable.NONE}) check with these.  May be empty, but not null.
	 */
	public default void onCraft(NonNullList<ItemStack> stacks, Map<IIngredient, ItemStack[]> matched, EntityPlayer player, List<IRecipeTable> tables) {
		for (Map.Entry<IIngredient, ItemStack[]> e : matched.entrySet()) {
			int needed = e.getKey().getCount();
			for (ItemStack s : e.getValue()) {
				int used = Math.min(needed, s.getCount());
				s.shrink(used);
				needed -= used;
			}
			if (needed != 0) Motherlode.LOGGER.error("An error has occured during crafting: Ingredient {} was unable to consume as many stacks as it needed.  Recipe: {}", e.getKey(), this.getRegistryName());
		}
	}

	/**
	 * @return Extra strings to draw on the output stack.  Ignored if empty.  May not be null.
	 */
	public default List<String> getRecipeTooltip() {
		return Collections.emptyList();
	}

	/**
	 * @return The sprite to draw in the output slot.
	 */
	public default Sprite getSprite() {
		return new ItemStackSprite(getOutput());
	}

	@Override
	default Class<IMotherlodeRecipe> getRegistryType() {
		return IMotherlodeRecipe.class;
	}

}
