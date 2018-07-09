package motherlode;

import motherlode.block.MotherlodeBlocks;
import motherlode.item.MotherlodeItems;
import motherlode.tileentity.TileEntityPot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class CommonProxy {

	public void preInit() {
		MotherlodeBlocks.load();
		MotherlodeItems.load();
		GameRegistry.registerTileEntity(TileEntityPot.class, new ResourceLocation(Motherlode.MOD_ID, "pot"));
	}

	public void init() {

	}

	public void postInit() {

	}

	abstract public boolean isDedicatedServer();

}
