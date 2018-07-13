package motherlode.client;

import motherlode.Motherlode;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = Motherlode.MOD_ID)
public class MainMenuEventHandler {
	public static final String[] TITLE_TEXTURE_MAPPING = new String[] { "G", "field_110352_y", "MINECRAFT_TITLE_TEXTURES" };
	public static final ResourceLocation MOTHERLODE_LOGO = new ResourceLocation("motherlode:textures/gui/logo.png");

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void onGuiInit(GuiScreenEvent.DrawScreenEvent event) {
		if (event.getGui() instanceof GuiMainMenu) {
			GuiMainMenu gui = (GuiMainMenu) event.getGui();
			ReflectionHelper.setPrivateValue(GuiMainMenu.class, gui, "", "i", "field_73975_c", "splashText");
			try {

				//				EnumHelper.setFailsafeFieldValue(ReflectionHelper.findField(GuiMainMenu.class, TITLE_TEXTURE_MAPPING), null, new ResourceLocation("textures/gui/title/minecraft.png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			gui.mc.getTextureManager().bindTexture(MOTHERLODE_LOGO);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

			int j = gui.width / 2 - 256 / 2;
			gui.drawTexturedModalRect(j, 17, 0, 0, 256, 100);
		}
	}
}
