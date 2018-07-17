package contrivitive;

import contrivitive.lib.ContrivitiveConstants;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.Random;

@Mod(modid = ContrivitiveConstants.MOD_ID, name = ContrivitiveConstants.MOD_NAME, version = ContrivitiveConstants.VERSION, dependencies = ContrivitiveConstants.DEPENDENCIES)
public class Contrivitive {

	public static final Random RANDOM = new Random();

	@Mod.Instance
	public static Contrivitive instance;

	@SidedProxy(clientSide = ContrivitiveConstants.CLIENT_PROXY_CLASS, serverSide = ContrivitiveConstants.SERVER_PROXY_CLASS)
	public static ContrivitiveCommon proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
}
