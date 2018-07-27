package motherlode.network;

import motherlode.Motherlode;
import motherlode.network.packet.PacketClientJump;
import motherlode.network.packet.PacketEntityFlyingInsectUpdate;
import motherlode.network.packet.PacketInventoryOpen;
import motherlode.network.packet.PacketTryCraft;
import motherlode.network.packet.PacketUpdateHeld;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class MotherlodeNetwork {
	
	public static int id = 0;
	public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(Motherlode.MOD_ID);

	public MotherlodeNetwork() {
	}

	public static int nextId() {
		return id++;
	}

	/*
		Side refers to the side that the packet is being SENT to
	 */
	public static void registerMessages() {
		NETWORK.registerMessage(PacketClientJump.Handler.class, PacketClientJump.class, nextId(), Side.SERVER);
		NETWORK.registerMessage(PacketInventoryOpen.Handler.class, PacketInventoryOpen.class, nextId(), Side.SERVER);
		NETWORK.registerMessage(PacketTryCraft.Handler.class, PacketTryCraft.class, nextId(), Side.SERVER);
		NETWORK.registerMessage(PacketEntityFlyingInsectUpdate.Handler.class, PacketEntityFlyingInsectUpdate.class, nextId(), Side.CLIENT);
		NETWORK.registerMessage(PacketUpdateHeld.Handler.class, PacketUpdateHeld.class, nextId(), Side.CLIENT);
	}
}
