package motherlode.recipe.table;

import java.util.List;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;

public class MultiStateTable extends RecipeTable {

	List<IBlockState> validStates;

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
