package motherlode.block;

import motherlode.client.model.BlockModelDefinition;
import motherlode.client.model.ItemBlockModelDefinition;
import motherlode.client.model.ItemModelDefinition;
import motherlode.item.MotherlodeItems;
import motherlode.util.RandomUtil;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;

public class BlockThickVine extends BlockHangingClimbable implements IShearable {

	public static final PropertyEnum FLOWERS = PropertyEnum.create("flowers", EnumFlower.class);
	public boolean hasFlowers;
	public BlockStateContainer flowersState;

	public BlockThickVine(boolean hasFlowers) {
		super(hasFlowers ? "flowered_thick_vine" : "thick_vine", Material.VINE);
		this.hasFlowers = hasFlowers;
		flowersState = new BlockStateContainer(this, FLOWERS);
		setDefaultState(getBlockState().getBaseState());
	}

	@Override
	public BlockStateContainer getBlockState() {
		return hasFlowers ? flowersState : super.getBlockState();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return hasFlowers ? this.getDefaultState().withProperty(FLOWERS, EnumFlower.values()[meta]) : super.getStateFromMeta(meta);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return hasFlowers ? ((EnumFlower) state.getValue(FLOWERS)).ordinal() : super.getMetaFromState(state);
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		drops.clear();
		if (RandomUtil.chance(1, 5)) {
			drops.add(new ItemStack(MotherlodeItems.PLANT_FIBER));
		}
	}

	@Override
	public boolean isShearable(
		@Nonnull
			ItemStack item, IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Nonnull
	@Override
	public List<ItemStack> onSheared(
		@Nonnull
			ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return NonNullList.withSize(1, new ItemStack(this));
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		if (hasFlowers) {
			for (EnumFlower flower : EnumFlower.values()) {
				items.add(new ItemStack(this, 1, flower.ordinal()));
			}
		} else {
			super.getSubBlocks(itemIn, items);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockModelDefinition getBlockModelDefinition() {
		if (hasFlowers) {
			return new BlockModelDefinition(this, "thick_vine").append("inventory=false")
				.setIBlockColor((state, worldIn, pos, tintIndex) -> {
					if (tintIndex == 0) {
						if (worldIn != null && pos != null) {
							return BiomeColorHelper.getFoliageColorAtPos(worldIn, pos);
						}
						return ColorizerFoliage.getFoliageColorBasic();
					}
					return 0xFFFFFFFF;
				});
		}
		return new BlockModelDefinition(this, "thick_vine").setVariant("flowers=none,inventory=false")
			.setIBlockColor((state, worldIn, pos, tintIndex) -> {
				if (tintIndex == 0) {
					if (worldIn != null && pos != null) {
						return BiomeColorHelper.getFoliageColorAtPos(worldIn, pos);
					}
					return ColorizerFoliage.getFoliageColorBasic();
				}
				return 0xFFFFFFFF;
			});
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ItemModelDefinition getItemModelDefinition() {
		if (hasFlowers) {
			return new ItemBlockModelDefinition(getBlockModelDefinition(), FLOWERS.getAllowedValues().size() - 1).append("inventory=true")
				.setIItemColor((stack, tintIndex) -> {
					if (tintIndex == 0) {
						IBlockState iblockstate = ((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
						return Minecraft.getMinecraft().getBlockColors().colorMultiplier(iblockstate, null, null, tintIndex);
					}
					return 0xFFFFFFFF;
				});
		}
		return new ItemBlockModelDefinition(this, "thick_vine").setVariant("flowers=none,inventory=true")
			// IItemColor
			.setIItemColor((stack, tintIndex) -> {
				if (tintIndex == 0) {
					IBlockState iblockstate = ((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
					return Minecraft.getMinecraft().getBlockColors().colorMultiplier(iblockstate, null, null, tintIndex);
				}
				return 0xFFFFFFFF;
			});
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		if (hasFlowers) {
			return new ItemStack(this, 1, getMetaFromState(state));
		}
		return super.getPickBlock(state, target, world, pos, player);
	}

	public enum EnumFlower implements IStringSerializable {
		RED_DARK("red_dark"),
		RED_LIGHT("red_light"),
		ORANGE_LIGHT("orange_light"),
		YELLOW_DARK("yellow_dark"),
		YELLOW_LIGHT("yellow_light"),
		MIX_RED_YELLOW("mix_red_yellow"),
		GREEN_DARK("green_dark"),
		BLUE_DARK("blue_dark"),
		LIGHT_BLUE_DARK("light_blue_dark"),
		LIGHT_BLUE_LIGHT("light_blue_light"),
		PINK_DARK("pink_dark"),
		PINK_LIGHT("pink_light"),
		MIX("mix"),
		BLACK_LIGHT("black_light"),
		WHITE_DARK("white_dark"),
		MIX_BLACK_WHITE("mix_black_white");
		String name;

		EnumFlower(String name) {
			this.name = name;
		}

		@Override
		public String getName() {
			return name;
		}

		public static String[] getAllNames() {
			String[] strings = new String[values().length];
			for (EnumFlower flower : values()) {
				strings[flower.ordinal()] = flower.getName();
			}
			return strings;
		}
	}
}
