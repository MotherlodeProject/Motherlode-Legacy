package motherlode;

public class DedicatedServerProxy extends CommonProxy {
	
	public void preInit() {
		super.preInit();
	}
	
	public void init() {
		super.init();
	}
	
	public void postInit() {
		super.postInit();
	}
	
//	public boolean isDedicatedServer = true;
	@Override
	public boolean isDedicatedServer() {
		return true;
	}

}
