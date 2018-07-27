package motherlode.gui;

import contrivitive.gui.GuiContainerBlueprint;
import contrivitive.gui.element.Element;
import motherlode.recipe.IMotherlodeRecipe;
import motherlode.recipe.MotherlodeRecipes;
import motherlode.recipe.ingredient.IRecipeIngredient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static motherlode.recipe.MotherlodeRecipes.addCraftingRecipes;

public class MLPlayerInv {
	public static GuiContainerBlueprint getCTInventoryBlueprint() {
		addCraftingRecipes();
		int inventoryWidth = 176;
		int inventoryHeight = 187;
		int craftingWidth = MLSprites.CRAFTING_BACKGROUND.width + 2;
		GuiContainerBlueprint blueprint = new GuiContainerBlueprint(inventoryWidth + craftingWidth, inventoryHeight, false);
		int inventoryX = craftingWidth;
		int inventoryY = 0;
		blueprint.setGuiLeft((width, height, xSize, ySize, screenWidth, screenHeight) -> {
			int centeredX = (width - xSize - craftingWidth) / 2;
			if (centeredX >= 0) {
				return centeredX;
			} else {
				return (width - xSize) / 2;
			}
		});
		blueprint.at(inventoryX, inventoryY, new Element(inventoryWidth, inventoryHeight).sprite(MLSprites.INVENTORY_BACKGROUND));
		blueprint.addPlayerInventory(inventoryX + 7, inventoryY + 101, 5, false);
		blueprint.addPlayerSlot(inventoryX + 7, inventoryY + 7, 39, false); // Helmet slot
		blueprint.addPlayerSlot(inventoryX + 7, inventoryY + 25, 38, false); // Chestplate slot
		blueprint.addPlayerSlot(inventoryX + 7, inventoryY + 43, 37, false); // Leggings slot
		blueprint.addPlayerSlot(inventoryX + 7, inventoryY + 61, 36, false); // Boots slot
		blueprint.addPlayerSlot(inventoryX + 7, inventoryY + 79, 40, false); // Offhand slot
		blueprint.at(inventoryX + 50, inventoryY + 80, new PlayerEntityElement(28));
		int craftingX = 0;
		int craftingY = 8;
		blueprint.at(craftingX, craftingY, new CraftingListElement());
		return blueprint;
	}
}
