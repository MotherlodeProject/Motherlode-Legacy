package motherlode.block;

import motherlode.client.model.BlockModelDefinition;
import motherlode.client.model.ItemBlockModelDefinition;
import motherlode.client.model.ItemModelDefinition;
import motherlode.util.AABBUtil;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class BlockPlatform extends BlockMotherlode {

	public final String name;

	public BlockPlatform(String name) {
		super(name + "_platform");
		this.name = name;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABBUtil.makeAABB(0, 11, 0, 16, 16, 16);
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

		addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0, 1, 0, 1, 1, 1));
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return face == EnumFacing.UP ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
	}

	@Override
	public boolean isTopSolid(IBlockState state) {
		return true;
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
		return new BlockModelDefinition(this, "platform").prepend("horiz_facing=ignore,inventory=false").setVariant("type=" + name);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ItemModelDefinition getItemModelDefinition() {
		return new ItemBlockModelDefinition(this, "platform").prepend("horiz_facing=ignore,inventory=true").setVariant("type=" + name);
	}
}
