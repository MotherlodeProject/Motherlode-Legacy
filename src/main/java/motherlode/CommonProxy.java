package motherlode;

import static motherlode.Motherlode.instance;

import motherlode.block.MotherlodeBlocks;
import motherlode.gui.MotherlodeGuiHandler;
import motherlode.item.MotherlodeItems;
import motherlode.network.MotherlodeNetwork;
import motherlode.tileentity.TileEntityPot;
import motherlode.world.biome.MotherlodeBiomes;
import motherlode.world.gen.MotherlodeWorldGenerators;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class CommonProxy {

	public void preInit() {
		MotherlodeBlocks.load();
		MotherlodeItems.load();
		MotherlodeBiomes.load();
		MotherlodeNetwork.registerMessages();
		GameRegistry.registerTileEntity(TileEntityPot.class, new ResourceLocation(Motherlode.MOD_ID, "pot"));
	}

	public void init() {
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new MotherlodeGuiHandler());
		MotherlodeWorldGenerators.registerWorldGens();
	}

	public void postInit() {

	}

	abstract public boolean isDedicatedServer();

}
