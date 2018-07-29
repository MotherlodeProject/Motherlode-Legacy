package motherlode.network.packet;

import java.util.List;
import java.util.Map;

import io.netty.buffer.ByteBuf;
import motherlode.api.MotherlodeAPI;
import motherlode.api.recipe.IIngredient;
import motherlode.api.recipe.IMotherlodeRecipe;
import motherlode.api.recipe.IRecipeTable;
import motherlode.recipe.MotherlodeRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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
		buf.writeInt(MotherlodeAPI.getRecipeRegistry().getID(recipe));
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		int i = buf.readInt();
		recipe = MotherlodeAPI.getRecipeRegistry().getValue(i);
		if (recipe == null) throw new NullPointerException("Recived a packet with invalid recipe ID " + i);
	}

	public static class Handler implements IMessageHandler<PacketTryCraft, IMessage> {

		@Override
		public IMessage onMessage(PacketTryCraft msg, MessageContext ctx) {
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> {
				EntityPlayer player = ctx.getServerHandler().player;
				List<IRecipeTable> tables = MotherlodeRecipes.getTables(ctx.getServerHandler().player);
				Map<IIngredient, ItemStack[]> map = msg.recipe.matches(player.inventory.mainInventory, player, tables);
				if (map != null) msg.recipe.onCraft(player.inventory.mainInventory, map, player, tables);
			});
			return null;
		}
	}
}
