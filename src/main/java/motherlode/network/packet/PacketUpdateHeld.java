package motherlode.network.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketUpdateHeld implements IMessage {

	ItemStack stack;

	public PacketUpdateHeld(ItemStack stack) {
		this.stack = stack;
	}

	public PacketUpdateHeld() {
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeItemStack(buf, stack);
		ByteBufUtils.writeTag(buf, stack.getTagCompound());
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		stack = ByteBufUtils.readItemStack(buf);
		stack.setTagCompound(ByteBufUtils.readTag(buf));
	}

	public static class Handler implements IMessageHandler<PacketUpdateHeld, IMessage> {

		@Override
		public IMessage onMessage(PacketUpdateHeld msg, MessageContext ctx) {
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> {
				Minecraft.getMinecraft().player.inventory.setItemStack(msg.stack);
			});
			return null;
		}
	}
}
