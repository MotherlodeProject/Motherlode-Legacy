package motherlode.network;

import motherlode.network.packet.PacketClientJump;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class MotherlodeNetwork {
	public static int id = 0;
	public static SimpleNetworkWrapper networkWrapper = null;

	public MotherlodeNetwork() {
	}

	public static int nextId() {
		return id++;
	}

	public static void registerMessages(String channelName) {
		networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);
		registerMessages();
	}

	/*
		Side refers to the side that the packet is being SENT to
	 */
	public static void registerMessages() {
		networkWrapper.registerMessage(PacketClientJump.Handler.class, PacketClientJump.class, nextId(), Side.SERVER);
	}
}
