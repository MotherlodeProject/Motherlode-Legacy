package motherlode.recipe.table;

import motherlode.api.recipe.IRecipeTable;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.registries.IForgeRegistryEntry;

/**
 * Simple recipe table, supports a single state.
 * @author Shadows
 */
public class RecipeTable extends IForgeRegistryEntry.Impl<IRecipeTable> implements IRecipeTable {

	IBlockState state;

	/**
	 * Creates a recipe table that matches this block's default state.  For all valid states, see {@link MultiStateTable}
	 */
	public RecipeTable(Block block) {
		this(block.getDefaultState());
	}

	public RecipeTable(IBlockState state) {
		this.state = state;
	}

	@Override
	public boolean apply(IBlockState input) {
		return input == state;
	}

	@Override
	public IBlockState getDisplayState() {
		return state;
	}

}
