package motherlode.block;

import motherlode.client.ModelCompound;
import motherlode.tileentity.TileEntityPot;
import motherlode.util.InitUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class BlockPot extends Block {

	public static final PropertyInteger PATTERN = PropertyInteger.create("pattern", 0, 3);

	public BlockPot() {
		super(Material.ROCK);
		InitUtil.setup(this, "pot");
		InitUtil.setModel(new ModelCompound(this).doNotRegisterItemModel());
		setHardness(2F);
		setResistance(5F);
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
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		TileEntity tile = worldIn.getTileEntity(pos);
		if (tile instanceof TileEntityPot) {
			state = state.withProperty(PATTERN, ((TileEntityPot) tile).getPattern());
		}
		return state;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState();
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, PATTERN);
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityPot();
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		TileEntity tileEntity = this.createTileEntity(worldIn, state);
		worldIn.setTileEntity(pos, tileEntity);
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Color")) {
			((TileEntityPot) tileEntity).setColor(EnumDyeColor.byMetadata(stack.getTagCompound().getInteger("Color")));
			((TileEntityPot) tileEntity).setPatternColor(EnumDyeColor.byMetadata(stack.getTagCompound().getInteger("PatternColor")));
			((TileEntityPot) tileEntity).setPattern(stack.getTagCompound().getInteger("Pattern"));
		} else {
			((TileEntityPot) tileEntity).setColor(EnumDyeColor.byMetadata(MathHelper.getInt(worldIn.rand, 0, 15)));
			((TileEntityPot) tileEntity).setPatternColor(EnumDyeColor.byMetadata(MathHelper.getInt(worldIn.rand, 0, 15)));
			((TileEntityPot) tileEntity).setPattern(MathHelper.getInt(worldIn.rand, 0, 3));
		}
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		ItemStack stack = new ItemStack(this);
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof TileEntityPot) {
			stack.getTagCompound().setInteger("Color", ((TileEntityPot) tile).getColor().getMetadata());
			stack.getTagCompound().setInteger("PatternColor", ((TileEntityPot) tile).getPatternColor().getMetadata());
			stack.getTagCompound().setInteger("Pattern", ((TileEntityPot) tile).getPattern());
		}
		return stack;
	}

	@Override
	public void addInformation(ItemStack stack,
	                           @Nullable
		                           World player, List<String> tooltip, ITooltipFlag advanced) {
		super.addInformation(stack, player, tooltip, advanced);
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("PatternColor")) {
			tooltip.add(TextFormatting.GRAY + I18n.translateToLocal("item.fireworksCharge." + EnumDyeColor.byMetadata(stack.getTagCompound().getInteger("PatternColor")).getUnlocalizedName()) + " Pattern");
			tooltip.add(TextFormatting.GRAY + "Pattern #" + stack.getTagCompound().getInteger("Pattern"));
		}
	}
}
