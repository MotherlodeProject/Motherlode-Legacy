package motherlode.entity;

import motherlode.Motherlode;
import motherlode.entity.item.EntityBomb;
import motherlode.entity.item.EntityDynamite;
import motherlode.entity.passive.*;
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

		registerItemEntities(event);
		registerPassiveEntities(event);
	}

	private static void registerItemEntities(RegistryEvent.Register<EntityEntry> event) {

		EntityEntry bomb = EntityEntryBuilder.create()
			.entity(EntityBomb.class)
			.id(new ResourceLocation(Motherlode.MOD_ID, "bomb"), ID++)
			.name("bomb")
			.tracker(64, 20, true)
			.build();
		event.getRegistry().register(bomb);

		EntityEntry dynamite = EntityEntryBuilder.create()
			.entity(EntityDynamite.class)
			.id(new ResourceLocation(Motherlode.MOD_ID, "dynamite"), ID++)
			.name("dynamite")
			.tracker(64, 20, true)
			.build();
		event.getRegistry().register(dynamite);

	}

	private static void registerPassiveEntities(RegistryEvent.Register<EntityEntry> event) {

		EntityEntry blueFirefly = EntityEntryBuilder.create()
			.entity(EntityFireflyBlue.class)
			.id(new ResourceLocation(Motherlode.MOD_ID, "blue_firefly"), ID++)
			.name("blue_firefly")
			.tracker(64, 20, true)
			.egg(0x101050, 0x0000ff)
			.spawn(EnumCreatureType.AMBIENT, 5, 10, 30, BiomeDictionary.getBiomes(Type.FOREST))
			.build();
		event.getRegistry().register(blueFirefly);

		EntityEntry greenFirefly = EntityEntryBuilder.create()
			.entity(EntityFireflyGreen.class)
			.id(new ResourceLocation(Motherlode.MOD_ID, "green_firefly"), ID++)
			.name("green_firefly")
			.tracker(64, 20, true)
			.egg(0x101050, 0x00ff00)
			.spawn(EnumCreatureType.AMBIENT, 5, 10, 30, BiomeDictionary.getBiomes(Type.FOREST))
			.build();
		event.getRegistry().register(greenFirefly);

		EntityEntry yellowFirefly = EntityEntryBuilder.create()
			.entity(EntityFireflyYellow.class)
			.id(new ResourceLocation(Motherlode.MOD_ID, "yellow_firefly"), ID++)
			.name("yellow_firefly")
			.tracker(64, 20, true)
			.egg(0x101050, 0xffff00)
			.spawn(EnumCreatureType.AMBIENT, 5, 10, 30, BiomeDictionary.getBiomes(Type.FOREST))
			.build();
		event.getRegistry().register(yellowFirefly);

		EntityEntry infernoFirefly = EntityEntryBuilder.create()
			.entity(EntityFireflyInferno.class)
			.id(new ResourceLocation(Motherlode.MOD_ID, "inferno_firefly"), ID++)
			.name("inferno_firefly")
			.tracker(64, 20, true)
			.egg(0x101050, 0xff0000)
			.spawn(EnumCreatureType.MONSTER, 50, 1, 5, BiomeDictionary.getBiomes(Type.NETHER))
			.build();
		event.getRegistry().register(infernoFirefly);

		EntityEntry butterfly = EntityEntryBuilder.create()
			.entity(EntityButterfly.class)
			.id(new ResourceLocation(Motherlode.MOD_ID, "butterfly"), ID++)
			.name("butterfly")
			.tracker(64, 20, true)
			.egg(0x202020, 0xf000f0)
			.spawn(EnumCreatureType.AMBIENT, 5, 5, 15, BiomeDictionary.getBiomes(Type.FOREST))
			.build();
		event.getRegistry().register(butterfly);

		EntityEntry exoticButterfly = EntityEntryBuilder.create()
			.entity(EntityButterflyExotic.class)
			.id(new ResourceLocation(Motherlode.MOD_ID, "exotic_butterfly"), ID++)
			.name("exotic_butterfly")
			.tracker(64, 20, true)
			.egg(0x404040, 0x10f050)
			.spawn(EnumCreatureType.AMBIENT, 5, 5, 15, BiomeDictionary.getBiomes(Type.JUNGLE))
			.build();
		event.getRegistry().register(exoticButterfly);

		EntityEntry moth = EntityEntryBuilder.create()
			.entity(EntityMoth.class)
			.id(new ResourceLocation(Motherlode.MOD_ID, "moth"), ID++)
			.name("moth")
			.tracker(64, 20, true)
			.egg(0x202020, 0x808010)
			.spawn(EnumCreatureType.AMBIENT, 5, 5, 15, BiomeDictionary.getBiomes(Type.PLAINS))
			.build();
		event.getRegistry().register(moth);

		EntityEntry sandLizard = EntityEntryBuilder.create()
			.entity(EntityLizard.class)
			.id(new ResourceLocation(Motherlode.MOD_ID, "sand_lizard"), ID++)
			.name("sand_lizard")
			.tracker(64, 1, true)
			.egg(0x202020, 0x808010)
			.spawn(EnumCreatureType.AMBIENT, 5, 5, 15, BiomeDictionary.getBiomes(Type.SANDY))
			.build();
		event.getRegistry().register(sandLizard);
	}
}
