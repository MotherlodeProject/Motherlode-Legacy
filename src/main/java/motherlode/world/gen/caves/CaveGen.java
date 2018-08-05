package motherlode.world.gen.caves;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.base.MoreObjects;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.NoiseGeneratorSimplex;

/**
 * Mod of Mojang's CaveGenBase and RavineGenBase by
 * 
 * @author PersonTheCat
 */
public class CaveGen extends MapGenBase {
	/**
	 * Avoid repeatedly initializing noise generators.
	 */
	protected static SimplexNoiseGenerator3D noise;
	protected static NoiseGeneratorSimplex noise2D1;
	protected static NoiseGeneratorSimplex noise2D2;

	protected Random indRand = new Random(12345); //Prevents artifacting.
	
	protected static final IBlockState
		BLK_STONE = Blocks.STONE.getDefaultState(),
		BLK_LAVA = Blocks.LAVA.getDefaultState(),
		BLK_WATER = Blocks.WATER.getDefaultState(),
		BLK_AIR = Blocks.AIR.getDefaultState();
	
	/**
	 * A list of replaceable blocks. Stone is handled elsewhere for performance efforts.
	 */
	protected static final List<Block> replaceableBlocks = Arrays.asList(new Block[] {
		Blocks.DIRT,
		Blocks.GRASS,
		Blocks.HARDENED_CLAY,
		Blocks.STAINED_HARDENED_CLAY,
		Blocks.SANDSTONE,
		Blocks.RED_SANDSTONE,
		Blocks.MYCELIUM,
		Blocks.SNOW_LAYER
	});
	
	protected static final List<Biome> exceptionBiomes = Arrays.asList(new Biome[] {
		Biomes.BEACH,
		Biomes.DESERT
	});
	
	protected static final float
		PI_OVER_2 = (float) (Math.PI / 2.0),
		PI_TIMES_2 = (float) (Math.PI * 2.0);
	
	/**
	 * A few lists of values that may need to be easily changeable.
	 */
	private final float
		cavernAmplitude = 1.0F,
		cavernFrequency = 70.0F, //Technically the opposite of frequency.
		cavernScaleY = 0.38F,
		cavernSelectionThreshold = 0.6F; //0 - 1; higher = smaller.
	
	private final int
		cavernMinHeight = 10,
		cavernMaxHeight = 50;
	
	@Override
	public void generate(World worldIn, int x, int z, ChunkPrimer primer) {
		this.world = worldIn;
		
		setupNoiseGenerators();
		addNoiseFeatures(x, z, primer);
		
		super.generate(worldIn, x, z, primer);
	}
	
	@Override
	protected void recursiveGenerate(World worldIn, int chunkX, int chunkZ, int originalX, int originalZ, ChunkPrimer primer) {
		int caveFrequency = rand.nextInt(rand.nextInt(rand.nextInt(7) + 1) + 1);
		if (rand.nextInt(7) != 0) caveFrequency = 0;

		for (int i = 0; i < caveFrequency; i++)	{
			double x = (chunkX * 16) + rand.nextInt(16),
			       y = rand.nextInt(rand.nextInt(128) + 8),
			       z = (chunkZ * 16) + rand.nextInt(16);
			
			runGenerator(1, primer, originalX, originalZ, x, y, z);
		}
	}
    
    private void runGenerator(int numDirections, ChunkPrimer primer, int originalX, int originalZ, double x, double y, double z) {
		for (int j = 0; j < numDirections; j++) {
        	float slopeXZ = 1.0F *(rand.nextFloat() * PI_TIMES_2);
        	float slopeY = 0.25F * (rand.nextFloat() - 0.5F);
        	float scale = 4.5F;

        	//Randomly increase starting size. From vanilla
        	if (this.rand.nextInt(10) == 0) {
        		scale *= rand.nextFloat() * rand.nextFloat() * 3.0F + 1.0F;
        	}

        	addTunnel(rand.nextLong(), originalX, originalZ, primer, x, y, z, scale, slopeXZ, slopeY, 0, 121, 0.6);
        }
    }
    
    private static World previousWorld;    
    
    /**
     * Avoid setting up generators every single time generate() is called.
     */
    private void setupNoiseGenerators() {
    	if (world != null && !world.equals(previousWorld)) {
    		previousWorld = world;
    		
    		initStaticGenerators(world);
    	}
    }
    
    private static void initStaticGenerators(World world) {
    	long seed = world.getSeed();

    	noise = new SimplexNoiseGenerator3D(seed);
    	noise2D1 = new NoiseGeneratorSimplex(new Random(seed));
    	noise2D2 = new NoiseGeneratorSimplex(new Random(seed >> 4));
    }
    
