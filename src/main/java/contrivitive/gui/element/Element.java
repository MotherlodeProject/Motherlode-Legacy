package contrivitive.gui.element;

import contrivitive.gui.IContrivitiveGui;
import contrivitive.gui.element.sprite.Sprite;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class Element {
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
	public boolean buttonClick = true;
	public InteractionAction drawTooltip = (element, gui, mouseX, mouseY) -> {
	};
	public List<InteractionAction> hoverActions = new ArrayList<>();
	public List<InteractionAction> dragActions = new ArrayList<>();
	public List<InteractionAction> startPressActions = new ArrayList<>();
	public List<InteractionAction> pressActions = new ArrayList<>();
	public List<InteractionAction> releaseActions = new ArrayList<>();
	public List<UpdateAction> updateActions = new ArrayList<>();

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

	@SideOnly(Side.CLIENT)
	public void draw(IContrivitiveGui gui, int x, int y, int mouseX, int mouseY, float elapsedTicks) {
		for (RelativeSprite sprite : sprites) {
			sprite.sprite.draw(gui, x + sprite.relativePos.x, y + sprite.relativePos.y, elapsedTicks);
		}
	}

	@SideOnly(Side.CLIENT)
	public void renderUpdate(GuiScreen gui) {
		isHoveringLast = isHovering;
		isPressingLast = isPressing;
		isDraggingLast = isDragging;
		isReleasingLast = isReleasing;
	}

	@SideOnly(Side.CLIENT)
	public interface InteractionAction {
		void execute(Element element, GuiScreen gui, int mouseX, int mouseY);
	}

	@SideOnly(Side.CLIENT)
	public interface UpdateAction {
		void update(Element element, GuiScreen gui);
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