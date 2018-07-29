package motherlode.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import motherlode.api.recipe.IIngredient;
import motherlode.api.recipe.IMotherlodeRecipe;
import motherlode.api.recipe.IRecipeTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.registries.IForgeRegistryEntry;

/**
 * The default implementation of a recipe.  All recipes in Motherlode are shapeless by nature.
 * @author Shadows
 */
public class MotherlodeRecipe extends IForgeRegistryEntry.Impl<IMotherlodeRecipe> implements IMotherlodeRecipe {

	ItemStack output;
	IRecipeTable table;
	List<IIngredient> inputs;

	/**
	 * Makes a recipe. 
	 * The ingredients should be unique, in that no IIngredient should match two of the same type of item.  Multiple required items should utilize count.
	 * Having multiple ingredients that could match the same stack will cause errors in this implementation of matches and onCraft.
	 * @param output The output stack.
	 * @param table The required table, or null, if no table is required.
	 * @param inputs The ingredients.
	 */
	public MotherlodeRecipe(ItemStack output, @Nullable IRecipeTable table, IIngredient... inputs) {
		Preconditions.checkArgument(!output.isEmpty() && output.getCount() <= 64, "Cannot create a MotherlodeRecipe with an empty stack or stack larger than 64 items!");
		Preconditions.checkArgument(inputs.length > 0, "Cannot create a MotherlodeRecipe with no inputs!");
		this.output = output;
		this.table = table;
		this.inputs = ImmutableList.<IIngredient>builder().add(inputs).build();
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
	public IRecipeTable getRecipeTable() {
		return table;
	}

	@Override
	public Map<IIngredient, ItemStack[]> matches(NonNullList<ItemStack> stacks, EntityPlayer player, List<IRecipeTable> tables) {

		if (table != null && !tables.contains(table)) return null;

		List<ItemStack> usable = new ArrayList<>(stacks.size());
		for (ItemStack s : stacks)
			if (!s.isEmpty()) usable.add(s);

		Map<IIngredient, ItemStack[]> matched = new HashMap<>(inputs.size());

		for (IIngredient i : inputs) {
			int needed = i.getCount();
			for (ItemStack s : usable) {
				if (needed > 0 && i.apply(s)) {
					needed -= s.getCount();
					if (!matched.containsKey(i)) matched.put(i, new ItemStack[] { s });
					else {
						ItemStack[] old = matched.get(i);
						ItemStack[] newArray = new ItemStack[old.length + 1];
						System.arraycopy(old, 0, newArray, 0, old.length);
						newArray[newArray.length - 1] = s;
						matched.put(i, newArray);
					}
				}
			}
			if (needed > 0) return null;
		}

		return matched;
	}
}
