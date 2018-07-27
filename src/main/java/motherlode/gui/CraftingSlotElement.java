package motherlode.gui;

import contrivitive.gui.IContrivitiveGui;
import contrivitive.gui.element.Element;
import contrivitive.gui.element.sprite.ItemStackSprite;
import contrivitive.util.RenderUtil;
import motherlode.network.MotherlodeNetwork;
import motherlode.network.packet.PacketTryCraft;
import motherlode.recipe.IMotherlodeRecipe;
import motherlode.recipe.ingredient.IIngredient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CraftingSlotElement extends Element {

	IMotherlodeRecipe recipe;
	int counter = 0;
	float ticks = 0;

	public CraftingSlotElement(IMotherlodeRecipe recipe) {
		super(MLSprites.CRAFTING_SLOT.width, MLSprites.CRAFTING_SLOT.height);
		this.recipe = recipe;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void initClient() {
		super.initClient();
		pressActions.add((element, gui, coordinate, mouseX, mouseY) -> {
			MotherlodeNetwork.networkWrapper.sendToServer(new PacketTryCraft(recipe));
		});
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void draw(IContrivitiveGui<?, ?> gui, int x, int y, int mouseX, int mouseY, float elapsedTicks) {
		super.draw(gui, x, y, mouseX, mouseY, elapsedTicks);
		if ((ticks += elapsedTicks) > 50F) {
			ticks = 0;
			counter++;
		}
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
		for (IIngredient ingredient : recipe.getInputs()) {
			GlStateManager.pushMatrix();
			float scale = 0.5F;
			GlStateManager.scale(scale, scale, 1);
			ItemStack stack = ingredient.getDisplayStacks().get(counter % ingredient.getDisplayStacks().size());
			new ItemStackSprite(stack, scale).draw(gui, x + drawX, y + 9, elapsedTicks);
			GlStateManager.popMatrix();
			float ingCtX = drawX + 5.5F;
			if (ingredient.getCount() > 10) {
				ingCtX -= 3F;
			}
			if (ingredient.getCount() > 1) {
				RenderUtil.drawString(gui, String.valueOf(ingredient.getCount()), x + ingCtX, y + 13.5F, 0.5F, 0xFFFFFF, true);
			}

			drawX += 10;
		}
	}
}
