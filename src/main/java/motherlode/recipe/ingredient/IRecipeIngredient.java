package motherlode.recipe.ingredient;

import net.minecraft.item.ItemStack;

import java.util.List;

public interface IRecipeIngredient {
	public boolean isStackGreaterOrEqual(ItemStack stack);

	public List<ItemStack> getItemsForDisplay();

	public int getAmount();
}
