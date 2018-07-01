package motherlode.common.entity;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class StartupClientOnly {

	public static void preInitClientOnly() {
		EntityEntry entitybomb = EntityEntryBuilder.create()
				.entity(EntityBomb.class)
				.id(new ResourceLocation("motherlode", "textures/item/item_bomb.png"), 1)
				.name("bomb") // unlocalized
//				.egg(0xFFFFFF, 0xFFFFFF)
				.tracker(64, 20, true) // range, updateFrequency, sendVelocityUpdates 
				.build();
		ForgeRegistries.ENTITIES.register(entitybomb);
	}
	
	public static void initClientOnly() {
	}
	
	public static void postInitClientOnly() {
	}
	
}
