package contrivitive.registry;

import contrivitive.lib.ContrivitiveConstants;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = ContrivitiveConstants.MOD_ID)
public class ContrivitiveRegistry {
	protected static final Map<String, Class<? extends TileEntity>> BLOCK_ENTITIES = new HashMap<>();

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		for (Item item : ContrivitiveItems.getItems()) {
			event.getRegistry().register(item);
		}
	}

	public static void registerRecipes() {
	}
}
