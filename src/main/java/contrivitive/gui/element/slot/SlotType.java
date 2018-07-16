package contrivitive.gui.element.slot;

import contrivitive.gui.element.sprite.Sprite;
import contrivitive.gui.element.sprite.Sprites;

public enum SlotType {
	NORMAL(1, 1, Sprites.SLOT_NORMAL, Sprites.SLOT_NORMAL_BUTTON, null);

	int slotOffsetX;
	int slotOffsetY;
	Sprite sprite;
	Sprite buttonSprite;
	Sprite buttonHoverOverlay;

	SlotType(int slotOffsetX, int slotOffsetY, Sprite sprite, Sprite buttonSprite, Sprite buttonHoverOverlay) {
		this.slotOffsetX = slotOffsetX;
		this.slotOffsetY = slotOffsetY;
		this.sprite = sprite;
		this.buttonSprite = buttonSprite;
		this.buttonHoverOverlay = buttonHoverOverlay;
	}

	SlotType(int slotOffset, Sprite sprite) {
		this.slotOffsetX = slotOffset;
		this.slotOffsetY = slotOffset;
		this.sprite = sprite;
	}

	public int getSlotOffsetX() {
		return slotOffsetX;
	}

	public int getSlotOffsetY() {
		return slotOffsetY;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public Sprite getButtonSprite() {
		return buttonSprite;
	}

	public Sprite getButtonHoverOverlay() {
		return buttonHoverOverlay;
	}
}