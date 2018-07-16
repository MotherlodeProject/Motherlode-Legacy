package contrivitive.gui.element.slot;

import contrivitive.gui.element.Element;
import contrivitive.gui.element.sprite.Sprites;

public class PlayerSlotElement extends Element {
	int x, y;

	public PlayerSlotElement(int x, int y) {
		this.x = x;
		this.y = y;
		sprite(Sprites.SLOT_NORMAL);
	}

	public int getX() {
		return x + 1;
	}

	public int getY() {
		return y + 1;
	}
}
