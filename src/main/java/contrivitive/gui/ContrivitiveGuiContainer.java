package contrivitive.gui;

import contrivitive.gui.element.Element;
import contrivitive.gui.element.PositionedElement;
import contrivitive.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContrivitiveGuiContainer extends GuiContainer implements IContrivitiveGui<GuiContainer, GuiContainerBlueprint> {

	public final EntityPlayer player;
	private final GuiContainerBlueprint blueprint;
	private Page page;
	private List<PositionedElement> elements;
	private int offsetFactorX;
	private int offsetFactorY;

	public ContrivitiveGuiContainer(ContrivitiveContainer container) {
		super(container);
		this.blueprint = container.blueprint;
		this.player = container.player;
		this.xSize = blueprint.width;
		this.ySize = blueprint.height;
		this.page = container.page;
		elements = new ArrayList<>();
		elements.addAll(blueprint.allPages.elements);
		elements.addAll(this.page.elements);
		for (PositionedElement element : elements) {
			element.element.initClient();
		}
	}

	@Override
	public void initGui() {
		super.initGui();
		this.guiTop = page.guiTop.get(width, height, xSize, ySize, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
		this.guiLeft = page.guiLeft.get(width, height, xSize, ySize, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
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
	public void updateScreen() {
		for (PositionedElement element : elements) {
			for (Element.UpdateAction updateAction : element.element.updateActions) {
				updateAction.update(element.element, this);
			}
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		offsetFactorX = guiLeft;
		offsetFactorY = guiTop;

		for (PositionedElement element : elements) {
			element.element.draw(this, element.coordinate.x, element.coordinate.y, mouseX, mouseY, partialTicks);
		}

		for (int i = elements.size() - 1; i >= 0; i--) {
			PositionedElement element = elements.get(i);
			if (RenderUtil.isInRect(this, element.coordinate.x, element.coordinate.y, element.element.width, element.element.height, mouseX, mouseY)) {
				element.element.isHovering = true;
				for (Element.InteractionAction hoverAction : element.element.hoverActions) {
					hoverAction.execute(element.element, this, element.coordinate, mouseX + element.coordinate.x, mouseY + element.coordinate.y);
				}
				for (PositionedElement e : elements) {
					if (e != element) {
						e.element.isHovering = false;
					}
				}
				break;
			} else {
				element.element.isHovering = false;
			}
			element.element.renderUpdate(this);
		}
	}

	@Override
	protected void renderHoveredToolTip(int mouseX, int mouseY) {
		super.renderHoveredToolTip(mouseX, mouseY);
		offsetFactorX = guiLeft;
		offsetFactorY = guiTop;
		for (int i = elements.size() - 1; i >= 0; i--) {
			PositionedElement element = elements.get(i);
			if (RenderUtil.isInRect(this, element.coordinate.x, element.coordinate.y, element.element.width, element.element.height, mouseX, mouseY)) {
				element.element.drawTooltip.execute(element.element, this, element.coordinate, mouseX, mouseY);
				break;
			}
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		offsetFactorX = 0;
		offsetFactorY = 0;
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		offsetFactorX = guiLeft;
		offsetFactorY = guiTop;
		if (mouseButton == 0) {
			for (int i = elements.size() - 1; i >= 0; i--) {
				PositionedElement element = elements.get(i);
				if (RenderUtil.isInRect(this, element.coordinate.x, element.coordinate.y, element.element.width, element.element.height, mouseX, mouseY)) {
					element.element.isPressing = true;
					for (Element.InteractionAction startPressAction : element.element.startPressActions) {
						startPressAction.execute(element.element, this, element.coordinate, mouseX, mouseY);
					}
					for (PositionedElement e : elements) {
						if (e != element) {
							e.element.isPressing = false;
						}
					}
					break;
				} else {
					element.element.isPressing = false;
				}
			}
		}
	}

	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int mouseButton, long timeSinceLastClick) {
		super.mouseClickMove(mouseX, mouseY, mouseButton, timeSinceLastClick);
		offsetFactorX = guiLeft;
		offsetFactorY = guiTop;
		if (mouseButton == 0) {
			for (int i = elements.size() - 1; i >= 0; i--) {
				PositionedElement element = elements.get(i);
				if (RenderUtil.isInRect(this, element.coordinate.x, element.coordinate.y, element.element.width, element.element.height, mouseX, mouseY)) {
					element.element.isDragging = true;
					for (Element.InteractionAction dragAction : element.element.dragActions) {
						dragAction.execute(element.element, this, element.coordinate, mouseX, mouseY);
					}
					for (PositionedElement e : elements) {
						if (e != element) {
							e.element.isDragging = false;
						}
					}
					break;
				} else {
					element.element.isDragging = false;
				}
			}
		}
	}

	@Override
	protected void mouseReleased(int mouseX, int mouseY, int mouseButton) {
		super.mouseReleased(mouseX, mouseY, mouseButton);
		offsetFactorX = guiLeft;
		offsetFactorY = guiTop;
		if (mouseButton == 0) {
			for (int i = elements.size() - 1; i >= 0; i--) {
				PositionedElement element = elements.get(i);
				if (RenderUtil.isInRect(this, element.coordinate.x, element.coordinate.y, element.element.width, element.element.height, mouseX, mouseY)) {
					element.element.isReleasing = true;
					for (Element.InteractionAction releaseAction : element.element.releaseActions) {
						releaseAction.execute(element.element, this, element.coordinate, mouseX, mouseY);
					}
					if (element.element.isPressing) {
						for (Element.InteractionAction pressAction : element.element.pressActions) {
							pressAction.execute(element.element, this, element.coordinate, mouseX, mouseY);
						}
						if (!element.element.pressActions.isEmpty() && element.element.buttonClick) {
							Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
						}
					}
					for (PositionedElement e : elements) {
						if (e != element) {
							e.element.isReleasing = false;
						}
					}
					break;
				} else {
					element.element.isReleasing = false;
				}
			}
		}
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

	@Override
	public RenderItem getRenderItem() {
		return itemRender;
	}
}
