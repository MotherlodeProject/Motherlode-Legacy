package motherlode.network.packet;

import io.netty.buffer.ByteBuf;
import motherlode.recipe.IMotherlodeRecipe;
import motherlode.recipe.MotherlodeRecipes;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketTryCraft implements IMessage {

	IMotherlodeRecipe recipe;

	public PacketTryCraft(IMotherlodeRecipe recipe) {
		this.recipe = recipe;
	}

	public PacketTryCraft() {
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(MotherlodeRecipes.getRecipeRegistry().getID(recipe));
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		int i = buf.readInt();
		recipe = MotherlodeRecipes.getRecipeRegistry().getValue(i);
		if (recipe == null) throw new NullPointerException("Recived a packet with invalid recipe ID " + i);
	}

	public static class Handler implements IMessageHandler<PacketTryCraft, IMessage> {

		@Override
		public IMessage onMessage(PacketTryCraft msg, MessageContext ctx) {
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> {
			});
			return null;
		}
	}
}
