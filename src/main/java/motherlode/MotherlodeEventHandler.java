package motherlode;

import motherlode.api.recipe.IMotherlodeRecipe;
import motherlode.api.recipe.IRecipeTable;
import net.minecraft.block.BlockFire;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.RegistryBuilder;

@Mod.EventBusSubscriber(modid = Motherlode.MOD_ID)
public class MotherlodeEventHandler {

	private static MotherlodeWorldSavedData data;

	@SubscribeEvent
	public static void onBlockPlaceEvent(PlaceEvent event) {

		BlockPos blockPos = event.getPos();
		World worldIn = event.getWorld();
		data = MotherlodeWorldSavedData.get(worldIn);

		if (event.getPlacedBlock().getBlock() instanceof BlockFire && Blocks.PORTAL.trySpawnPortal(worldIn, blockPos) && !data.getNetherAccess()) {
			worldIn.setBlockToAir(blockPos); // Canceling the event still spawns the rest of the portal, but setting the block to air works fine
			event.getPlayer().sendMessage(new TextComponentString("You are not prepared"));
		}

		// Placeholder code to test the WorldSavedData
		//		if (event.getPlacedBlock().getBlocks() instanceof BlockDirt) {
		//			data.setNetherAccess(false);
		//			System.out.println("no access");
		//		} else if (event.getPlacedBlock().getBlocks() instanceof BlockStone) {
		//			data.setNetherAccess(true);
		//			System.out.println("yes access");
		//		}
	}

	@SubscribeEvent
	public static void registryCreation(RegistryEvent.NewRegistry e) {
		new RegistryBuilder<IMotherlodeRecipe>().setName(new ResourceLocation(Motherlode.MOD_ID, "recipes")).allowModification().setType(IMotherlodeRecipe.class).disableSaving().create();
		new RegistryBuilder<IRecipeTable>().setName(new ResourceLocation(Motherlode.MOD_ID, "recipe_tables")).setType(IRecipeTable.class).disableSaving().create();
	}

}
