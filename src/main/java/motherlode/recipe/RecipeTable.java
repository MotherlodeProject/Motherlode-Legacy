package motherlode.recipe;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

/**
 * Default Recipe Tables.
 * @author Shadows
 *
 */
public enum RecipeTable implements IRecipeTable {

	CRAFTING_TABLE(Blocks.CRAFTING_TABLE);

	IBlockState state;

	RecipeTable(Block block) {
		this.state = block.getDefaultState();
	}

	RecipeTable(IBlockState state) {
		this.state = state;
	}

	@Override
	public IBlockState getDisplayState() {
		return state;
	}

	@Override
	public boolean apply(IBlockState input) {
		return input == state;
	}
}
