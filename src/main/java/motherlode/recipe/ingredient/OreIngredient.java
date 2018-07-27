package motherlode.recipe.ingredient;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntLists;
import net.minecraft.client.util.RecipeItemHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

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
		return matches.contains(RecipeItemHelper.pack(input)) || matches.contains(Item.REGISTRY.getIDForObject(input.getItem()) << 16 | OreDictionary.WILDCARD_VALUE & 65535);
	}

	@Override
	public List<ItemStack> getDisplayStacks() {
		NonNullList<ItemStack> stacks = NonNullList.create();
		for (ItemStack stack : OreDictionary.getOres(ore, false)) {
			if (stack.getMetadata() == OreDictionary.WILDCARD_VALUE) {
				stack.getItem().getSubItems(CreativeTabs.SEARCH, stacks);
			} else {
				stacks.add(stack);
			}
		}
		if (display == null) display = ImmutableList.copyOf(stacks);
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
