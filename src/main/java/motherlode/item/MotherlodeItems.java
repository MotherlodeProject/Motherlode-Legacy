package motherlode.item;

import motherlode.Motherlode;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Motherlode.MOD_ID)
public class MotherlodeItems {

	public static final Item BOMB;
	public static final Item DYNAMITE;
	public static final Item BANDAGE;

	static {
		BOMB = new ItemBomb();
		DYNAMITE = new ItemDynamite();
		BANDAGE = new ItemBandage();
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(BOMB);
		event.getRegistry().register(DYNAMITE);
		event.getRegistry().register(BANDAGE);
	}
}
