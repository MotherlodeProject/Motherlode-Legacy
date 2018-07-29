package motherlode.recipe.table;

import motherlode.Motherlode;
import motherlode.api.recipe.IRecipeTable;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

/**
 * Default Recipe Tables.
 * @author Shadows
 */
@EventBusSubscriber(modid = Motherlode.MOD_ID)
@ObjectHolder(Motherlode.MOD_ID)
public class RecipeTables {

	public static final IRecipeTable CRAFTING_TABLE = new RecipeTable(Blocks.CRAFTING_TABLE).setRegistryName(Motherlode.MOD_ID, "crafting_table");
	public static final IRecipeTable STONE = new MultiStateTable(Blocks.STONE).setRegistryName(Motherlode.MOD_ID, "stone");

	@SubscribeEvent
	public static void registerTables(RegistryEvent.Register<IRecipeTable> e) {
		e.getRegistry().registerAll(CRAFTING_TABLE, STONE);
	}
}
