package contrivitive.gui;

import contrivitive.gui.element.slot.PlayerSlotElement;
import contrivitive.gui.element.slot.SlotElement;
import contrivitive.gui.element.slot.SlotType;
import contrivitive.util.ContrivitiveSlot;
import net.minecraftforge.items.IItemHandler;

public class GuiContainerBlueprint extends GuiBlueprint {

	public GuiContainerBlueprint(int width, int height) {
		this(width, height, true);
	}

	public GuiContainerBlueprint(int width, int height, boolean defaultBackground) {
		super(width, height, defaultBackground);
	}

	public GuiContainerBlueprint addPlayerSlot(String page, int x, int y, int index) {
		return addPlayerSlot(page, x, y, index, true);
	}

	public GuiContainerBlueprint addPlayerSlot(String page, int x, int y, int index, boolean draw) {
		PlayerSlotElement element = new PlayerSlotElement(x, y, draw);
		at(x, y, element);
		getPage(page).playerSlots.put(index, element);
		return this;
	}

	public GuiContainerBlueprint addPlayerSlot(int x, int y, int index) {
		return addPlayerSlot(x, y, index, true);
	}

	public GuiContainerBlueprint addPlayerSlot(int x, int y, int index, boolean draw) {
		return addPlayerSlot("main", x, y, index, draw);
	}

	public GuiContainerBlueprint addSlot(String page, SlotElement<?> slot, IItemHandler inventory) {
		getPage(page).slots.put(getPage(page).slots.size(), new SlotEntry<>(slot, inventory));
		getPage(page).justPlayerInv = false;
		at(page, slot.getSlotX(), slot.getSlotY(), slot);
		return this;
	}

	public GuiContainerBlueprint addSlot(String page, IItemHandler inventory, SlotType type, int x, int y) {
		addSlot(page, new SlotElement<>(x + type.getSlotOffsetX(), y + type.getSlotOffsetY(), type), inventory);
		return this;
	}

	public GuiContainerBlueprint addSlot(String page, IItemHandler inventory, int x, int y) {
		return addSlot(page, inventory, SlotType.NORMAL, x, y);
	}

	public GuiContainerBlueprint addSlot(SlotElement<?> slot, IItemHandler inventory) {
		return addSlot("main", slot, inventory);
	}

	public GuiContainerBlueprint addSlot(IItemHandler inventory, SlotType type, int x, int y) {
		addSlot(inventory, type, x, y, (slot, stack) -> true);
		return this;
	}

	public GuiContainerBlueprint addSlot(IItemHandler inventory, int x, int y) {
		return addSlot(inventory, SlotType.NORMAL, x, y);
	}

	public GuiContainerBlueprint addSlot(IItemHandler inventory, SlotType type, int x, int y, ContrivitiveSlot.SlotFilter filter) {
		addSlot(new SlotElement<>(x + type.getSlotOffsetX(), y + type.getSlotOffsetY(), type).setFilter(filter), inventory);
		return this;
	}

	public GuiContainerBlueprint addSlot(IItemHandler inventory, int x, int y, ContrivitiveSlot.SlotFilter filter) {
		return addSlot(inventory, SlotType.NORMAL, x, y, filter);
	}

	public GuiContainerBlueprint addPlayerInventory(String page, int x, int y) {
		return addPlayerInventory(page, x, y, 0, true);
	}

	public GuiContainerBlueprint addPlayerInventory(String page, int x, int y, int hotbarOffset, boolean draw) {
		this.getPage(page).playerInvX = x;
		this.getPage(page).playerInvY = y;
		if (x > -1 && y > -1) {
			for (int row = 0; row < 3; ++row) {
				for (int column = 0; column < 9; ++column) {
					int index = column + row * 9 + 9;
					int xpos = x + (column * 18);
					int ypos = y + (row * 18);
					addPlayerSlot(page, xpos, ypos, index, draw);
				}
			}
			for (int column = 0; column < 9; ++column) {
				int xpos = x + (column * 18);
				int ypos = y + 58 + hotbarOffset;
				addPlayerSlot(page, xpos, ypos, column, draw);
			}
		}
		return this;
	}

	public GuiContainerBlueprint addPlayerInventory(int x, int y) {
		return addPlayerInventory(x, y, 0, true);
	}

	public GuiContainerBlueprint addPlayerInventory(int x, int y, int hotbarOffset, boolean draw) {
		return addPlayerInventory("main", x, y, hotbarOffset, draw);
	}

	public GuiContainerBlueprint setGuiLeft(GetGuiInt guiLeft) {
		return setGuiLeft("main", guiLeft);
	}

	public GuiContainerBlueprint setGuiLeft(String page, GetGuiInt guiLeft) {
		this.getPage(page).guiLeft = guiLeft;
		return this;
	}

	public GuiContainerBlueprint setGuiTop(GetGuiInt guiTop) {
		return setGuiLeft("main", guiTop);
	}

	public GuiContainerBlueprint setGuiTop(String page, GetGuiInt guiTop) {
		this.getPage(page).guiTop = guiTop;
		return this;
	}

	public class SlotEntry<B extends GuiBlueprint> {
		private SlotElement<B> element;
		private IItemHandler inventory;

		public SlotEntry(SlotElement<B> element, IItemHandler inventory) {
			this.element = element;
			this.inventory = inventory;
		}

		public SlotElement<B> getElement() {
			return element;
		}

		public IItemHandler getInventory() {
			return inventory;
		}
	}
}
