package motherlode.recipe.ingredient;

import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntLists;
import net.minecraft.block.Block;
import net.minecraft.client.util.RecipeItemHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * A simple Ingredient.  Matches only a single stack.
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

	/**
	 * The packed stacks, also just cached.
	 */
	IntList packed;

	public SimpleIngredient(Block block) {
		this(block, 0);
	}

	public SimpleIngredient(Block block, int meta) {
		this(Item.getItemFromBlock(block), meta);
	}

	public SimpleIngredient(Item item) {
		this(item, 0);
	}

	public SimpleIngredient(Item item, int meta) {
		this(new ItemStack(item, 1, meta));
	}

	public SimpleIngredient(ItemStack stack) {
		Preconditions.checkArgument(!stack.isEmpty(), "Cannot construct a SimpleIngredient with an empty stack!");
		this.stack = stack;
		display = ImmutableList.of(stack);
		packed = IntLists.singleton(RecipeItemHelper.pack(stack));
	}

	@Override
	public boolean apply(ItemStack input) {
		return stack.isItemEqual(input);
	}

	@Override
	public List<ItemStack> getDisplayStacks() {
		return display;
	}

	@Override
	public IntList getMatchingStacksPacked() {
		return packed;
	}

	@Override
	public boolean isSimple() {
		return true;
	}

}
