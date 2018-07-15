package motherlode.client;

import motherlode.CommonProxy;
import motherlode.client.model.MotherlodeModels;
import motherlode.client.render.MotherlodeRenders;
import net.minecraftforge.common.MinecraftForge;

@SuppressWarnings("MethodCallSideOnly")
public class ClientProxy extends CommonProxy {

	public void preInit() {
		super.preInit();
		MinecraftForge.EVENT_BUS.register(MotherlodeModels.class);
		MotherlodeRenders.registerEntityRenders();
	}

	public void init() {
		super.init();
		MotherlodeModels.registerColorHandlers();
	}

	public void postInit() {
		super.postInit();
	}

	@Override
	public boolean isDedicatedServer() {
		return false;
	}

}
