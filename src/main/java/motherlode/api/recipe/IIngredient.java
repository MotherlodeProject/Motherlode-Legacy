package motherlode.api.recipe;

import java.util.List;

import com.google.common.base.Predicate;

import net.minecraft.item.ItemStack;

/**
 * Represents an Ingredient to be used in a {@link IMotherlodeRecipe}
 * @author Shadows
 *
 */
public interface IIngredient extends Predicate<ItemStack> {

	/**
	 * @return The stacks to rotate on display for this Ingredient.  These stacks should NEVER be mutated.
	 */
	public List<ItemStack> getDisplayStacks();

	/**
	 * @return The amount the matched stack will be shrunk by during crafting.
	 */
	public int getCount();

	/**
	 * Tests if this stack matches this ingredient.  Count should not be checked here, but should be left to the recipe logic to deal with.
	 */
	public boolean apply(ItemStack input);
}
