package contrivitive.gui.element.slot;

import contrivitive.gui.GuiBlueprint;
import contrivitive.gui.element.Element;
import contrivitive.util.ContrivitiveSlot;

public class SlotElement<B extends GuiBlueprint> extends Element {
	public ContrivitiveSlot.SlotFilter filter = (slot, stack) -> true;
	protected String slotTexture = null;
	protected int stackSize = 64;
	protected SlotType type;
	protected int slotX, slotY;

	public SlotElement(int slotX, int slotY, SlotType type) {
		super(type.getSprite().width, type.getSprite().height);
		sprite(type.getSprite());
		this.type = type;
		this.slotX = slotX;
		this.slotY = slotY;
	}

	public SlotType getType() {
		return type;
	}

	public int getSlotX() {
		return slotX;
	}

	public int getSlotY() {
		return slotY;
	}

	public ContrivitiveSlot.SlotFilter getFilter() {
		return filter;
	}

	public SlotElement<B> setFilter(ContrivitiveSlot.SlotFilter filter) {
		this.filter = filter;
		return this;
	}

	public String getSlotTexture() {
		return slotTexture;
	}

	public SlotElement<B> setSlotTexture(String slotTexture) {
		this.slotTexture = slotTexture;
		return this;
	}

	public int getStackSize() {
		return stackSize;
	}

	public SlotElement<B> setStackSize(int stackSize) {
		this.stackSize = stackSize;
		return this;
	}
}
