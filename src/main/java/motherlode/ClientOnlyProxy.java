package motherlode;

public class ClientOnlyProxy extends CommonProxy {

	public void preInit() {
		super.preInit();
		// Call all object types preInitClientOnly()
		motherlode.common.item.StartupClientOnly.preInitClientOnly();
		motherlode.common.entity.StartupClientOnly.preInitClientOnly();
		motherlode.client.render.StartupClientOnly.preInitClientOnly();
	}
	
	public void init() {
		super.init();
		// Call all object types initClientOnly()
		motherlode.common.item.StartupClientOnly.initClientOnly();
		motherlode.common.entity.StartupClientOnly.initClientOnly();
		motherlode.client.render.StartupClientOnly.initClientOnly();
	}
	
	public void postInit() {
		super.postInit();
		// Call all object types postInitClientOnly()
		motherlode.common.item.StartupClientOnly.postInitClientOnly();
		motherlode.common.entity.StartupClientOnly.postInitClientOnly();
		motherlode.client.render.StartupClientOnly.postInitClientOnly();
	}
	
//	public boolean isDedicatedServer = false;
	@Override
	public boolean isDedicatedServer() {
		return false;
	}
	
}
