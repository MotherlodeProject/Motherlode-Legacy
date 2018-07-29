package motherlode.client;

import motherlode.CommonProxy;
import motherlode.client.model.MotherlodeModels;
import motherlode.client.render.MotherlodeRenders;
import net.minecraftforge.common.MinecraftForge;

@SuppressWarnings({ "VariableUseSideOnly", "MethodCallSideOnly", "NewExpressionSideOnly" })
public class ClientProxy extends CommonProxy {

	@Override
	public void preInit() {
		MinecraftForge.EVENT_BUS.register(MotherlodeModels.class);
		MotherlodeRenders.registerEntityRenders();
	}

	@Override
	public void init() {
		MotherlodeModels.registerColorHandlers();
	}

	@Override
	public void postInit() {
	}

	@Override
	public boolean isDedicatedServer() {
		return false;
	}

}
