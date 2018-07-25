package motherlode.entity;

import motherlode.Motherlode;
import motherlode.entity.item.EntityBomb;
import motherlode.entity.item.EntityDynamite;
import motherlode.entity.passive.*;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.passive.EntityCow;
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

		// PASSIVE

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

		EntityEntry netherFirefly = EntityEntryBuilder.create()
			.entity(EntityFireflyNether.class)
			.id(new ResourceLocation(Motherlode.MOD_ID, "nether_firefly"), ID++)
			.name("nether_firefly")
			.tracker(64, 20, true)
			.egg(0x101050, 0xff0000)
			.spawn(EnumCreatureType.AMBIENT, 2, 10, 30, BiomeDictionary.getBiomes(Type.NETHER))
			.build();
		event.getRegistry().register(netherFirefly);

		EntityEntry butterfly = EntityEntryBuilder.create()
			.entity(EntityButterfly.class)
			.id(new ResourceLocation(Motherlode.MOD_ID, "butterfly"), ID++)
			.name("butterfly")
			.tracker(64, 20, true)
			.egg(0x202020, 0xf000f0)
			.spawn(EnumCreatureType.AMBIENT, 5, 2, 10, BiomeDictionary.getBiomes(Type.FOREST))
			.build();
		event.getRegistry().register(butterfly);

		EntityEntry sandLizard = EntityEntryBuilder.create()
			.entity(EntityLizard.class)
			.id(new ResourceLocation(Motherlode.MOD_ID, "sand_lizard"), ID++)
			.name("sand_lizard")
			.tracker(64, 1, true)
			.egg(0x202020, 0xf000f0)
			.spawn(EnumCreatureType.CREATURE, 10, 1, 2, BiomeDictionary.getBiomes(Type.SANDY))
			.build();
		event.getRegistry().register(sandLizard);
	}
}
