package motherlode.client.render;

import motherlode.entity.item.EntityBomb;
import motherlode.entity.item.EntityDynamite;
import motherlode.entity.passive.EntityFirefly;
import motherlode.entity.passive.EntityFireflyBlue;
import motherlode.entity.passive.EntityFireflyGreen;
import motherlode.entity.passive.EntityFireflyNether;
import motherlode.entity.passive.EntityFireflyYellow;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class MotherlodeRenders {

	public static void registerEntityRenders() {
		RenderingRegistry.registerEntityRenderingHandler(EntityBomb.class, RenderBomb.Factory.INSTANCE);
		RenderingRegistry.registerEntityRenderingHandler(EntityDynamite.class, RenderDynamite.Factory.INSTANCE);
		
		RenderingRegistry.registerEntityRenderingHandler(EntityFirefly.class, RenderFirefly.Factory.INSTANCE);
		RenderingRegistry.registerEntityRenderingHandler(EntityFireflyBlue.class, RenderFirefly.Factory.INSTANCE);
		RenderingRegistry.registerEntityRenderingHandler(EntityFireflyGreen.class, RenderFirefly.Factory.INSTANCE);
		RenderingRegistry.registerEntityRenderingHandler(EntityFireflyYellow.class, RenderFirefly.Factory.INSTANCE);
		RenderingRegistry.registerEntityRenderingHandler(EntityFireflyNether.class, RenderFirefly.Factory.INSTANCE);
	}
}
