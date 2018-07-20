package motherlode.gui;

import contrivitive.gui.IContrivitiveGui;
import contrivitive.gui.element.Element;
import contrivitive.gui.element.sprite.ItemStackSprite;
import contrivitive.util.RenderUtil;
import motherlode.network.MotherlodeNetwork;
import motherlode.network.packet.PacketInventoryOpen;
import motherlode.network.packet.PacketTryCraft;
import motherlode.recipe.IMotherlodeRecipe;
import motherlode.recipe.ingredient.IRecipeIngredient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;

public class CraftingSlotElement extends Element {
	IMotherlodeRecipe recipe;
	float counter = 0;

	public CraftingSlotElement(IMotherlodeRecipe recipe) {
		super(MLSprites.CRAFTING_SLOT.width, MLSprites.CRAFTING_SLOT.height);
		this.recipe = recipe;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void initClient() {
		super.initClient();
		pressActions.add((element, gui, mouseX, mouseY) -> {
				MotherlodeNetwork.networkWrapper.sendToServer(new PacketTryCraft(recipe.getRegistryName().toString()));
				EntityPlayer player = Minecraft.getMinecraft().player;
				if (player.inventory.getItemStack().isEmpty() || (player.inventory.getItemStack().isItemEqual(recipe.getOutput()) && player.inventory.getItemStack().getCount() + recipe.getOutput().getCount() <= recipe.getOutput().getMaxStackSize())) {
					boolean hasAllIngredients = true;
					HashMap<IRecipeIngredient, ItemStack> playerIngredients = new HashMap<>();
					for (IRecipeIngredient ingredient : recipe.getInputs()) {
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
						for (IRecipeIngredient ingredient : playerIngredients.keySet()) {
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
				MotherlodeNetwork.networkWrapper.sendToServer(new PacketInventoryOpen());
			}
		);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void draw(IContrivitiveGui gui, int x, int y, int mouseX, int mouseY, float elapsedTicks) {
		super.draw(gui, x, y, mouseX, mouseY, elapsedTicks);
		counter += elapsedTicks;
		GlStateManager.color(1, 1, 1, 1);
		if (isHovering) {
			MLSprites.HOVERED_CRAFTING_SLOT.draw(gui, x, y, elapsedTicks);
			RenderUtil.drawString(gui, recipe.getRecipeTooltip() != null && !recipe.getRecipeTooltip().isEmpty() ? recipe.getRecipeTooltip().get(0) : recipe.getOutput().getDisplayName(), x + 25, y + 4, 0.5F, 0xFFFFA0);
		} else {
			MLSprites.CRAFTING_SLOT.draw(gui, x, y, elapsedTicks);
			RenderUtil.drawString(gui, recipe.getRecipeTooltip() != null && !recipe.getRecipeTooltip().isEmpty() ? recipe.getRecipeTooltip().get(0) : recipe.getOutput().getDisplayName(), x + 25, y + 4, 0.5F, 0xFFFFFF);
		}
		new ItemStackSprite(recipe.getOutput()).draw(gui, x + 6, y + 2, elapsedTicks);
		float countX = 17;
		if (recipe.getOutput().getCount() > 10) {
			countX -= 6;
		}
		if (recipe.getOutput().getCount() > 1) {
			RenderUtil.drawString(gui, String.valueOf(recipe.getOutput().getCount()), x + countX, y + 11F, 1, 0xFFFFFF, true);
		}
		int drawX = 25;
		for (IRecipeIngredient ingredient : recipe.getInputs()) {
			GlStateManager.pushMatrix();
			float scale = 0.5F;
			GlStateManager.scale(scale, scale, 1);
			int cycle = (int) Math.floor(counter) % ingredient.getItemsForDisplay().size() * 20;
			//			System.out.println(cycle);
			ItemStack stack = ingredient.getItemsForDisplay().get(0);
			new ItemStackSprite(stack, scale).draw(gui, x + drawX, y + 9, elapsedTicks);
			GlStateManager.popMatrix();
			float ingCtX = drawX + 5.5F;
			if (ingredient.getAmount() > 10) {
				ingCtX -= 3F;
			}
			if (ingredient.getAmount() > 1) {
				RenderUtil.drawString(gui, String.valueOf(ingredient.getAmount()), x + ingCtX, y + 13.5F, 0.5F, 0xFFFFFF, true);
			}

			drawX += 10;
		}
	}
}
