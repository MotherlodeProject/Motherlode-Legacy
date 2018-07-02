package motherlode.client;

import motherlode.Motherlode;
import motherlode.item.MotherlodeItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Motherlode.MOD_ID)
public class MotherlodeModels {

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(MotherlodeItems.BOMB, 0, new ModelResourceLocation("motherlode:BOMB", "inventory"));
		ModelLoader.setCustomModelResourceLocation(MotherlodeItems.DYNAMITE, 0, new ModelResourceLocation("motherlode:DYNAMITE", "inventory"));
	}
}
