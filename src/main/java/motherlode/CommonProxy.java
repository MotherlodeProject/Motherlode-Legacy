package motherlode;

public abstract class CommonProxy {

	public void preInit() {
		// Call all object types preInitCommon()
		motherlode.common.item.StartupCommon.preInitCommon();
	}
	
	public void init() {
		// Call all object types initCommon()
		motherlode.common.item.StartupCommon.initCommon();

	}
	
	public void postInit() {
		// Call all object types postInitCommon()
		motherlode.common.item.StartupCommon.postInitCommon();

	}
	
	abstract public boolean isDedicatedServer();
	
}
