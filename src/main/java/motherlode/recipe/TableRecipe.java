package motherlode.recipe;

import motherlode.recipe.ingredient.IIngredient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import scala.actors.threadpool.Arrays;

import java.util.List;

public class TableRecipe implements IMotherlodeRecipe {
	IRecipeTable table;
	ResourceLocation registryName;
	ItemStack output;
	List<IIngredient> inputs;

	@SuppressWarnings("unchecked")
	public TableRecipe(IRecipeTable table, ResourceLocation registryName, ItemStack output, IIngredient... inputs) {
		this.table = table;
		this.registryName = registryName;
		this.output = output;
		this.inputs = Arrays.asList(inputs);
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
