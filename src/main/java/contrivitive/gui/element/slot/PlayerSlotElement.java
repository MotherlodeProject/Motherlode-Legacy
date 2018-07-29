package contrivitive.gui.element.slot;

import contrivitive.gui.GuiBlueprint;
import contrivitive.gui.IContrivitiveGui;
import contrivitive.gui.element.Element;
import contrivitive.gui.element.sprite.Sprites;
import contrivitive.util.ContrivitivePlayerSlot;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PlayerSlotElement extends Element {
	public ContrivitivePlayerSlot.SlotFilter filter = (slot, stack) -> true;
	protected String slotTexture = null;
	protected int stackSize = 64;
	int x, y;
	boolean draw;

	public PlayerSlotElement(int x, int y) {
		this(x, y, true);
	}

	public PlayerSlotElement(int x, int y, boolean draw) {
		this.x = x;
		this.y = y;
		this.draw = draw;
		sprite(Sprites.SLOT_NORMAL);
	}

	public int getX() {
		return x + 1;
	}

	public int getY() {
		return y + 1;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public <G extends GuiScreen, B extends GuiBlueprint> void draw(IContrivitiveGui<G,B> gui, int x, int y, int mouseX, int mouseY, float elapsedTicks) {
		if (draw) {
			super.draw(gui, x, y, mouseX, mouseY, elapsedTicks);
		}
	}

	public ContrivitivePlayerSlot.SlotFilter getFilter() {
		return filter;
	}

	public PlayerSlotElement setFilter(ContrivitivePlayerSlot.SlotFilter filter) {
		this.filter = filter;
		return this;
	}

	public String getSlotTexture() {
		return slotTexture;
	}

	public PlayerSlotElement setSlotTexture(String slotTexture) {
		this.slotTexture = slotTexture;
		return this;
	}

	public int getStackSize() {
		return stackSize;
	}

	public PlayerSlotElement setStackSize(int stackSize) {
		this.stackSize = stackSize;
		return this;
	}
}
