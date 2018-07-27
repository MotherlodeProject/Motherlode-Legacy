package motherlode.gui;

import contrivitive.gui.IContrivitiveGui;
import contrivitive.gui.element.Element;
import contrivitive.gui.element.sprite.ItemStackSprite;
import contrivitive.util.RenderUtil;
import motherlode.network.MotherlodeNetwork;
import motherlode.network.packet.PacketTryCraft;
import motherlode.recipe.IMotherlodeRecipe;
import motherlode.recipe.MotherlodeRecipes;
import motherlode.recipe.ingredient.IRecipeIngredient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CraftingListElement extends Element {
	List<IMotherlodeRecipe> craftableRecipes = new ArrayList<>();
	int hoverIndex = -1;
	float counter = 0;

	public CraftingListElement() {
		super(MLSprites.CRAFTING_BACKGROUND.width, MLSprites.CRAFTING_BACKGROUND.height);
		sprite(MLSprites.CRAFTING_BACKGROUND);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void initClient() {
		super.initClient();
		Minecraft mc = Minecraft.getMinecraft();
		updateActions.add((element, gui) -> {
			craftableRecipes.clear();
			for (IMotherlodeRecipe recipe : MotherlodeRecipes.CRAFTING_RECIPES.values()) {
				boolean hasAllIngredients = true;
				for (IRecipeIngredient ingredient : recipe.getInputs()) {
					boolean hasIngredient = false;
					for (ItemStack stack : mc.player.inventory.mainInventory) {
						if (ingredient.isStackGreaterOrEqual(stack)) {
							hasIngredient = true;
						}
					}
					if (!hasIngredient) {
						hasAllIngredients = false;
					}
				}
				if (hasAllIngredients) {
					craftableRecipes.add(recipe);
				}
			}
			if (!isHovering) {
				hoverIndex = -1;
			}
		});
		pressActions.add((element, gui, coordinates, mouseX, mouseY) -> {
			if (hoverIndex >= 0 && hoverIndex < craftableRecipes.size()) {
				IMotherlodeRecipe recipe = craftableRecipes.get(hoverIndex);
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
			}
		});
		hoverActions.add((element, gui, coordinate, mouseX, mouseY) -> {
			boolean hoverSomething = false;
			for (IMotherlodeRecipe recipe : craftableRecipes) {
				int index = craftableRecipes.indexOf(recipe);
				if (RenderUtil.isInRect(gui, coordinate.x, coordinate.y + 23 + 20 * index, MLSprites.CRAFTING_SLOT.width, MLSprites.CRAFTING_SLOT.height, mouseX + coordinate.x - gui.getStartingX(), mouseY + coordinate.y - gui.getStartingY() - 16, false)) {
					hoverIndex = index;
					hoverSomething = true;
				}
			}
			if (!hoverSomething) {
				hoverIndex = -1;
			}
		});
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void draw(IContrivitiveGui gui, int x, int y, int mouseX, int mouseY, float elapsedTicks) {
		super.draw(gui, x, y, mouseX, mouseY, elapsedTicks);
		for (IMotherlodeRecipe recipe : craftableRecipes) {
			int i = 23 + 20 * craftableRecipes.indexOf(recipe);
			counter += elapsedTicks;
			GlStateManager.color(1, 1, 1, 1);
			if (hoverIndex == craftableRecipes.indexOf(recipe)) {
				MLSprites.HOVERED_CRAFTING_SLOT.draw(gui, x, y + i, elapsedTicks);
				RenderUtil.drawString(gui, recipe.getRecipeTooltip() != null && !recipe.getRecipeTooltip().isEmpty() ? recipe.getRecipeTooltip().get(0) : recipe.getOutput().getDisplayName(), x + 25, y + i + 4, 0.5F, 0xFFFFA0);
			} else {
				MLSprites.CRAFTING_SLOT.draw(gui, x, y + i, elapsedTicks);
				RenderUtil.drawString(gui, recipe.getRecipeTooltip() != null && !recipe.getRecipeTooltip().isEmpty() ? recipe.getRecipeTooltip().get(0) : recipe.getOutput().getDisplayName(), x + 25, y + i + 4, 0.5F, 0xFFFFFF);
			}
			new ItemStackSprite(recipe.getOutput()).draw(gui, x + 6, y + i + 2, elapsedTicks);
			float countX = 17;
			if (recipe.getOutput().getCount() > 10) {
				countX -= 6;
			}
			if (recipe.getOutput().getCount() > 1) {
				RenderUtil.drawString(gui, String.valueOf(recipe.getOutput().getCount()), x + countX, y + i + 11F, 1, 0xFFFFFF, true);
			}
			int drawX = 25;
			for (IRecipeIngredient ingredient : recipe.getInputs()) {
				GlStateManager.pushMatrix();
				float scale = 0.5F;
				GlStateManager.scale(scale, scale, 1);
				int cycle = (int) Math.floor(counter) % ingredient.getItemsForDisplay().size() * 20;
				//			System.out.println(cycle);
				ItemStack stack = ingredient.getItemsForDisplay().get(0);
				new ItemStackSprite(stack, scale).draw(gui, x + drawX, y + i + 9, elapsedTicks);
				GlStateManager.popMatrix();
				float ingCtX = drawX + 5.5F;
				if (ingredient.getAmount() > 10) {
					ingCtX -= 3F;
				}
				if (ingredient.getAmount() > 1) {
					RenderUtil.drawString(gui, String.valueOf(ingredient.getAmount()), x + ingCtX, y + i + 13.5F, 0.5F, 0xFFFFFF, true);
				}

				drawX += 10;
			}
		}
	}
}
