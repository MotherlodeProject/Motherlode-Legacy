package contrivitive.gui;

import contrivitive.gui.element.PositionedElement;

import java.util.ArrayList;
import java.util.List;

public class Page {
	public final String name;
	public final int width;
	public final int height;
	public List<PositionedElement> elements = new ArrayList<>();

	public Page(String name, int width, int height) {
		this.name = name;
		this.width = width;
		this.height = height;
	}
}
