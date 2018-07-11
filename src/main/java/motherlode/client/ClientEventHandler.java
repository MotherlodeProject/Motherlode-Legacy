package motherlode.client;

import motherlode.Motherlode;
import motherlode.network.MotherlodeNetwork;
import motherlode.network.packet.PacketClientJump;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = Motherlode.MOD_ID)
public class ClientEventHandler {

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
}
