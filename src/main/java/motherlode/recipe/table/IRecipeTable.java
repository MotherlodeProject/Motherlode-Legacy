package motherlode.recipe.table;

import com.google.common.base.Predicate;

import net.minecraft.block.state.IBlockState;
import net.minecraftforge.registries.IForgeRegistryEntry;

/**
 * Represents a Recipe Table.  A Recipe table is a block which when nearby the player, may unlock additional recipes in the crafting gui.
 * @author Shadows
 *
 */
public interface IRecipeTable extends Predicate<IBlockState>, IForgeRegistryEntry<IRecipeTable> {

	/**
	 * The state that best represents this table.
	 */
	public IBlockState getDisplayState();

	@Override
	default Class<IRecipeTable> getRegistryType() {
		return IRecipeTable.class;
	}
}
