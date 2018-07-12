package motherlode.client;

import motherlode.CommonProxy;
import motherlode.block.MotherlodeBlocks;
import motherlode.client.render.MotherlodeRenders;
import motherlode.tileentity.TileEntityPot;
import motherlode.util.ColorUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.biome.BiomeColorHelper;

public class ClientProxy extends CommonProxy {

	public void preInit() {
		super.preInit();
		MotherlodeRenders.registerEntityRenders();
	}

	public void init() {
		super.init();
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
			if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Color")) {
				if (tintIndex == 0) {
					return ColorUtil.desaturate((EnumDyeColor.byMetadata(stack.getTagCompound().getInteger("Color")).getColorValue()), 0.2F);
				}
				if (tintIndex == 1) {
					return ColorUtil.desaturate((EnumDyeColor.byMetadata(stack.getTagCompound().getInteger("PatternColor")).getColorValue()), 0.5F);
				}
			}
			return 0xFFFFFF;
		}, Item.getItemFromBlock(MotherlodeBlocks.POT));
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, worldIn, pos, tintIndex) -> {
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
		}, MotherlodeBlocks.POT);
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
			IBlockState iblockstate = ((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
			return Minecraft.getMinecraft().getBlockColors().colorMultiplier(iblockstate, null, null, tintIndex);
		}, MotherlodeBlocks.CAVE_VINE);
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, worldIn, pos, tintIndex) -> {
				if (tintIndex == 0 && worldIn != null && pos != null) {
					return BiomeColorHelper.getFoliageColorAtPos(worldIn, pos);
				}
				return ColorizerFoliage.getFoliageColorBasic();
			},
			MotherlodeBlocks.CAVE_VINE);
	}

	public void postInit() {
		super.postInit();
	}

	@Override
	public boolean isDedicatedServer() {
		return false;
	}

}
