package motherlode.client.render;

import motherlode.common.entity.EntityBomb;
import motherlode.common.entity.EntityDynamite;
import motherlode.common.item.ItemDynamite;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class StartupClientOnly {
	
	public static void preInitClientOnly() {
		RenderingRegistry.registerEntityRenderingHandler(EntityBomb.class, RenderBomb.Factory.INSTANCE);
		
		RenderingRegistry.registerEntityRenderingHandler(EntityDynamite.class, RenderDynamite.Factory.INSTANCE);
	}
	
	public static void initClientOnly() {
	}
	
	public static void postInitClientOnly() {
	}
}
