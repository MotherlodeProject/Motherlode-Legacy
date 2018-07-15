package motherlode.client.render;

import motherlode.entity.EntityBomb;
import motherlode.entity.EntityDynamite;
import motherlode.entity.passive.EntityFirefly;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class MotherlodeRenders {

	public static void registerEntityRenders() {
		RenderingRegistry.registerEntityRenderingHandler(EntityBomb.class, RenderBomb.Factory.INSTANCE);
		RenderingRegistry.registerEntityRenderingHandler(EntityDynamite.class, RenderDynamite.Factory.INSTANCE);
		
		RenderingRegistry.registerEntityRenderingHandler(EntityFirefly.class, RenderFirefly.Factory.INSTANCE);
	}
}
