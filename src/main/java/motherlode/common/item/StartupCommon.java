package motherlode.common.item;

import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class StartupCommon {

	public static ItemBomb itemBomb;
	public static ItemDynamite itemDynamite;
	
	public static void preInitCommon() {
		itemBomb = (ItemBomb)(new ItemBomb().setUnlocalizedName("item_bomb_unlocalized"));
		itemBomb.setRegistryName("item_bomb_registry");
		ForgeRegistries.ITEMS.register(itemBomb);
		
		itemDynamite = (ItemDynamite)(new ItemDynamite().setUnlocalizedName("item_dynamite_unlocalized"));
		itemDynamite.setRegistryName("item_dynamite_registry");
		ForgeRegistries.ITEMS.register(itemDynamite);
	}
	
	public static void initCommon() {
	}
	
	public static void postInitCommon() {
	}
	
}
