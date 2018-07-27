package motherlode.recipe.ingredient;

import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntLists;
import net.minecraft.client.util.RecipeItemHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreIngredient implements IIngredient {

	/**
	 * The matching ore dict tag.
	 */
	String ore;

	/**
	 * The required amount of items.
	 */
	int count;

	/**
	 * Display stacks.  Not loaded until first invocation of getDisplayStacks.
	 */
	ImmutableList<ItemStack> display;

	/**
	 * Packed matches, since OreDict ignores NBT anyway.  Not loaded until first invocation of apply.
	 */
	IntList matches;

	public OreIngredient(String ore) {
		this(ore, 1);
	}

	public OreIngredient(String ore, int count) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(ore), "Cannot create an OreIngredient with a null or empty string!");
		this.ore = ore;
		this.count = count;
	}

	@Override
	public boolean apply(ItemStack input) {
		if (matches == null) {
			IntList i = new IntArrayList();
			for (ItemStack s : OreDictionary.getOres(ore))
				i.add(RecipeItemHelper.pack(s));
			matches = IntLists.unmodifiable(i);
		}
		return matches.contains(RecipeItemHelper.pack(input));
	}

	@Override
	public List<ItemStack> getDisplayStacks() {
		if (display == null) display = ImmutableList.copyOf(OreDictionary.getOres(ore, false));
		return display;
	}

	@Override
	public int getCount() {
		return count;
	}

	@Override
	public String toString() {
		return "OreIngredient - Ore: " + ore;
	}

}
