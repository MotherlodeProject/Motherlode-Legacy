package motherlode.entity;

import motherlode.Motherlode;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

@Mod.EventBusSubscriber(modid = Motherlode.MOD_ID)
public class MotherlodeEntities {

	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
		EntityEntry entityBomb = EntityEntryBuilder.create()
			.entity(EntityBomb.class)
			.id(new ResourceLocation(Motherlode.MOD_ID, "bomb"), 1)
			.name("bomb")
			.tracker(64, 20, true)
			.build();
		event.getRegistry().register(entityBomb);

		EntityEntry entityDynamite = EntityEntryBuilder.create()
			.entity(EntityDynamite.class)
			.id(new ResourceLocation(Motherlode.MOD_ID, "dynamite"), 1)
			.name("dynamite")
			.tracker(64, 20, true)
			.build();
		event.getRegistry().register(entityDynamite);
	}
}
