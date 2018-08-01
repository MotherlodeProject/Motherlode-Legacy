package motherlode;

import motherlode.api.recipe.IMotherlodeRecipe;
import motherlode.api.recipe.IRecipeTable;
import motherlode.command.CommandFindBiome;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import java.util.ArrayList;
import java.util.List;

import motherlode.Motherlode;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import net.minecraftforge.registries.RegistryBuilder;

@Mod.EventBusSubscriber(modid = Motherlode.MOD_ID)
public class MotherlodeEventHandler {

	private static List<BlockPos> scannedBlocks = new ArrayList<BlockPos>(24);
	private static List<BlockPos> logs = new ArrayList<BlockPos>(24);
	private static int startHeight;
	private static BlockPos initialPos;
	private static int initialHeight;
	private static final int PARTIAL_HEIGHT = 10;
	private static boolean wasInterrupted = false;
	private static World world;
	
	@SubscribeEvent
	public static void registryCreation(RegistryEvent.NewRegistry e) {
		new RegistryBuilder<IMotherlodeRecipe>().setName(new ResourceLocation(Motherlode.MOD_ID, "recipes")).allowModification().setType(IMotherlodeRecipe.class).disableSaving().create();
		new RegistryBuilder<IRecipeTable>().setName(new ResourceLocation(Motherlode.MOD_ID, "recipe_tables")).setType(IRecipeTable.class).disableSaving().create();
	}
	
	
	// Prototype search algorithm for chopping trees, spread over multiple ticks (sort of cheating, can fail to find some branches
	// due to search being "reset" every tick, but testing works fine)
	/*
	@SubscribeEvent
	public static void onBlockBreakEvent(BreakEvent event) {
		if (!event.getWorld().isRemote) {
			EntityPlayer player = event.getPlayer();
			world = event.getWorld();
			if (player.getHeldItemMainhand().getItem() instanceof ItemAxe && event.getState().getBlock() == Blocks.LOG) {
				initialPos = event.getPos();
				startHeight = initialPos.getY();
				initialHeight = startHeight;
				
				scannedBlocks.clear();
				logs.clear();
				wasInterrupted = false;
				findSomeBlocks(world, event.getPos());
				
				if (!wasInterrupted) {
					for (BlockPos p : logs) {
//						world.destroyBlock(p, true);
						world.setBlockToAir(p);
						
					}
					scannedBlocks.clear();
					logs.clear();
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onServerTickEvent(ServerTickEvent event) {
		if (wasInterrupted) {
			if (event.phase == TickEvent.Phase.START) {
				wasInterrupted = false;
				startHeight += PARTIAL_HEIGHT + 1;
				findSomeBlocks(world, initialPos.up(startHeight - initialHeight));
				System.out.println(wasInterrupted);
				
				if (!wasInterrupted) {
					for (BlockPos p : logs) {
						world.setBlockToAir(p);
						
					}
					scannedBlocks.clear();
					logs.clear();
				}
				
			}			
		}
	}
	
	private static void findSomeBlocks(World world, BlockPos pos) {
		if (pos.getY() > startHeight + PARTIAL_HEIGHT) {
			wasInterrupted = true;
			return;
		}
		
		Block block = world.getBlockState(pos).getBlock();
		
		scannedBlocks.add(pos);
		if (block == Blocks.LOG || block == Blocks.LOG2) {
			logs.add(pos);
		} else {
			return;
		}
		
		if (!scannedBlocks.contains(pos.up())) {
			findSomeBlocks(world, pos.up());
		}
		if (!scannedBlocks.contains(pos.north())) {
			findSomeBlocks(world, pos.north());
		}
		if (!scannedBlocks.contains(pos.west())) {
			findSomeBlocks(world, pos.west());
		}
		if (!scannedBlocks.contains(pos.south())) {
			findSomeBlocks(world, pos.south());
		}
		if (!scannedBlocks.contains(pos.east())) {
			findSomeBlocks(world, pos.east());
		}
		
		if (!scannedBlocks.contains(pos.north().west())) {
			findSomeBlocks(world, pos.north().west());
		}
		if (!scannedBlocks.contains(pos.north().east())) {
			findSomeBlocks(world, pos.north().east());
		}
		if (!scannedBlocks.contains(pos.south().west())) {
			findSomeBlocks(world, pos.south().west());
		}
		if (!scannedBlocks.contains(pos.south().east())) {
			findSomeBlocks(world, pos.south().east());
		}
		
		if (!scannedBlocks.contains(pos.up().north())) {
			findSomeBlocks(world, pos.up().north());
		}
		if (!scannedBlocks.contains(pos.up().west())) {
			findSomeBlocks(world, pos.up().west());
		}
		if (!scannedBlocks.contains(pos.up().south())) {
			findSomeBlocks(world, pos.up().south());
		}
		if (!scannedBlocks.contains(pos.up().east())) {
			findSomeBlocks(world, pos.up().east());
		}
		
		if (!scannedBlocks.contains(pos.up().north().west())) {
			findSomeBlocks(world, pos.up().north().west());
		}
		if (!scannedBlocks.contains(pos.up().north().east())) {
			findSomeBlocks(world, pos.up().north().east());
		}
		if (!scannedBlocks.contains(pos.up().south().west())) {
			findSomeBlocks(world, pos.up().south().west());
		}
		if (!scannedBlocks.contains(pos.up().south().east())) {
			findSomeBlocks(world, pos.up().south().east());
		}
	}
	*/

}
