package motherlode.world.gen.feature;

import java.util.Random;

import com.google.common.base.Predicate;

import motherlode.block.MotherlodeBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class MotherlodeFeatureGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		switch (world.provider.getDimension()) {
		case 0:
			generateOverworld(random, chunkX, chunkZ, world);
			break;
		case 1:
			break;
		case -1:
			generateNether(random, chunkX, chunkZ, world);
			break;
		}
		
	}
	
	private void generateOverworld(Random random, int chunkX, int chunkZ, World world) {
		
		generateOre(MotherlodeBlocks.AMETHYST_ORE.getDefaultState(), world, random, 5, 10, chunkX, chunkZ, 20, 128, 5);
		generateOre(MotherlodeBlocks.COPPER_ORE.getDefaultState(), world, random, 5, 10, chunkX, chunkZ, 20, 128, 5);
		generateOre(MotherlodeBlocks.PLATINUM_ORE.getDefaultState(), world, random, 5, 10, chunkX, chunkZ, 20, 128, 5);
		generateOre(MotherlodeBlocks.RUBY_ORE.getDefaultState(), world, random, 5, 10, chunkX, chunkZ, 20, 128, 5);
		generateOre(MotherlodeBlocks.SALTPETER_ORE.getDefaultState(), world, random, 5, 10, chunkX, chunkZ, 20, 128, 5);
		generateOre(MotherlodeBlocks.SAPPHIRE_ORE.getDefaultState(), world, random, 5, 10, chunkX, chunkZ, 20, 128, 5);
		generateOre(MotherlodeBlocks.SILVER_ORE.getDefaultState(), world, random, 5, 10, chunkX, chunkZ, 20, 128, 5);
		generateOre(MotherlodeBlocks.SULPHUR_ORE.getDefaultState(), world, random, 5, 10, chunkX, chunkZ, 20, 128, 5);
		generateOre(MotherlodeBlocks.TITANIUM_ORE.getDefaultState(), world, random, 5, 10, chunkX, chunkZ, 20, 128, 5);
		generateOre(MotherlodeBlocks.TOPAZ_ORE.getDefaultState(), world, random, 5, 10, chunkX, chunkZ, 20, 128, 5);
		
	}
	
	private void generateNether(Random random, int chunkX, int chunkZ, World world) {
		
		generateNetherOre(MotherlodeBlocks.NETHERITE_ORE.getDefaultState(), world, random, 5, 10, chunkX, chunkZ, 20, 100, 10);
		
		generateNetherMushroom(random, chunkX, chunkZ, world, 0.3F);
		
	}
	
	private void generateOre(IBlockState state, World world, Random random, int blockAmountMin, int blockAmountMax, int chunkX, int chunkZ, int minY, int maxY, int attempts) {
		
		for (int i=0; i<attempts; i++) {
			
			int amount = random.nextInt(blockAmountMax - blockAmountMin);
			int x = chunkX * 16 + random.nextInt(16);
			int y = minY + random.nextInt(maxY - minY);
			int z = chunkZ * 16 + random.nextInt(16);
			
			new WorldGenMinable(state, amount).generate(world, random, new BlockPos(x, y, z));
			/*
			for (int j=minY; j<maxY; j++) {
				if (world.getBlockState(new BlockPos(x, j, z)) == state) {
					System.out.println("Generated " + state.getBlock() + " at " + x + " " + j + " " + z);
				}
			}
			*/
		}
	}

	private void generateNetherOre(IBlockState state, World world, Random random, int blockAmountMin, int blockAmountMax, int chunkX, int chunkZ, int minY, int maxY, int attempts) {
		
		for (int i=0; i<attempts; i++) {
			
			int amount = random.nextInt(blockAmountMax - blockAmountMin);
			int x = chunkX * 16 + random.nextInt(16);
			int y = minY + random.nextInt(maxY - minY);
			int z = chunkZ * 16 + random.nextInt(16);
			
			new WorldGenMinable(state, amount, new NetherPredicate()).generate(world, random, new BlockPos(x, y, z));
			/*
			for (int j=minY; j<maxY; j++) {
				if (world.getBlockState(new BlockPos(x, j, z)) == state) {
					System.out.println("Generated " + state.getBlock() + " at " + x + " " + j + " " + z);
				}
			}
			*/
		}
	}
	
	private void generateNetherMushroom(Random random, int chunkX, int chunkZ, World world, float probability) {
		if (random.nextFloat() < probability) {
			
			int x = chunkX * 16 + random.nextInt(16);
			int z = chunkZ * 16 + random.nextInt(16);
			int y = findGroundFromBelow(world, Blocks.NETHERRACK, x, 10, 50, z);
			if (y > 0) {
				new WorldGenNetherMushroom().generate(world, random, new BlockPos(x, y + 1, z));
			}
			
			x = chunkX * 16 + random.nextInt(16);
			z = chunkZ * 16 + random.nextInt(16);
			y = findGroundFromAbove(world, Blocks.NETHERRACK, x, 51, 100, z);
			if (y > 0) {
				new WorldGenNetherMushroom().generate(world, random, new BlockPos(x, y + 1, z));
			}
		}
	}
	
	private int findGroundFromBelow(World world, Block groundBlock, int x, int minY, int maxY, int z) {
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(x, 0, z);
		for (int y=minY; y<=maxY; y++) {
			if (world.getBlockState(pos.setPos(x, y + 1, z)).getBlock() == Blocks.AIR && world.getBlockState(pos.setPos(x, y, z)).getBlock() == groundBlock) {
				return y;
			}
		}
		return 0;
	}
	
	private int findGroundFromAbove(World world, Block groundBlock, int x, int minY, int maxY, int z) {
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(x, 0, z);
		for (int y=maxY; y>=minY; y--) {
			if (world.getBlockState(pos.setPos(x, y + 1, z)).getBlock() == Blocks.AIR && world.getBlockState(pos.setPos(x, y, z)).getBlock() == groundBlock) {
				return y;
			}
		}
		return 0;
	}
	
	static class NetherPredicate implements Predicate<IBlockState> {

		@Override
		public boolean apply(IBlockState input) {
			return input.getBlock() == Blocks.NETHERRACK;
		}
	}

}
