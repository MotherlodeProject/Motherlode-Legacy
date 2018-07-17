package motherlode.block;

import motherlode.client.model.BlockModelDefinition;
import motherlode.client.model.ItemBlockModelDefinition;
import motherlode.client.model.ItemModelDefinition;
import motherlode.util.InitUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public abstract class BlockMotherlodeSlab extends BlockSlab implements IModeledBlock {
	public final String name;
	public final String blockstate;

	public Block halfslab;

	public static final PropertyEnum<BlockMotherlodeSlab.Variant> VARIANT = PropertyEnum.create("variant", BlockMotherlodeSlab.Variant.class);

	public static final String[] HARDNESS_MAPPINGS = new String[] { "q", "field_149782_v", "blockHardness" };
	public static final String[] RESISTANCE_MAPPINGS = new String[] { "r", "field_149781_w", "blockResistance" };

	public BlockMotherlodeSlab(String name, String blockstate, Block baseBlock) {
		super(baseBlock.getMaterial(baseBlock.getDefaultState()));
		this.name = name;
		this.blockstate = blockstate;
		IBlockState iblockstate = this.blockState.getBaseState();

		if (!this.isDouble()) {
			iblockstate = iblockstate.withProperty(HALF, EnumBlockHalf.BOTTOM);
			InitUtil.setup(this, name + "_slab");
			halfslab = this;
		} else {
			InitUtil.setup(this, name + "_double_slab");
		}
		if (this.blockMaterial == Material.ROCK) {
			setHarvestLevel("pickaxe", 0);
		}
		setHardness(ReflectionHelper.getPrivateValue(Block.class, baseBlock, HARDNESS_MAPPINGS));
		setResistance(ReflectionHelper.getPrivateValue(Block.class, baseBlock, RESISTANCE_MAPPINGS));
		setSoundType(baseBlock.getSoundType());
		this.setDefaultState(iblockstate);
		useNeighborBrightness = true;
	}

	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(halfslab);
	}

	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(halfslab);
	}

	public IBlockState getStateFromMeta(int meta) {
		IBlockState iblockstate = this.getDefaultState();
		if (!this.isDouble()) {
			iblockstate = iblockstate.withProperty(HALF, (meta & 8) == 0 ? EnumBlockHalf.BOTTOM : EnumBlockHalf.TOP);
		}
		return iblockstate;
	}

	public int getMetaFromState(IBlockState state) {
		int i = 0;
		if (!this.isDouble() && state.getValue(HALF) == EnumBlockHalf.TOP) {
			i |= 8;
		}
		return i;
	}

	protected BlockStateContainer createBlockState() {
		return this.isDouble() ? new BlockStateContainer(this, VARIANT) : new BlockStateContainer(this, HALF, VARIANT);
	}

	public String getUnlocalizedName(int meta) {
		return super.getUnlocalizedName();
	}

	public static class Double extends BlockMotherlodeSlab {

		public Double(String name, Block baseBlock, Block half) {
			this(name, "", baseBlock, half);
		}

		public Double(String name, String blockstate, Block baseBlock, Block half) {
			super(name, blockstate, baseBlock);
			this.halfslab = half;
		}

		public boolean isDouble() {
			return true;
		}
	}

	public static class Half extends BlockMotherlodeSlab {

		public Half(String name, Block baseBlock) {
			this(name, "", baseBlock);
		}

		public Half(String name, String blockstate, Block baseBlock) {
			super(name, blockstate, baseBlock);
		}

		public boolean isDouble() {
			return false;
		}
	}

	@Override
	public Comparable<?> getTypeForItem(ItemStack stack) {
		return Variant.DEFAULT;
	}

	@Override
	public IProperty<?> getVariantProperty() {
		return VARIANT;
	}

	public static enum Variant implements IStringSerializable {
		DEFAULT;

		public String getName() {
			return "default";
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockModelDefinition getBlockModelDefinition() {
		if (isDouble()) {
			if (blockstate.isEmpty()) {
				return new BlockModelDefinition(this, VARIANT).prepend("horiz_facing=ignore").append("stairs_half=ignore,stairs_shape=ignore").setVariant("slab_half=double");
			}
			return new BlockModelDefinition(this, blockstate, VARIANT).prepend("horiz_facing=ignore").append("stairs_half=ignore,stairs_shape=ignore").setVariant("slab_half=double").append("type=" + name);
		} else {
			if (blockstate.isEmpty()) {
				return new BlockModelDefinition(this, VARIANT).prepend("horiz_facing=ignore").append("stairs_half=ignore,stairs_shape=ignore").setVariant(state -> "slab_half=" + state.getValue(HALF).getName());
			}
			return new BlockModelDefinition(this, blockstate, VARIANT).prepend("horiz_facing=ignore").append("stairs_half=ignore,stairs_shape=ignore").append("type=" + name).setVariant(state -> "slab_half=" + state.getValue(HALF).getName());
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ItemModelDefinition getItemModelDefinition() {
		if (isDouble()) {
			return null;
		}
		if (blockstate.isEmpty()) {
			return new ItemBlockModelDefinition(this).setVariant("slab_half=bottom");
		}
		return new ItemBlockModelDefinition(this, blockstate).append("type=" + name).setVariant("slab_half=bottom,stair=ignore");
	}
}
