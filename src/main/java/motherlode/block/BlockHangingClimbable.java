package motherlode.block;

import motherlode.util.AABBUtil;
import motherlode.util.MotherlodeCache;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockHangingClimbable extends BlockMotherlode implements IClimbable {
	public BlockHangingClimbable(String name, Material material) {
		super(name, material);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABBUtil.makeAABB(5.5, 0, 5.5, 16 - 5.5, 16, 16 - 5.5);
	}

	@Nullable
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	public boolean isSuitablePos(IBlockAccess worldIn, BlockPos pos) {
		BlockPos posUp = pos.up();
		IBlockState stateUp = worldIn.getBlockState(posUp);
		return stateUp.isSideSolid(worldIn, posUp, EnumFacing.DOWN) || stateUp.getBlock().equals(this);
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return isSuitablePos(worldIn, pos);
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
		super.neighborChanged(state, world, pos, blockIn, fromPos);
		if (!this.isSuitablePos(world, pos)) {
			this.dropBlockAsItem(world, pos, world.getBlockState(pos), 0);
			world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (playerIn.getHeldItem(hand).getItem().equals(Item.getItemFromBlock(this))) {
			for (int i = 0; i < pos.getY(); i++) {
				BlockPos newPos = pos.down(i);
				if (worldIn.getBlockState(newPos).getBlock().isReplaceable(worldIn, newPos)) {
					worldIn.setBlockState(pos.down(), this.getDefaultState());
				}
			}
			return true;
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		super.onEntityCollidedWithBlock(world, pos, state, entity);
		if (!world.isRemote && entity instanceof EntityPlayer) {
			Boolean isJumping = MotherlodeCache.playerJumpMap.get(entity.getUniqueID());
			if (isJumping != null && isJumping) {
				climb(isJumping, world, pos, state, entity);
			}
		}
		if (world.isRemote) {
			climb(Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown(), world, pos, state, entity);
		}
	}

	public static void climb(boolean isClimbing, World world, BlockPos pos, IBlockState state, Entity entity) {
		float speed = 0.2F;
		if (isClimbing) {
			entity.motionY = speed;
		}
		float f9 = 0.15F;
		entity.motionX = MathHelper.clamp(entity.motionX, -0.15000000596046448D, 0.15000000596046448D);
		entity.motionZ = MathHelper.clamp(entity.motionZ, -0.15000000596046448D, 0.15000000596046448D);
		entity.fallDistance = 0.0F;

		if (entity.motionY < -0.15D) {
			entity.motionY = -0.15D;
		}

		boolean flag = entity.isSneaking() && entity instanceof EntityPlayer;

		if (flag && entity.motionY < 0.0D) {
			entity.motionY = 0.0D;
		}
	}
}
