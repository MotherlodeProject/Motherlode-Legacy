package contrivitive.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class ContrivitivePacketManager {
	public static int id = 0;
	public static SimpleNetworkWrapper networkWrapper = null;

	public ContrivitivePacketManager() {
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
		
	}
}
