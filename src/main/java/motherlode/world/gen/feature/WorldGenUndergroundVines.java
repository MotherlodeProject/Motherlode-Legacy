package motherlode.world.gen.feature;

import java.util.Random;

import motherlode.block.MotherlodeBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class WorldGenUndergroundVines extends WorldGenerator {

	private static final int MAX_LENGTH = 4;
	
	private static final Type[] TARGET_BIOME_TYPES = new Type[] {
		Type.CONIFEROUS,
		Type.FOREST,
		Type.JUNGLE,
		Type.LUSH,
		Type.PLAINS,
		Type.SWAMP,
	};
	
	private final boolean useFlowers;
	private final Random rand;
	
	public WorldGenUndergroundVines(boolean useFlowers, Random rand) {
		this.useFlowers = useFlowers;
		this.rand = rand;
	}
	
	@Override
	public boolean generate(World world, Random localRand, BlockPos pos) {
		//Already tested for air.
		world.setBlockState(pos, getVineBlock(), 16);

		for (int i = 1; i < MAX_LENGTH; i++) {
			BlockPos newPos = new BlockPos(pos.getX(), pos.getY() - i, pos.getZ());
			
			if (!world.getBlockState(newPos).equals(Blocks.AIR.getDefaultState()) || localRand.nextInt(2) == 0) {
				break;
			}
			
			world.setBlockState(newPos, getVineBlock(), 16);
		}
		
		return true;
	}
	
	private IBlockState getVineBlock() {
		if (useFlowers && rand.nextInt(3) == 0) {
			return MotherlodeBlocks.FLOWERED_THICK_VINE.getDefaultState();
		}
		
		return MotherlodeBlocks.THICK_VINE.getDefaultState();
	}
	
	public static boolean canGenerateInBiome(Biome biome) {
		for (int i = 0; i < TARGET_BIOME_TYPES.length; i++) {
			if (BiomeDictionary.hasType(biome, TARGET_BIOME_TYPES[i])) {
				return true;
			}
		}
		
		return false;
	}
}