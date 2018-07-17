package motherlode.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class CraftingRecipe implements IMotherlodeRecipe {
	ItemStack output;
	List<Object> inputs;

	public CraftingRecipe() {
	}

	@Override
	public ItemStack getOutput() {
		return null;
	}

	@Override
	public List<Object> getInputs() {
		return null;
	}

	@Override
	public ResourceLocation getRegistryName() {
		return null;
	}

	@Override
	public IRecipeTable getRecipeTable() {
		return null;
	}
}