	/**
	 * Generates caverns directly based on the world seed using an open simplex
	 * noise generator. Would ideally be called as stone gets placed (?).
	 * 
	 * Where caverns cannot generate, stone layers are placed, if applicable.
	 */
	protected void addNoiseFeatures(int chunkX, int chunkZ, ChunkPrimer primer) {
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = cavernMaxHeight + 2; y >= 6; y--) {
					decidePlace(chunkX, chunkZ, primer, x, y, z);
				}
			}
		}
	}
	
	/**
	 * Add other noise-base features here.
	 */
	private void decidePlace(int chunkX, int chunkZ, ChunkPrimer primer, int x, int y, int z) {
		if (!primer.getBlockState(x, y, z).equals(BLK_WATER) && !primer.getBlockState(x, y + 1, z).equals(BLK_WATER)) {
			double actualX = x + (chunkX * 16);
			double actualZ = z + (chunkZ * 16);
			
			double caveNoise = noise.getFractalNoise(actualX, (float) y / cavernScaleY, actualZ, 1, cavernFrequency, cavernAmplitude);
			
			if (caveNoise >= cavernSelectionThreshold) {
				//Don't immediately calculate both of these to save time.
				double floorNoise, ceilNoise;
				
				//More blocks are likely to be above this point than to be below floor noise.
				ceilNoise = get2DNoiseFractal(noise2D2, actualX, actualZ, 1, cavernFrequency * 0.75, cavernAmplitude);
				
				if (y < cavernMaxHeight + (7.0 * ceilNoise - 7.0)) {
					floorNoise = get2DNoiseFractal(noise2D1, actualX, actualZ, 1, cavernFrequency * 0.75, cavernAmplitude);
					
					if (y > cavernMinHeight + (3.0 * floorNoise + 3.0)) {
						replaceBlock(primer, x, y, z, chunkX, chunkZ, false);
						
						return;
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
	
	/**
	 * Mod of {@link MapGenCaves#addTunnel} by PersonTheCat. Supports object-specific replacement
	 * of most variables, as well as a few new equations to support additional shapes, noise control,
	 * and block replacement alternatives.
	 * 
	 * @param seed          The world's seed. Used to create a local Random object for regen parity.
	 * @param chunkX        Chunk x coordinate.
	 * @param chunkZ        Chunk z coordinate.
	 * @param primer        Provides information about the current chunk.
	 * @param posX          Block x coordinate.
	 * @param posY          Block y coordinate.
	 * @param posZ          Block z coordinate.
	 * @param scale         Overall cave size (radius?).
	 * @param slopeXZ       The horizontal angle of the cave.
	 * @param slopeY        The vertical angle of the cave.
	 * @param startingPoint Not entirely sure. Seems to indicate whether a room should be spawned instead of a tunnel. Also used for determining branch length.
	 * @param distance      The length of the tunnel. 0 = get distance from MapGenBase.
	 * @param scaleY        A vertical cave size multiple. 1.0 = same as scale.
	 */
	protected void addTunnel(long seed, int chunkX, int chunkZ, ChunkPrimer primer, double posX, double posY, double posZ, float scale, float slopeXZ, float slopeY, int startingPoint, int distance, double scaleY) {
		float twistXZ = 0.0F, twistY = 0.0F;
		
		double centerX = chunkX * 16 + 8,
		       centerZ = chunkZ * 16 + 8;

		Random localRandom = new Random(seed);

		int randomSegmentIndex = localRandom.nextInt(distance / 2) + distance / 4,
		    position = startingPoint;

		for (boolean randomNoiseCorrection = localRandom.nextInt(6) == 0; position < distance; position++) {
			double stretchXZ = 1.5D + (MathHelper.sin(position * (float) Math.PI / distance) * scale),
			       stretchY = stretchXZ * scaleY;
			
			float cos = MathHelper.cos(slopeY),
			      sin = MathHelper.sin(slopeY);
			
			posX += MathHelper.cos(slopeXZ) * cos;
			posY += sin;
			posZ += MathHelper.sin(slopeXZ) * cos;
			
			//Slopes are recalculated on subsequent iterations.
			slopeXZ += twistXZ * 0.1F;
			slopeY += twistY * 0.1F;

			//Rotates the beginning of the line around the end.
			twistY = adjustTwist(twistY, localRandom, 0.5F, 2.0F);
			
			//Positive is counterclockwise, negative is clockwise.
			twistXZ = adjustTwist(twistXZ, localRandom, 0.75F, 4.0F);

//			scale = (float) adjustScale(scale, indRand, 1.0F, 0F);			
//			scaleY = adjustScale(scaleY, indRand, 1.0F, 0F);

			if (scale > 1.0F && distance > 0) {
				if (position == randomSegmentIndex) {
					addTunnel(localRandom.nextLong(), chunkX, chunkZ, primer, posX, posY, posZ, scale, slopeXZ - PI_OVER_2, slopeY / 3.0F, position, distance, scaleY);
					addTunnel(localRandom.nextLong(), chunkX, chunkZ, primer, posX, posY, posZ, scale, slopeXZ + PI_OVER_2, slopeY / 3.0F, position, distance, scaleY);
					
					return;
				}
			}

			if (localRandom.nextInt(4) != 0) {
				double fromCenterX = posX - centerX,
				       fromCenterZ = posZ - centerZ,
				       currentPos = distance - position,
				       adjustedScale = scale + 18.0F;

				if (((fromCenterX * fromCenterX) + (fromCenterZ * fromCenterZ) - (currentPos * currentPos)) > (adjustedScale * adjustedScale)) {
					return;
				}

				if (posX >= centerX - 16.0D - stretchXZ * 2.0D && posZ >= centerZ - 16.0D - stretchXZ * 2.0D && posX <= centerX + 16.0D + stretchXZ * 2.0D && posZ <= centerZ + 16.0D + stretchXZ * 2.0D) {
					int startX = applyLimitXZ(MathHelper.floor(posX - stretchXZ) - chunkX * 16 - 1),
					    endX = applyLimitXZ(MathHelper.floor(posX + stretchXZ) - chunkX * 16 + 1),
					    startY = applyLimitY(MathHelper.floor(posY - stretchY) - 1),
					    endY = applyLimitY(MathHelper.floor(posY + stretchY) + 1),
					    startZ = applyLimitXZ(MathHelper.floor(posZ - stretchXZ) - chunkZ * 16 - 1),
					    endZ = applyLimitXZ(MathHelper.floor(posZ + stretchXZ) - chunkZ * 16 + 1);

					if (!testForWater(primer, stretchXZ, stretchY, chunkX, chunkZ, startX, endX, posX, startY, endY, posY, startZ, endZ, posZ)) {
						replaceSection(primer, stretchXZ, stretchY, chunkX, chunkZ, startX, endX, posX, startY, endY, posY, startZ, endZ, posZ);
					}
				}
			}
		}
	}
	
	private int applyLimitXZ(int xz) {
		return xz < 0 ? 0 : xz > 16 ? 16 : xz;
	}
	
	private int applyLimitY(int y) {
		return y < 1 ? 1 : y > 248 ? 248 : y;
	}
	
	private float adjustTwist(float original, Random rand, float factor, float randFactor) {
		original *= factor;
		original += randFactor * (rand.nextFloat() - rand.nextFloat()) * rand.nextFloat();
		
		return original;
	}
	
	private double adjustScale(double original, Random rand, float factor, float randFactor) {
		original *= factor;
		original += randFactor * (rand.nextFloat() - 0.5F);
		if (original < 0) original = 0;
		
		return original;
	}
	
	private boolean testForWater(ChunkPrimer primer, double stretchXZ, double stretchY, int chunkX, int chunkZ, int x1, int x2, double posX, int y1, int y2, double posY, int z1, int z2, double posZ) {
		for (int x = x1; x < x2; x++) {
			double finalX = ((x + chunkX * 16) + 0.5D - posX) / stretchXZ;

			for (int z = z1; z < z2; z++) {
				double finalZ = ((z + chunkZ * 16) + 0.5D - posZ) / stretchXZ;

				if (((finalX * finalX) + (finalZ * finalZ)) < 1.0D)	{
					for (int y = y2; y > y1; y--) {
						double finalY = ((y - 1) + 0.5D - posY) / stretchY;

						if ((finalY > -0.7D) && (((finalX * finalX) + (finalY * finalY) + (finalZ * finalZ)) < 1.0D)) {
							if (isOceanBlock(primer, x, y, z, chunkX, chunkZ)) {
								return true;
							}
						}
					}
				}
			}
		}
		
		return false;
	}
	
	protected boolean isOceanBlock(ChunkPrimer primer, int x, int y, int z, int chunkX, int chunkZ) {
		Block block = primer.getBlockState(x, y, z).getBlock();
		
		return block.equals(Blocks.FLOWING_WATER) || block.equals(Blocks.WATER);
	}
	
	private void replaceSection(ChunkPrimer primer, double stretchXZ, double stretchY, int chunkX, int chunkZ, int x1, int x2, double posX, int y1, int y2, double posY, int z1, int z2, double posZ) {
		for (int x = x1; x < x2; x++) {
			double finalX = ((x + chunkX * 16) + 0.5 - posX) / stretchXZ;

			for (int z = z1; z < z2; z++) {
				double finalZ = ((z + chunkZ * 16) + 0.5 - posZ) / stretchXZ;

				if (((finalX * finalX) + (finalZ * finalZ)) < 1.0) {
					for (int y = y2; y > y1; y--) {
						double finalY = ((y - 1) + 0.5 - posY) / stretchY;

						if ((finalY > -0.7) && (((finalX * finalX) + (finalY * finalY) + (finalZ * finalZ)) < 1.0D)) {
							if (isTopBlock(primer, x, y, z, chunkX, chunkZ)) {
								replaceBlock(primer, x, y, z, chunkX, chunkZ, true);
							}

							else replaceBlock(primer, x, y, z, chunkX, chunkZ, false);
						}
					}
				}
			}
		}
	}
	
	/*
	 * From Forge:
	 * 
	 * Determine if the block at the specified location is the top block for the biome, we take into account
	 * Vanilla bugs to make sure that we generate the map the same way vanilla does.
	 */
	private boolean isTopBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ) {
		Biome biome = world.getBiome(new BlockPos(x + chunkX * 16, 0, z + chunkZ * 16));
		IBlockState state = data.getBlockState(x, y, z);

		return (isExceptionBiome(biome) ? state.getBlock() == Blocks.GRASS : state.getBlock() == biome.topBlock);
	}
	
	/*
	 * From Forge: to help imitate vanilla generation.
	 */
	private boolean isExceptionBiome(Biome biome) {
		for (Biome biome2 : exceptionBiomes) {
			if (biome.equals(biome2)) return true;
		}
		
		return false;
	}
	
	/**
	 * Digs out the current block, default implementation removes stone, filler, and top block
	 * Sets the block to lava if y is less then 10, and air other wise.
	 * If setting to air, it also checks to see if we've broken the surface and if so
	 * tries to make the floor the biome's top block
	 * 
	 * Modded by PersonTheCat to allow filling with water, or randomly from an array of filler
	 * blocks. Each filler block can have direction matchers and can either replace the
	 * current position or the location where the matching blockstate was found.
	 * 
	 * * Block fillers temporarily (?) removed for Motherlode.
	 * 
	 * @param data          Block data array
	 * @param x             local X position
	 * @param y             local Y position
	 * @param z             local Z position
	 * @param chunkX        Chunk X position
	 * @param chunkZ        Chunk Y position
	 * @param foundTop      True if we've encountered the biome's top block. Ideally if we've broken the surface.
	 * 
	 * To-do: change airOnly to decorate / noAir. Decorate never places air.
	 */
	protected boolean replaceBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop) {
		Biome biome = world.getBiome(new BlockPos(x + (chunkX * 16), 0, z + (chunkZ * 16)));
		
		IBlockState state = data.getBlockState(x, y, z);
		IBlockState up = (IBlockState) MoreObjects.firstNonNull(data.getBlockState(x, y + 1, z), BLK_AIR);
		IBlockState top = biome.topBlock;
		IBlockState filler = biome.fillerBlock;
				
		int yDown = y - 1;
		
		if (canReplaceBlock(state, up) || state.getBlock().equals(top.getBlock()) || state.getBlock().equals(filler.getBlock())) {
			if (yDown < 10)	{
				data.setBlockState(x, y, z, BLK_LAVA);
				
				return true;
			}
			
			data.setBlockState(x, y, z, BLK_AIR);

			if (foundTop && data.getBlockState(x, yDown, z).getBlock().equals(filler.getBlock())) {
				data.setBlockState(x, yDown, z, top.getBlock().getDefaultState());
			}
			
			return true;
		}
		
		return false;
	}
	
	private static boolean canReplaceBlock(IBlockState state, IBlockState blockAbove) {
    	if (state.equals(BLK_STONE)) return true; //First most common block
    	
    	if (state.equals(BLK_AIR)) return false; //Second most common (from overlapping generation)
		
    	for (Block block : replaceableBlocks) {//Others
    		if (state.getBlock().equals(block)) return true;
    	}
    	
		return (state.getBlock().equals(Blocks.SAND) || state.getBlock().equals(Blocks.GRAVEL)) &&
				!blockAbove.getMaterial().equals(Material.WATER);
	}
}