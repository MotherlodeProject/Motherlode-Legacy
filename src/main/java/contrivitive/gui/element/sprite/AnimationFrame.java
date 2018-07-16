package contrivitive.gui.element.sprite;

import contrivitive.gui.element.Coordinate;

public class AnimationFrame {
	public Sprite sprite;
	public Coordinate relativePos;
	public int lifetime;

	public AnimationFrame(Sprite sprite, Coordinate relativePos, int lifetime) {
		this.sprite = sprite;
		this.relativePos = relativePos;
		this.lifetime = lifetime;
	}

	public AnimationFrame(Sprite sprite) {
		this(sprite, new Coordinate(), 1);
	}

	public AnimationFrame(Sprite sprite, int lifetime) {
		this(sprite, new Coordinate(), lifetime);
	}

	public AnimationFrame(Sprite sprite, Coordinate relativePos) {
		this(sprite, 1);
	}
}
