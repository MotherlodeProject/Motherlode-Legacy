package motherlode.block;

import java.util.List;

import javax.annotation.Nullable;

import motherlode.client.model.BlockModelDefinition;
import motherlode.client.model.ItemBlockModelDefinition;
import motherlode.client.model.ItemModelDefinition;
import motherlode.client.model.MRLBuilder;
import motherlode.tileentity.TileEntityPot;
import motherlode.util.ColorUtil;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPot extends BlockMotherlode {

	public static final PropertyInteger PATTERN = PropertyInteger.create("pattern", 0, 13);

	public BlockPot() {
		super("pot", Material.ROCK);
		setResistance(5F);
		setHardness(2F);
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
			((TileEntityPot) tileEntity).setPattern(MathHelper.getInt(worldIn.rand, 0, PATTERN.getAllowedValues().size() - 1));
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
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced) {
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("PatternColor")) {
			tooltip.add(TextFormatting.GRAY + I18n.format("item.fireworksCharge." + EnumDyeColor.byMetadata(stack.getTagCompound().getInteger("PatternColor")).getUnlocalizedName()) + " Pattern");
			tooltip.add(TextFormatting.GRAY + "Pattern #" + stack.getTagCompound().getInteger("Pattern"));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockModelDefinition getBlockModelDefinition() {
		return new BlockModelDefinition(this)
			// IBlockColor
			.setIBlockColor((state, worldIn, pos, tintIndex) -> {
				if (worldIn != null && pos != null) {
					if (tintIndex == 0) {
						TileEntity entity = worldIn.getTileEntity(pos);
						if (entity instanceof TileEntityPot) {
							return ColorUtil.desaturate(((TileEntityPot) entity).getColor().getColorValue(), 0.2F);
						}
					}
					if (tintIndex == 1) {
						TileEntity entity = worldIn.getTileEntity(pos);
						if (entity instanceof TileEntityPot) {
							return ColorUtil.desaturate(((TileEntityPot) entity).getPatternColor().getColorValue(), 0.5F);
						}
					}
				}
				return 0xFFFFFFF;
			});
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ItemModelDefinition getItemModelDefinition() {
		return new ItemBlockModelDefinition(this)
			// Mesh Definition
			.setMeshDefinition(
				stack -> {
					if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Pattern")) {
						return new MRLBuilder("pot_item").setVariant("pattern=" + stack.getTagCompound().getInteger("Pattern")).build();
					}
					return new MRLBuilder("pot_item").setVariant("pattern=random").build();
				},
				// Get Item Variants
				list -> {
					for (Integer i : BlockPot.PATTERN.getAllowedValues()) {
						list.add(new MRLBuilder("pot_item").setVariant("pattern=" + i).build());
					}
					list.add(new MRLBuilder("pot_item").setVariant("pattern=random").build());
				})
			// IItemColor
			.setIItemColor(
				(stack, tintIndex) -> {
					if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Color")) {
						if (tintIndex == 0) {
							return ColorUtil.desaturate((EnumDyeColor.byMetadata(stack.getTagCompound().getInteger("Color")).getColorValue()), 0.2F);
						}
						if (tintIndex == 1) {
							return ColorUtil.desaturate((EnumDyeColor.byMetadata(stack.getTagCompound().getInteger("PatternColor")).getColorValue()), 0.5F);
						}
					}
					return 0xFFFFFF;
				});
	}
}
