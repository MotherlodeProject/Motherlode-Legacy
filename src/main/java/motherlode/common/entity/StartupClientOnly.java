package motherlode.common.entity;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class StartupClientOnly {

	public static void preInitClientOnly() {
		EntityEntry entityBomb = EntityEntryBuilder.create()
				.entity(EntityBomb.class)
				.id(new ResourceLocation("motherlode", "textures/item/item_bomb_0.png"), 1)
				.name("bomb") // unlocalized
//				.egg(0xFFFFFF, 0xFFFFFF)
				.tracker(64, 20, true) // range, updateFrequency, sendVelocityUpdates 
				.build();
		ForgeRegistries.ENTITIES.register(entityBomb);
		
		EntityEntry entityDynamite = EntityEntryBuilder.create()
				.entity(EntityDynamite.class)
				.id(new ResourceLocation("motherlode", "textures/item/item_dynamite_0.png"), 1)
				.name("dynamite")
				.tracker(64, 20, true)
				.build();
		ForgeRegistries.ENTITIES.register(entityDynamite);
	}
	
	public static void initClientOnly() {
	}
	
	public static void postInitClientOnly() {
	}
	
}
