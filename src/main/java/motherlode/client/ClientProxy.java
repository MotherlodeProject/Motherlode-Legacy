package motherlode.client;

import motherlode.CommonProxy;
import motherlode.client.render.MotherlodeRenders;

public class ClientProxy extends CommonProxy {

	public void preInit() {
		super.preInit();
		MotherlodeRenders.registerEntityRenders();
	}

	public void init() {
		super.init();

	}

	public void postInit() {
		super.postInit();

	}

	@Override
	public boolean isDedicatedServer() {
		return false;
	}

}
