package motherlode.client;

import motherlode.Motherlode;
import motherlode.network.MotherlodeNetwork;
import motherlode.network.packet.PacketClientJump;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = Motherlode.MOD_ID)
public class ClientEventHandler {

	public static final String[] SPLASH_TEXT = new String[] { "i", "field_73975_c", "splashText" };
	public static final ResourceLocation MOTHERLODE_LOGO = new ResourceLocation("motherlode:textures/gui/logo.png");
	public static boolean lastJumping = false;

	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public static void onEvent(InputEvent.KeyInputEvent event) {
		if (Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown()) {
			if (!lastJumping) {
				MotherlodeNetwork.networkWrapper.sendToServer(new PacketClientJump(true));
			}
			lastJumping = true;
		} else {
			if (lastJumping) {
				MotherlodeNetwork.networkWrapper.sendToServer(new PacketClientJump(false));
			}
			lastJumping = false;
		}
	}

	@SubscribeEvent
	public static void onGuiDraw(GuiScreenEvent.DrawScreenEvent event) {
		if (event.getGui() instanceof GuiMainMenu) {
			GuiMainMenu gui = (GuiMainMenu) event.getGui();
			ReflectionHelper.setPrivateValue(GuiMainMenu.class, gui, "", SPLASH_TEXT);
			gui.mc.getTextureManager().bindTexture(MOTHERLODE_LOGO);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			int j = gui.width / 2 - 256 / 2;
			gui.drawTexturedModalRect(j, 17, 0, 0, 256, 100);
		}
	}
}
