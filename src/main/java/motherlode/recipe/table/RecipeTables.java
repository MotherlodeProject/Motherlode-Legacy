package motherlode.recipe.table;

import motherlode.Motherlode;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

/**
 * Default Recipe Tables.
 * @author Shadows
 *
 */
@EventBusSubscriber(modid = Motherlode.MOD_ID)
@ObjectHolder(Motherlode.MOD_ID)
public class RecipeTables {

	public static final IRecipeTable CRAFTING_TABLE = new RecipeTable(Blocks.CRAFTING_TABLE).setRegistryName(Motherlode.MOD_ID, "crafting_table");
	public static final IRecipeTable STONE = new MultiStateTable(Blocks.STONE).setRegistryName(Motherlode.MOD_ID, "stone");

	private static ForgeRegistry<IRecipeTable> tables;

	@SubscribeEvent
	public static void createRegistry(RegistryEvent.NewRegistry e) {
		tables = (ForgeRegistry<IRecipeTable>) new RegistryBuilder<IRecipeTable>().setName(new ResourceLocation(Motherlode.MOD_ID, "recipe_tables")).setType(IRecipeTable.class).disableSaving().create();
	}

	@SubscribeEvent
	public static void registerTables(RegistryEvent.Register<IRecipeTable> e) {
		e.getRegistry().registerAll(CRAFTING_TABLE, STONE);
	}

	public static ForgeRegistry<IRecipeTable> getRegistry() {
		return tables;
	}
}
