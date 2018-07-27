package motherlode.network.packet;

import io.netty.buffer.ByteBuf;
import motherlode.recipe.IMotherlodeRecipe;
import motherlode.recipe.MotherlodeRecipes;
import motherlode.recipe.ingredient.IIngredient;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.HashMap;

public class PacketTryCraft implements IMessage {

	String registryName;

	public PacketTryCraft(String registryName) {
		this.registryName = registryName;
	}

	public PacketTryCraft() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		registryName = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, registryName);
	}

	public static class Handler implements IMessageHandler<PacketTryCraft, IMessage> {
		@Override
		public IMessage onMessage(PacketTryCraft message, MessageContext ctx) {
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
			return null;
		}

		private void handle(PacketTryCraft message, MessageContext context) {
			EntityPlayerMP player = context.getServerHandler().player;
			IMotherlodeRecipe recipe = MotherlodeRecipes.CRAFTING_RECIPES.get(new ResourceLocation(message.registryName));
			if (player.inventory.getItemStack().isEmpty() || (player.inventory.getItemStack().isItemEqual(recipe.getOutput()) && player.inventory.getItemStack().getCount() + recipe.getOutput().getCount() <= recipe.getOutput().getMaxStackSize())) {
				boolean hasAllIngredients = true;
				HashMap<IIngredient, ItemStack> playerIngredients = new HashMap<>();
				for (IIngredient ingredient : recipe.getInputs()) {
					boolean hasIngredient = false;
					for (ItemStack stack : player.inventory.mainInventory) {
						if (ingredient.isStackGreaterOrEqual(stack)) {
							hasIngredient = true;
							playerIngredients.put(ingredient, stack);
						}
					}
					if (!hasIngredient) {
						hasAllIngredients = false;
					}
				}
				if (hasAllIngredients) {
					for (IIngredient ingredient : playerIngredients.keySet()) {
						player.inventory.mainInventory.get(player.inventory.mainInventory.indexOf(playerIngredients.get(ingredient))).shrink(ingredient.getAmount());
					}
					if (player.inventory.getItemStack().isItemEqual(recipe.getOutput())) {
						ItemStack stack = player.inventory.getItemStack();
						stack.grow(recipe.getOutput().getCount());
						player.inventory.setItemStack(stack);
					} else {
						player.inventory.setItemStack(recipe.getOutput().copy());
					}
				}
			}
		}
	}
}
