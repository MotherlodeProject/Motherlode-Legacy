package motherlode.recipe.ingredient;

import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import motherlode.api.recipe.IIngredient;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

/**
 * A simple Ingredient.  Matches only a single stack.  Supports count, meta, and wildcard meta.  Ignores NBT.
 * @author Shadows
 *
 */
public class SimpleIngredient implements IIngredient {

	/**
	 * The matching stack.
	 */
	ItemStack stack;

	/**
	 * The display, just a cached list.
	 */
	List<ItemStack> display;

	public SimpleIngredient(Block block) {
		this(block, 0);
	}

	public SimpleIngredient(Block block, int count) {
		this(block, count, 0);
	}

	public SimpleIngredient(Block block, int count, int meta) {
		this(new ItemStack(block, count, meta));
	}

	public SimpleIngredient(Item item) {
		this(item, 0);
	}

	public SimpleIngredient(Item item, int count) {
		this(item, count, 0);
	}

	public SimpleIngredient(Item item, int count, int meta) {
		this(new ItemStack(item, count, meta));
	}

	public SimpleIngredient(ItemStack stack) {
		Preconditions.checkArgument(!stack.isEmpty(), "Cannot construct a SimpleIngredient with an empty stack!");
		this.stack = stack;
		if (stack.getMetadata() == OreDictionary.WILDCARD_VALUE) {
			NonNullList<ItemStack> stacks = NonNullList.create();
			stack.getItem().getSubItems(CreativeTabs.SEARCH, stacks);
			display = ImmutableList.copyOf(stacks);
		} else display = ImmutableList.of(stack);
	}

	@Override
	public boolean apply(ItemStack input) {
		return stack.getItem() == input.getItem() && (stack.getMetadata() == OreDictionary.WILDCARD_VALUE || stack.getMetadata() == input.getMetadata());
	}

	@Override
	public List<ItemStack> getDisplayStacks() {
		return display;
	}

	@Override
	public int getCount() {
		return stack.getCount();
	}

	@Override
	public String toString() {
		return "SimpleIngredient - Stack: " + stack;
	}

}
