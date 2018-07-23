package motherlode.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenNetherMushroom extends WorldGenerator {

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		
		Block hat = Blocks.DIRT;
		Block stem = Blocks.STONE;
		
		int height = 2 + rand.nextInt(7);
		height = 7;
		
		if (worldIn.getBlockState(position.add(-2, height, 0)).getBlock() == Blocks.AIR &&
				worldIn.getBlockState(position.add(2, height, 0)).getBlock() == Blocks.AIR &&
				worldIn.getBlockState(position.add(0, height, 2)).getBlock() == Blocks.AIR &&
				worldIn.getBlockState(position.add(0, height, -2)).getBlock() == Blocks.AIR) {
			
			generateStem(worldIn, rand, position, stem, height);
			
			if (height < 5) {
				generateHatSmall(worldIn, rand, position.add(0, height, 0), hat);
			} else if (height < 7) {
				generateHatMedium(worldIn, rand, position.add(0, height, 0), hat);
			} else {
				generateHatLarge(worldIn, rand, position.add(0, height, 0), hat);
			}
			return true;
		}
		return false;
	}
	
	private void generateHatSmall(World world, Random rand, BlockPos pos, Block block) {
		int r = 1;
		for (int i=-r; i<=r; i++) {
			for (int j=-r; j<=r; j++) {
				world.setBlockState(pos.add(i, 0, j), block.getDefaultState());
			}
		}
	}
	
	private void generateHatMedium(World world, Random rand, BlockPos pos, Block block) {
		int r = 1;
		for (int i=-r; i<=r; i++) {
			for (int j=-r; j<=r; j++) {
				world.setBlockState(pos.add(i, 0, j), block.getDefaultState());
			}
			
			world.setBlockState(pos.add(-(r + 1), 0, i), block.getDefaultState());
			world.setBlockState(pos.add(r + 1, 0, i), block.getDefaultState());
			world.setBlockState(pos.add(i, 0, -(r + 1)), block.getDefaultState());
			world.setBlockState(pos.add(i, 0, r + 1), block.getDefaultState());
		}
	}
	
	private void generateHatLarge(World world, Random rand, BlockPos pos, Block block) {
		generateHatMedium(world, rand, pos, block);
		
		for (int i=-1; i<=1; i++) {
			world.setBlockState(pos.add(-3, -1, i), block.getDefaultState());
			world.setBlockState(pos.add(3, -1, i), block.getDefaultState());
			world.setBlockState(pos.add(i, -1, -3), block.getDefaultState());
			world.setBlockState(pos.add(i, -1, 3), block.getDefaultState());
		}
		for (int i=-2; i<=2; i++) {
			world.setBlockState(pos.add(-2, -1, i), block.getDefaultState());
			world.setBlockState(pos.add(2, -1, i), block.getDefaultState());
			world.setBlockState(pos.add(i, -1, -2), block.getDefaultState());
			world.setBlockState(pos.add(i, -1, 2), block.getDefaultState());
		}
		
	}
	
	private void generateStem(World world, Random rand, BlockPos pos, Block block, int height) {
		for (int i=0; i<height; i++) {
			world.setBlockState(pos.add(0, i, 0), block.getDefaultState());
		}
	}

}
