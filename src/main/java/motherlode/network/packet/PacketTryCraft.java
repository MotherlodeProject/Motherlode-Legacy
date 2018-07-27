package motherlode.network.packet;

import java.util.List;
import java.util.Map;

import io.netty.buffer.ByteBuf;
import motherlode.recipe.IMotherlodeRecipe;
import motherlode.recipe.MotherlodeRecipes;
import motherlode.recipe.ingredient.IIngredient;
import motherlode.recipe.table.IRecipeTable;
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
		buf.writeInt(MotherlodeRecipes.getRegistry().getID(recipe));
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		int i = buf.readInt();
		recipe = MotherlodeRecipes.getRegistry().getValue(i);
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
