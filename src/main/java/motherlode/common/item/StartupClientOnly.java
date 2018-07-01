package motherlode.common.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class StartupClientOnly {

	public static void preInitClientOnly() {
		ModelLoader.setCustomModelResourceLocation(StartupCommon.itemBomb, 0,
				new ModelResourceLocation("motherlode:item_bomb", "inventory"));
		
		ModelLoader.setCustomModelResourceLocation(StartupCommon.itemDynamite, 0,
				new ModelResourceLocation("motherlode:item_dynamite", "inventory"));
	}
	
	public static void initClientOnly() {
	}
	
	public static void postInitClientOnly() {
	}
}
