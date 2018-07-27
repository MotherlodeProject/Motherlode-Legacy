package motherlode.recipe.ingredient;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import it.unimi.dsi.fastutil.ints.IntList;
import motherlode.recipe.IMotherlodeRecipe;
import net.minecraft.client.util.RecipeItemHelper;
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
	 * Returns a list of packed matching stacks, as created by {@link RecipeItemHelper#pack(ItemStack)}.  This list should be immutable.
	 * @return Matching stacks, packed, or null, if this recipe is not simple.  A single packed stack will have a count of one.
	 */
	@Nullable
	public IntList getMatchingStacksPacked();

	/**
	 * @return If this recipe is simple, determined by if all valid stacks can be packed.  For example, NBTIngredient is not simple.
	 */
	public boolean isSimple();
}
