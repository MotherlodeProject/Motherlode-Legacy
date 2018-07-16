package contrivitive.gui.element;

import contrivitive.Contrivitive;
import contrivitive.gui.GuiBlueprint;
import contrivitive.gui.IContrivitiveGui;
import contrivitive.gui.element.sprite.Sprite;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class Element<B extends GuiBlueprint> {
	public B blueprint;
	public int width = 0;
	public int height = 0;

	public List<RelativeSprite> sprites = new ArrayList<>();

	public boolean isHovering = false;
	public boolean isDragging = false;
	public boolean isPressing = false;
	public boolean isReleasing = false;
	public boolean startPressLast = false;
	public boolean isHoveringLast = false;
	public boolean isDraggingLast = false;
	public boolean isPressingLast = false;
	public boolean isReleasingLast = false;
	public List<InteractionAction<B>> hoverActions = new ArrayList<>();
	public List<InteractionAction<B>> dragActions = new ArrayList<>();
	public List<InteractionAction<B>> startPressActions = new ArrayList<>();
	public List<InteractionAction<B>> pressActions = new ArrayList<>();
	public List<InteractionAction<B>> releaseActions = new ArrayList<>();

	public List<UpdateAction<B>> updateActions = new ArrayList<>();

	public Element() {
	}

	public Element(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public Element sprite(Sprite sprite, Coordinate relativePos) {
		sprites.add(new RelativeSprite(sprite, relativePos));
		return this;
	}

	public Element sprite(Sprite sprite) {
		sprites.add(new RelativeSprite(sprite, new Coordinate()));
		return this;
	}

	public void sprite(RelativeSprite sprite) {
		sprites.add(sprite);
	}

	public void draw(IContrivitiveGui gui, int x, int y, int mouseX, int mouseY, float elapsedTicks) {
		for (RelativeSprite sprite : sprites) {
			sprite.sprite.draw(gui, x + sprite.relativePos.x, y + sprite.relativePos.y, elapsedTicks);
		}
	}

	@SideOnly(Side.CLIENT)
	public interface InteractionAction<B extends GuiBlueprint> {
		void execute(Element<B> element, Contrivitive gui, int mouseX, int mouseY);
	}

	@SideOnly(Side.CLIENT)
	public interface UpdateAction<B extends GuiBlueprint> {
		void update(Element<B> element, Contrivitive gui);
	}

	public class RelativeSprite {
		public final Sprite sprite;
		public final Coordinate relativePos;

		public RelativeSprite(Sprite sprite, Coordinate relativePos) {
			this.sprite = sprite;
			this.relativePos = relativePos;
		}
	}
}
