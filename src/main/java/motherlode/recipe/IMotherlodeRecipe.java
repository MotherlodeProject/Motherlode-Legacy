package motherlode.recipe;

import contrivitive.gui.element.sprite.ItemStackSprite;
import contrivitive.gui.element.sprite.Sprite;
import motherlode.recipe.ingredient.IRecipeIngredient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public interface IMotherlodeRecipe {
	public ItemStack getOutput();

	public List<IRecipeIngredient> getInputs();

	public ResourceLocation getRegistryName();

	public IRecipeTable getRecipeTable();

	public default boolean canCraft(EntityPlayer player) {
		return true;
	}

	/**
	 * @return tooltip. If null or empty, the output ItemStack's tooltip will be used.
	 */
	public default List<String> getRecipeTooltip() {
		return null;
	}

	/**
	 * @return these lines will be added to the end of the recipe tooltip.
	 */
	public default List<String> getRecipeTooltipAdditions() {
		return new ArrayList<>();
	}

	public default Sprite getSprite() {
		return new ItemStackSprite(getOutput());
	}

}
