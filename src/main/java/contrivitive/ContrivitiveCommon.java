package contrivitive;

import contrivitive.gui.ContrivitiveGuiHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ContrivitiveCommon {

	public void preInit(FMLPreInitializationEvent event) {

	}

	public void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(Contrivitive.instance, new ContrivitiveGuiHandler());
	}

	public void postInit(FMLPostInitializationEvent event) {

	}

}
