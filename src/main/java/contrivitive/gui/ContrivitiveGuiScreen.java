package contrivitive.gui;

import contrivitive.gui.element.PositionedElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

public class ContrivitiveGuiScreen extends GuiScreen implements IContrivitiveGui<GuiScreen, GuiBlueprint> {

	private final GuiBlueprint blueprint;
	private int width;
	private int height;
	private int startX;
	private int startY;
	private Page currentPage;

	public ContrivitiveGuiScreen(GuiBlueprint blueprint) {
		this.blueprint = blueprint;
	}

	@Override
	public void initGui() {
		super.initGui();
		currentPage = blueprint.pages.get("main");
		this.width = blueprint.width;
		this.height = blueprint.height;
		startX = super.width / 2 - this.width / 2;
		startY = super.height / 2 - this.height / 2;
	}

	@Override
	public final GuiBlueprint getGuiBlueprint() {
		return blueprint;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.drawDefaultBackground();
		this.width = currentPage.width;
		this.height = currentPage.height;
		startX = super.width / 2 - this.width / 2;
		startY = super.height / 2 - this.height / 2;

		for (PositionedElement element : blueprint.allPages.elements) {
			element.element.draw(this, element.coordinate.x, element.coordinate.y, mouseX, mouseY, partialTicks);
		}

		for (PositionedElement element : currentPage.elements) {
			element.element.draw(this, element.coordinate.x, element.coordinate.y, mouseX, mouseY, partialTicks);
		}
	}

	@Override
	public GuiScreen getGui() {
		return this;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getStartingX() {
		return startX;
	}

	@Override
	public int getStartingY() {
		return startY;
	}
}
