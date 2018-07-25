package motherlode.network.packet;

import io.netty.buffer.ByteBuf;
import motherlode.entity.passive.EntityFlyingInsect;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketEntityFlyingInsectUpdate implements IMessage {

	private boolean isSitting;
	private int facing;
	private int entityId;

	public PacketEntityFlyingInsectUpdate() {
	}

	public PacketEntityFlyingInsectUpdate(boolean isSitting, int facing, int entityId) {
		this.isSitting = isSitting;
		this.facing = facing;
		this.entityId = entityId;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.isSitting = buf.readBoolean();
		this.facing = buf.readInt();
		this.entityId = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(this.isSitting);
		buf.writeInt(this.facing);
		buf.writeInt(this.entityId);
	}

	public static class Handler implements IMessageHandler<PacketEntityFlyingInsectUpdate, IMessage> {
		@Override
		public IMessage onMessage(PacketEntityFlyingInsectUpdate message, MessageContext ctx) {
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
			return null;
		}

		private void handle(PacketEntityFlyingInsectUpdate message, MessageContext context) {

			boolean b = message.isSitting;
			int f = message.facing;
			int id = message.entityId;

			if (Minecraft.getMinecraft().world.getEntityByID(id) instanceof EntityFlyingInsect) {
				EntityFlyingInsect insect = (EntityFlyingInsect) Minecraft.getMinecraft().world.getEntityByID(id);
				insect.setIsSitting(b);
				insect.setFacing(f);
			}
		}
	}

}
