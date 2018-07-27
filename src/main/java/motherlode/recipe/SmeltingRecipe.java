package motherlode.recipe;

import motherlode.recipe.ingredient.IIngredient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class SmeltingRecipe implements IMotherlodeRecipe {
	IRecipeTable table;
	ResourceLocation registryName;
	ItemStack output;
	List<IIngredient> inputs;

	public SmeltingRecipe(ResourceLocation registryName, ItemStack output, IIngredient input) {
		this(RecipeTable.FURNACE, registryName, output, input);
	}

	@SuppressWarnings("unchecked")
	public SmeltingRecipe(IRecipeTable table, ResourceLocation registryName, ItemStack output, IIngredient input) {
		this.table = table;
		this.registryName = registryName;
		this.output = output;
		this.inputs = new ArrayList<>();
		this.inputs.add(input);
	}

	public int getProcessingTime() {
		return 200;
	}

	@Override
	public ItemStack getOutput() {
		return output;
	}

	@Override
	public List<IIngredient> getInputs() {
		return inputs;
	}

	@Override
	public ResourceLocation getRegistryName() {
		return registryName;
	}

	@Override
	public IRecipeTable getRecipeTable() {
		return table;
	}
}
