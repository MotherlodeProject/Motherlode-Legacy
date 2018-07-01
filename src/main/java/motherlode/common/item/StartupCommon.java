package motherlode.common.item;

import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class StartupCommon {

	public static ItemBomb itemBomb;
	
	public static void preInitCommon() {
		itemBomb = (ItemBomb)(new ItemBomb().setUnlocalizedName("item_bomb_unlocalised"));
		itemBomb.setRegistryName("item_bomb_registry");
		ForgeRegistries.ITEMS.register(itemBomb);
	}
	
	public static void initCommon() {
	}
	
	public static void postInitCommon() {
	}
	
}
