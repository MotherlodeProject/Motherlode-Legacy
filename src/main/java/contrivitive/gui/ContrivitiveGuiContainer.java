package contrivitive.gui;

import contrivitive.gui.element.PositionedElement;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;

public class ContrivitiveGuiContainer extends GuiContainer implements IContrivitiveGui<GuiContainer, GuiContainerBlueprint> {

	public final EntityPlayer player;
	private final GuiContainerBlueprint blueprint;
	private Page currentPage;
	private int offsetFactorX;
	private int offsetFactorY;

	public ContrivitiveGuiContainer(ContrivitiveContainer container) {
		super(container);
		this.blueprint = container.blueprint;
		this.player = container.player;
		this.xSize = blueprint.width;
		this.ySize = blueprint.height;
	}

	@Override
	public void initGui() {
		super.initGui();
		currentPage = blueprint.pages.get("main");
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
		offsetFactorX = guiLeft;
		offsetFactorY = guiTop;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		offsetFactorX = guiLeft;
		offsetFactorY = guiTop;

		for (PositionedElement element : blueprint.allPages.elements) {
			element.element.draw(this, element.coordinate.x, element.coordinate.y, mouseX, mouseY, partialTicks);
		}

		for (PositionedElement element : currentPage.elements) {
			element.element.draw(this, element.coordinate.x, element.coordinate.y, mouseX, mouseY, partialTicks);
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		offsetFactorX = 0;
		offsetFactorY = 0;
	}

	@Override
	public final GuiContainerBlueprint getGuiBlueprint() {
		return blueprint;
	}

	@Override
	public GuiContainer getGui() {
		return this;
	}

	@Override
	public int getWidth() {
		return xSize;
	}

	@Override
	public int getHeight() {
		return ySize;
	}

	@Override
	public int getStartingX() {
		return offsetFactorX;
	}

	@Override
	public int getStartingY() {
		return offsetFactorY;
	}
}
