package motherlode.world.gen.feature;

import java.util.Random;

import com.google.common.base.Predicate;

import motherlode.block.MotherlodeBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorSimplex;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.IWorldGenerator;

public class MotherlodeFeatureGenerator implements IWorldGenerator {

	private static NoiseGeneratorSimplex vineNoise;
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		switch (world.provider.getDimension()) {
		case 0:
			vineNoise = new NoiseGeneratorSimplex(new Random(world.getSeed()));
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
		
		generateUndergroundVines(random, chunkX, chunkZ, world);
		
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
	
	private void generateUndergroundVines(Random rand, int chunkX, int chunkZ, World world)
	{
		int xOrigin = chunkX * 16, zOrigin = chunkZ * 16;

		Biome centerBiome = world.getBiome(new BlockPos(xOrigin + 8, 0, zOrigin + 8));
		boolean useFlowers = (BiomeDictionary.hasType(centerBiome, Type.JUNGLE) ||
		                      BiomeDictionary.hasType(centerBiome, Type.LUSH));
		
		//Only creating one instance / chunk.
		WorldGenUndergroundVines gen = new WorldGenUndergroundVines(useFlowers, rand);
		
		//Don't calculate noise at all 256 coords. Just do 4 quadrants / chunk.
		generateUndergroundVineQuadrant(gen, rand, xOrigin, zOrigin, world);
		generateUndergroundVineQuadrant(gen, rand, xOrigin + 8, zOrigin, world);
		generateUndergroundVineQuadrant(gen, rand, xOrigin, zOrigin + 8, world);
		generateUndergroundVineQuadrant(gen, rand, xOrigin + 8, zOrigin + 8, world);
	}
	
	private void generateUndergroundVineQuadrant(WorldGenUndergroundVines gen, Random rand, int posX, int posZ, World world)
	{
		int minHeight = 40, maxHeight = 80;
		
		if (WorldGenUndergroundVines.canGenerateInBiome(world.getBiome(new BlockPos(posX + 4, 0, posZ + 4)))) {	
			if (get2DNoiseFractal(vineNoise, posX + 4, posZ + 4, 1, 40, 1.0) > 0.15) {
				for (int x = posX; x < posX + 8; x++) {
					for (int z = posZ; z < posZ + 8; z++) {
						boolean previouslyAir = false;

						for (int y = minHeight; y < maxHeight; y++) {
							boolean currentlyAir = world.getBlockState(new BlockPos(x, y, z)).equals(Blocks.AIR.getDefaultState());

							if ((previouslyAir && !currentlyAir) && rand.nextInt(5) == 0) {
								gen.generate(world, rand, new BlockPos(x, y - 1, z));
							}
							
							previouslyAir = currentlyAir;
						}
					}
				}
			}
		}
	}
	
	private double get2DNoiseFractal(NoiseGeneratorSimplex generator, double x, double z, int octaves, double frequency, double amplitude) {
		double gain = 1.0F, sum = 0.0F;
		
		for (int i = 0; i < octaves; i++) {
			sum += generator.getValue(x * gain / frequency, z * gain / frequency) * amplitude / gain;
			gain *= 2.0F;
		}
		
		return sum;
	}
	
	static class NetherPredicate implements Predicate<IBlockState> {

		@Override
		public boolean apply(IBlockState input) {
			return input.getBlock() == Blocks.NETHERRACK;
		}
	}

}
