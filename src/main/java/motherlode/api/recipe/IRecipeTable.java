package motherlode.api.recipe;

import com.google.common.base.Predicate;

import motherlode.recipe.table.RecipeTable;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistryEntry;

/**
 * Represents a Recipe Table.  Recipe Tables can unlock additional recipes when within 3 blocks of a player.<br>
 * Tables should be registered during the {@link RegistryEvent.Register}<br>
 * Default Implementation: {@link RecipeTable}
 * @author Shadows
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
