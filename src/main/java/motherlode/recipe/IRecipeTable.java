package motherlode.recipe;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;

/**
 * Represents a Recipe Table.  A Recipe table is a block which when nearby the player, may unlock additional recipes in the crafting gui.
 * @author Shadows
 *
 */
public interface IRecipeTable {

	/**
	 * The state that represents this table.  May be null.
	 */
	@Nullable
	public IBlockState getState();
}
