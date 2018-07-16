package motherlode.block;

import motherlode.client.model.BlockModelDefinition;
import motherlode.client.model.ItemBlockModelDefinition;
import motherlode.client.model.ItemModelDefinition;
import motherlode.client.model.MRLBuilder;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLantern extends BlockMotherlode {

	public static final PropertyBool LIT = PropertyBool.create("lit");

	public BlockLantern() {
		super("lantern");
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(LIT) ? 0 : 1;
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		ItemStack stack = placer.getHeldItem(hand);
		if (!stack.isEmpty() && stack.hasTagCompound()) {
			return getDefaultState().withProperty(LIT, stack.getTagCompound().getBoolean("Lit"));
		}
		return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return meta == 0 ? getDefaultState().withProperty(LIT, false) : getDefaultState().withProperty(LIT, true);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		worldIn.setBlockState(pos, state.withProperty(LIT, !state.getValue(LIT)));
		return true;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, LIT);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		return state.getValue(LIT) ? 15 : 0;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockModelDefinition getBlockModelDefinition() {
		return new BlockModelDefinition(this, "lantern").prepend("inventory=false");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ItemModelDefinition getItemModelDefinition() {
		return new ItemBlockModelDefinition(this).setMeshDefinition(stack -> {
			if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Lit")) {
				return new MRLBuilder(name).setVariant("lit=" + stack.getTagCompound().getBoolean("Lit")).prepend("inventory=true").build();
			}
			return new MRLBuilder(name).setVariant("lit=false").prepend("inventory=true").build();
		}, list -> {
			list.add(new MRLBuilder(name).setVariant("lit=true").prepend("inventory=true").build());
			list.add(new MRLBuilder(name).setVariant("lit=false").prepend("inventory=true").build());
		});
	}
}
