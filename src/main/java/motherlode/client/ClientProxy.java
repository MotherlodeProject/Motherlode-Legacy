package motherlode.client;

import motherlode.CommonProxy;
import motherlode.block.MotherlodeBlocks;
import motherlode.client.render.MotherlodeRenders;
import motherlode.tileentity.TileEntityPot;
import motherlode.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;

public class ClientProxy extends CommonProxy {

	public void preInit() {
		super.preInit();
		MotherlodeRenders.registerEntityRenders();
	}

	public void init() {
		super.init();
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, worldIn, pos, tintIndex) -> {
			if (tintIndex == 0) {
				TileEntity entity = worldIn.getTileEntity(pos);
				if (entity instanceof TileEntityPot) {
					return ColorUtil.desaturate(((TileEntityPot) entity).getColor().getColorValue(), 0.55F);
				}
			}
			if (tintIndex == 1) {
				TileEntity entity = worldIn.getTileEntity(pos);
				if (entity instanceof TileEntityPot) {
					return ColorUtil.desaturate(((TileEntityPot) entity).getPatternColor().getColorValue(), 0.6F);
				}
			}
			return 0xFFFFFFF;
		}, MotherlodeBlocks.POT);
	}

	public void postInit() {
		super.postInit();
	}

	@Override
	public boolean isDedicatedServer() {
		return false;
	}

}
