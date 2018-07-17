package contrivitive.gui;

import contrivitive.gui.element.Coordinate;
import contrivitive.gui.element.Element;
import contrivitive.gui.element.PlayerSpecificElements;
import contrivitive.gui.element.PositionedElement;
import contrivitive.gui.element.background.DefaultBackgroundElement;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

public class GuiBlueprint {
	public final int width;
	public final int height;
	public final Page allPages;
	public Map<String, Page> pages = new HashMap<>();

	public GuiBlueprint(int width, int height, boolean defaultBackground) {
		this.width = width;
		this.height = height;
		pages.put("main", new Page("main", width, height));
		allPages = new Page("all", width, height);
		if (defaultBackground)
			at("all", 0, 0, new DefaultBackgroundElement());
	}

	public GuiBlueprint(int width, int height) {
		this(width, height, true);
	}

	public GuiBlueprint page(String name) {
		this.pages.put(name, new Page(name, width, height));
		return this;
	}

	public GuiBlueprint page(Page page) {
		this.pages.put(page.name, page);
		return this;
	}

	public GuiBlueprint setPlayerSpecificElements(String page, PlayerSpecificElements playerSpecificElements) {
		if (page.equals("all")) {
			allPages.playerSpecificElements = playerSpecificElements;
		}
		if (!pages.containsKey(page)) {
			page(page);
		}
		this.pages.get(page).playerSpecificElements = playerSpecificElements;
		return this;
	}

	public GuiBlueprint at(int x, int y, Element element) {
		return at("main", x, y, element);
	}

	public GuiBlueprint at(String page, int x, int y, Element element) {
		if (page.equals("all")) {
			allPages.elements.add(new PositionedElement(element, new Coordinate(x, y)));
			return this;
		}
		if (!pages.containsKey(page)) {
			page(page);
		}
		this.pages.get(page).elements.add(new PositionedElement(element, new Coordinate(x, y)));
		return this;
	}

	public GuiBlueprint syncIntegerValue(String page, final IntSupplier supplier, final IntConsumer setter) {
		getPage(page).intSyncables.add(Pair.of(supplier, setter));
		return this;
	}

	public GuiBlueprint syncShortValue(String page, final IntSupplier supplier, final IntConsumer setter) {
		getPage(page).shortSyncables.add(Pair.of(supplier, setter));
		return this;
	}

	public Page getPage(String page) {
		if (page.equals("all")) {
			return allPages;
		}
		if (!pages.containsKey(page)) {
			page(page);
		}
		return this.pages.get(page);
	}
}
