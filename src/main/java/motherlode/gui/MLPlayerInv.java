package motherlode.gui;

import contrivitive.gui.GuiContainerBlueprint;
import contrivitive.gui.element.Element;

public class MLPlayerInv {
	public static GuiContainerBlueprint getCTInventoryBlueprint() {
		GuiContainerBlueprint blueprint = new GuiContainerBlueprint(176, 187, false);
		blueprint.at(0, 0, new Element(176, 187).sprite(MLSprites.INVENTORY_BACKGROUND));
		blueprint.addPlayerInventory(7, 101, 5, false);
		blueprint.addPlayerSlot(7, 7, 39, false); // Offhand slot
		blueprint.addPlayerSlot(7, 25, 38, false); // Offhand slot
		blueprint.addPlayerSlot(7, 43, 37, false); // Offhand slot
		blueprint.addPlayerSlot(7, 61, 36, false); // Offhand slot
		blueprint.addPlayerSlot(7, 79, 40, false); // Offhand slot
		blueprint.at(50, 80, new PlayerEntityElement(28));
		int craftingX = -72;
		int craftingY = 8;
		blueprint.at(craftingX, craftingY, new Element(70, 170).sprite(MLSprites.CRAFTING_BACKGROUND));
		blueprint.at(craftingX + 60, craftingY + 4, new Element().sprite(MLSprites.SCROLL_BAR));
		blueprint.at(craftingX + 6, craftingY + 23, new CraftingSlotElement());
		return blueprint;
	}
}
