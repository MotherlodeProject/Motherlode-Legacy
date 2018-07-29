package motherlode.recipe.table;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;

/**
 * A recipe table that supports multiple states.
 * @author Shadows
 */
public class MultiStateTable extends RecipeTable {

	final ImmutableList<IBlockState> validStates;

	/**
	 * Creates a recipe table using all valid states of the block.
	 */
	public MultiStateTable(Block block) {
		super(block);
		validStates = block.getBlockState().getValidStates();
	}

	public MultiStateTable(IBlockState... states) {
		super(states[0]);
		validStates = ImmutableList.<IBlockState>builder().add(states).build();
	}

	@Override
	public boolean apply(IBlockState input) {
		return validStates.contains(input);
	}

}
