package contrivitive.gui;

import contrivitive.gui.element.Coordinate;
import contrivitive.gui.element.Element;
import contrivitive.gui.element.PositionedElement;
import contrivitive.gui.element.background.DefaultBackgroundElement;
import contrivitive.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

public class GuiBlueprint {
	public final List<Pair<IntSupplier, IntConsumer>> shortSyncables = new ArrayList<>();
	public final List<Pair<IntSupplier, IntConsumer>> intSyncables = new ArrayList<>();
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
}
