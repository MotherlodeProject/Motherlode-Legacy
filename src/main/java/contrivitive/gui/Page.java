package contrivitive.gui;

import contrivitive.gui.element.PlayerSpecificElements;
import contrivitive.gui.element.PositionedElement;
import contrivitive.gui.element.slot.PlayerSlotElement;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

public class Page {
	public final List<Pair<IntSupplier, IntConsumer>> shortSyncables = new ArrayList<>();
	public final List<Pair<IntSupplier, IntConsumer>> intSyncables = new ArrayList<>();
	public final String name;
	public final int width;
	public final int height;
	public List<PositionedElement> elements = new ArrayList<>();
	public PlayerSpecificElements playerSpecificElements;

	//Only used in containers
	public int playerInvX = -1;
	public int playerInvY = -1;
	public HashMap<Integer, GuiContainerBlueprint.SlotEntry<?>> slots = new HashMap<>();
	public HashMap<Integer, PlayerSlotElement> playerSlots = new HashMap<>();
	public boolean justPlayerInv = true;
	public GetGuiInt guiLeft = (width1, height1, xSize, ySize, screenWidth, screenHeight) -> (width1 - xSize) / 2;
	public GetGuiInt guiTop = (width1, height1, xSize, ySize, screenWidth, screenHeight) -> (height1 - ySize) / 2;

	public Page(String name, int width, int height) {
		this(name, width, height, player -> new ArrayList<>());
	}

	public Page(String name, int width, int height, PlayerSpecificElements playerSpecificElements) {
		this.name = name;
		this.width = width;
		this.height = height;
		this.playerSpecificElements = playerSpecificElements;
	}

	public Page setPlayerSpecificElements(PlayerSpecificElements playerSpecificElements) {
		this.playerSpecificElements = playerSpecificElements;
		return this;
	}
}
