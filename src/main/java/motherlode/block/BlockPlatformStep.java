package motherlode.block;

import motherlode.client.model.BlockModelDefinition;
import motherlode.client.model.ItemBlockModelDefinition;
import motherlode.client.model.ItemModelDefinition;
import motherlode.util.AABBUtil;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class BlockPlatformStep extends BlockMotherlode {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public final String name;

	public BlockPlatformStep(String name) {
		super(name + "_platform_step");
		this.name = name;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABBUtil.makeAABB(0, 3, 0, 16, 16, 16);
	}

	@Override
	public void addCollisionBoxToList(IBlockState state,
	                                  World worldIn,
	                                  BlockPos pos,
	                                  AxisAlignedBB entityBox,
	                                  List<AxisAlignedBB> collidingBoxes,
	                                  @Nullable
		                                  Entity entityIn,
	                                  boolean isActualState) {
		switch (state.getValue(FACING)) {
			case NORTH:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, AABBUtil.makeAABB(16, 8, 16, 8, 8, 8));
				addCollisionBoxToList(pos, entityBox, collidingBoxes, AABBUtil.makeAABB(8, 16, 8, 0, 16, 0));
				break;
			case SOUTH:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, AABBUtil.makeAABB(0, 8, 0, 8, 8, 8));
				addCollisionBoxToList(pos, entityBox, collidingBoxes, AABBUtil.makeAABB(8, 16, 8, 16, 16, 16));
				break;
			case EAST:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, AABBUtil.makeAABB(8, 8, 0, 0, 8, 16));
				addCollisionBoxToList(pos, entityBox, collidingBoxes, AABBUtil.makeAABB(8, 16, 0, 16, 16, 16));
				break;
			case WEST:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, AABBUtil.makeAABB(8, 8, 0, 16, 8, 16));
				addCollisionBoxToList(pos, entityBox, collidingBoxes, AABBUtil.makeAABB(8, 16, 0, 0, 16, 16));
				break;
			default:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, AABBUtil.makeAABB(0, 8, 0, 8, 8, 8));
				addCollisionBoxToList(pos, entityBox, collidingBoxes, AABBUtil.makeAABB(8, 16, 8, 16, 16, 16));
				break;
		}
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public boolean isTopSolid(IBlockState state) {
		return false;
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getHorizontalIndex();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
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
	public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockModelDefinition getBlockModelDefinition() {
		return new BlockModelDefinition(this, "platform").setVariant(state -> "horiz_facing=" + state.getValue(FACING) + ",inventory=false,type=" + name);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ItemModelDefinition getItemModelDefinition() {
		return new ItemBlockModelDefinition(this, "platform").prepend("horiz_facing=east,inventory=true").setVariant("type=" + name);
	}
}
