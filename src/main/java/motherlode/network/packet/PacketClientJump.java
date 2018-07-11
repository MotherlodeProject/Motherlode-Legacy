package motherlode.network.packet;

import io.netty.buffer.ByteBuf;
import motherlode.util.MotherlodeCache;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketClientJump implements IMessage {

	boolean isJumping;

	public PacketClientJump(boolean isJumping) {
		this.isJumping = isJumping;
	}

	public PacketClientJump() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(isJumping);
	}

	public static class Handler implements IMessageHandler<PacketClientJump, IMessage> {
		@Override
		public IMessage onMessage(PacketClientJump message, MessageContext ctx) {
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
			return null;
		}

		private void handle(PacketClientJump message, MessageContext context) {
			EntityPlayerMP player = context.getServerHandler().player;
			MotherlodeCache.playerJumpMap.put(player.getUniqueID(), message.isJumping);
		}
	}
}
