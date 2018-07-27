package motherlode.gui;

import java.util.ArrayList;
import java.util.List;

import contrivitive.gui.IContrivitiveGui;
import contrivitive.gui.element.Element;
import contrivitive.gui.element.sprite.ItemStackSprite;
import contrivitive.util.RenderUtil;
import motherlode.network.MotherlodeNetwork;
import motherlode.network.packet.PacketTryCraft;
import motherlode.recipe.IMotherlodeRecipe;
import motherlode.recipe.MotherlodeRecipes;
import motherlode.recipe.ingredient.IIngredient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CraftingListElement extends Element {

	List<IMotherlodeRecipe> craftableRecipes = new ArrayList<>();
	int hoverIndex = -1;
	int counter = 0;
	float ticks = 0;

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
			craftableRecipes = MotherlodeRecipes.getAllPossibleRecipes(mc.player.inventory.mainInventory, mc.player, MotherlodeRecipes.getTables(mc.player));
			if (!isHovering) hoverIndex = -1;
		});
		pressActions.add((element, gui, coordinates, mouseX, mouseY) -> {
			if (hoverIndex >= 0 && hoverIndex < craftableRecipes.size()) {
				IMotherlodeRecipe recipe = craftableRecipes.get(hoverIndex);
				MotherlodeNetwork.NETWORK.sendToServer(new PacketTryCraft(recipe));
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
	public void draw(IContrivitiveGui<?, ?> gui, int x, int y, int mouseX, int mouseY, float elapsedTicks) {
		super.draw(gui, x, y, mouseX, mouseY, elapsedTicks);
		for (IMotherlodeRecipe recipe : craftableRecipes) {
			int i = 23 + 20 * craftableRecipes.indexOf(recipe);
			if ((ticks += elapsedTicks) > 50F) {
				ticks = 0;
				counter++;
			}
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
			for (IIngredient ingredient : recipe.getInputs()) {
				GlStateManager.pushMatrix();
				float scale = 0.5F;
				GlStateManager.scale(scale, scale, 1);
				ItemStack stack = ingredient.getDisplayStacks().get(counter % ingredient.getDisplayStacks().size());
				new ItemStackSprite(stack, scale).draw(gui, x + drawX, y + i + 9, elapsedTicks);
				GlStateManager.popMatrix();
				float ingCtX = drawX + 5.5F;
				if (ingredient.getCount() > 10) {
					ingCtX -= 3F;
				}
				if (ingredient.getCount() > 1) {
					RenderUtil.drawString(gui, String.valueOf(ingredient.getCount()), x + ingCtX, y + i + 13.5F, 0.5F, 0xFFFFFF, true);
				}

				drawX += 10;
			}
		}
	}
}
