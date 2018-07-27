package motherlode.recipe;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

/**
 * Default Recipe Tables.
 * @author Shadows
 *
 */
public enum RecipeTable implements IRecipeTable {

	NONE,
	CRAFTING_TABLE(Blocks.CRAFTING_TABLE);

	IBlockState state;

	RecipeTable() {
		state = null;
	}

	RecipeTable(Block block) {
		this.state = block.getDefaultState();
	}

	RecipeTable(IBlockState state) {
		this.state = state;
	}

	@Override
	@Nullable
	public IBlockState getState() {
		return state;
	}
}
