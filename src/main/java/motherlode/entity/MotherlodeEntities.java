package motherlode.entity;

import motherlode.Motherlode;
import motherlode.entity.item.EntityBomb;
import motherlode.entity.item.EntityDynamite;
import motherlode.entity.passive.EntityButterfly;
import motherlode.entity.passive.EntityFireflyBlue;
import motherlode.entity.passive.EntityFireflyGreen;
import motherlode.entity.passive.EntityFireflyNether;
import motherlode.entity.passive.EntityFireflyYellow;
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
		
		// ITEM
		
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
		
		// PASSIVE
		
		EntityEntry entityFireflyBlue = EntityEntryBuilder.create()
				.entity(EntityFireflyBlue.class)
				.id(new ResourceLocation(Motherlode.MOD_ID, "fireflyblue"), ID++)
				.name("fireflyblue")
				.tracker(64, 20, true)
				.egg(0x101050, 0x0000ff)
				.spawn(EnumCreatureType.AMBIENT, 5, 10, 30, BiomeDictionary.getBiomes(Type.FOREST))
				.build();
		event.getRegistry().register(entityFireflyBlue);
		
		EntityEntry entityFireflyGreen = EntityEntryBuilder.create()
				.entity(EntityFireflyGreen.class)
				.id(new ResourceLocation(Motherlode.MOD_ID, "fireflygreen"), ID++)
				.name("fireflygreen")
				.tracker(64, 20, true)
				.egg(0x101050, 0x00ff00)
				.spawn(EnumCreatureType.AMBIENT, 5, 10, 30, BiomeDictionary.getBiomes(Type.FOREST))
				.build();
		event.getRegistry().register(entityFireflyGreen);
		
		EntityEntry entityFireflyYellow = EntityEntryBuilder.create()
				.entity(EntityFireflyYellow.class)
				.id(new ResourceLocation(Motherlode.MOD_ID, "fireflyyellow"), ID++)
				.name("fireflyyellow")
				.tracker(64, 20, true)
				.egg(0x101050, 0xffff00)
				.spawn(EnumCreatureType.AMBIENT, 5, 10, 30, BiomeDictionary.getBiomes(Type.FOREST))
				.build();
		event.getRegistry().register(entityFireflyYellow);
		
		EntityEntry entityFireflyNether = EntityEntryBuilder.create()
				.entity(EntityFireflyNether.class)
				.id(new ResourceLocation(Motherlode.MOD_ID, "fireflynether"), ID++)
				.name("fireflynether")
				.tracker(64, 20, true)
				.egg(0x101050, 0xff0000)
				.spawn(EnumCreatureType.AMBIENT, 2, 10, 30, BiomeDictionary.getBiomes(Type.NETHER))
				.build();
		event.getRegistry().register(entityFireflyNether);
		
		EntityEntry entityButterfly = EntityEntryBuilder.create()
				.entity(EntityButterfly.class)
				.id(new ResourceLocation(Motherlode.MOD_ID, "butterfly"), ID++)
				.name("butterfly")
				.tracker(64, 20, true)
				.egg(0x202020, 0xf000f0)
				.spawn(EnumCreatureType.AMBIENT, 5, 2, 10, BiomeDictionary.getBiomes(Type.FOREST))
				.build();
		event.getRegistry().register(entityButterfly);
	}
}
