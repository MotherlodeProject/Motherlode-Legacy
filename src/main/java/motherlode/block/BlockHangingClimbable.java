package motherlode.block;

import motherlode.item.MotherlodeItems;
import motherlode.util.AABBUtil;
import motherlode.util.MotherlodeCache;
import motherlode.util.RandomUtil;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class BlockHangingClimbable extends BlockMotherlode implements IClimbable, IShearable {
	public static final AxisAlignedBB AABB = AABBUtil.makeAABB(5, 0, 5, 16 - 5, 16, 16 - 5);

	public BlockHangingClimbable(String name, Material material) {
		super(name, material);
		setSoundType(material == Material.PLANTS || material == Material.VINE ? SoundType.PLANT : SoundType.CLOTH);
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
		return AABB;
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
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (player.getHeldItem(hand).getItem().equals(Item.getItemFromBlock(this))) {
			for (int i = 1; i < pos.getY(); i++) {
				BlockPos newPos = pos.down(i);
				if (world.getBlockState(newPos).getBlock().isReplaceable(world, newPos) && world.getBlockState(newPos).getBlock() != this) {
					if (player.getHeldItem(hand).onItemUse(player, world, newPos, hand, EnumFacing.DOWN, 0.5F, 0.5F, 0.5F) == EnumActionResult.SUCCESS) {
						if (player.isCreative()) {
							player.getHeldItem(hand).grow(1);
						}
						return true;
					}
				}
			}
		}
		return super.onBlockActivated(world, pos, state, player, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		super.onEntityCollidedWithBlock(world, pos, state, entity);
		if (!world.isRemote && entity instanceof EntityPlayer) {
			Boolean isJumping = MotherlodeCache.playerJumpMap.get(entity.getUniqueID());
			if (isJumping != null) {
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
			entity.motionX = MathHelper.clamp(entity.motionX, -0.15000000596046448D, 0.15000000596046448D);
			entity.motionZ = MathHelper.clamp(entity.motionZ, -0.15000000596046448D, 0.15000000596046448D);
		}
		entity.fallDistance = 0.0F;

		if (entity.motionY < -0.15D) {
			entity.motionY = -0.15D;
		}

		boolean flag = entity.isSneaking() && entity instanceof EntityPlayer;

		if (flag && entity.motionY < 0.0D) {
			entity.motionY = 0;
		}
		double d = entity.motionY;
		if (true) {

		}
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		if (blockMaterial == Material.VINE || blockMaterial == Material.PLANTS) {
			drops.clear();
			if (RandomUtil.chance(1, 5)) {
				drops.add(new ItemStack(MotherlodeItems.PLANT_FIBER));
			}
		} else {
			super.getDrops(drops, world, pos, state, fortune);
		}
	}

	@Override
	public boolean isShearable(
		@Nonnull
			ItemStack item, IBlockAccess world, BlockPos pos) {
		return blockMaterial == Material.VINE || blockMaterial == Material.PLANTS;
	}

	@Nonnull
	@Override
	public List<ItemStack> onSheared(
		@Nonnull
			ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return NonNullList.withSize(1, new ItemStack(this));
	}
}
