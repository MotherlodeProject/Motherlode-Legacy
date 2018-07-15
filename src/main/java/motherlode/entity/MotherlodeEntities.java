package motherlode.entity;

import motherlode.Motherlode;
import motherlode.entity.passive.EntityFirefly;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

@Mod.EventBusSubscriber(modid = Motherlode.MOD_ID)
public class MotherlodeEntities {
	
	private static int ID = 0;

	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
		EntityEntry entityBomb = EntityEntryBuilder.create()
			.entity(EntityBomb.class)
			.id(new ResourceLocation(Motherlode.MOD_ID, "bomb"), ID++)
			.name("bomb")
			.tracker(64, 20, true)
			.build();
		event.getRegistry().register(entityBomb);

		EntityEntry entityDynamite = EntityEntryBuilder.create()
			.entity(EntityDynamite.class)
			.id(new ResourceLocation(Motherlode.MOD_ID, "dynamite"), ID++)
			.name("dynamite")
			.tracker(64, 20, true)
			.build();
		event.getRegistry().register(entityDynamite);
		
		EntityEntry entityFirefly = EntityEntryBuilder.create()
			.entity(EntityFirefly.class)
			.id(new ResourceLocation(Motherlode.MOD_ID, "firefly"), ID++)
			.name("firefly")
			.tracker(64, 20, true)
			.egg(0, 0)
			.spawn(EnumCreatureType.AMBIENT, 10, 5, 15, BiomeDictionary.getBiomes(Type.FOREST))
			.build();
		event.getRegistry().register(entityFirefly);
	}
}
