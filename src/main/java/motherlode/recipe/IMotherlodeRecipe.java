package motherlode.recipe;

import java.util.Collections;
import java.util.List;

import contrivitive.gui.element.sprite.ItemStackSprite;
import contrivitive.gui.element.sprite.Sprite;
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
	 * @return The table required for this recipe.
	 */
	public IRecipeTable getRecipeTable();

	/**
	 * @return If this recipe can craft in the given context.
	 * @param stacks Stacks that are available for crafting.  DO NOT mutate any of these at this step.
	 * @param player The crafting player.
	 * @param table The nearby crafting tables.  If you require a specific table (other than {@link RecipeTable.NONE}) check with these.  May be empty, but not null.
	 */
	public boolean matches(NonNullList<ItemStack> stacks, EntityPlayer player, List<IRecipeTable> tables);

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
