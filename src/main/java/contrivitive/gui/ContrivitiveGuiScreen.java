package contrivitive.gui;

import contrivitive.gui.element.Element;
import contrivitive.gui.element.PositionedElement;
import contrivitive.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.init.SoundEvents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContrivitiveGuiScreen extends GuiScreen implements IContrivitiveGui<GuiScreen, GuiBlueprint> {

	private final GuiBlueprint blueprint;
	private int width;
	private int height;
	private int startX;
	private int startY;
	private Page page;
	private List<PositionedElement> elements;

	public ContrivitiveGuiScreen(GuiBlueprint blueprint) {
		this(blueprint, "main");
	}

	public ContrivitiveGuiScreen(GuiBlueprint blueprint, String page) {
		this.blueprint = blueprint;
		this.page = blueprint.pages.get(page);
		elements = new ArrayList<>();
		elements.addAll(blueprint.allPages.elements);
		elements.addAll(this.page.elements);
	}

	@Override
	public void initGui() {
		super.initGui();
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
	public void updateScreen() {
		for (PositionedElement element : elements) {
			for (Element.UpdateAction updateAction : element.element.updateActions) {
				updateAction.update(element.element, this);
			}
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.drawDefaultBackground();
		this.width = page.width;
		this.height = page.height;
		startX = super.width / 2 - this.width / 2;
		startY = super.height / 2 - this.height / 2;

		for (PositionedElement element : elements) {
			element.element.draw(this, element.coordinate.x, element.coordinate.y, mouseX, mouseY, partialTicks);
		}

		for (int i = elements.size() - 1; i >= 0; i--) {
			PositionedElement element = elements.get(i);
			if (RenderUtil.isInRect(this, element.coordinate.x, element.coordinate.y, element.element.width, element.element.height, mouseX, mouseY)) {
				element.element.isHovering = true;
				for (Element.InteractionAction hoverAction : element.element.hoverActions) {
					hoverAction.execute(element.element, this, element.coordinate, mouseX, mouseY);
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

		for (int i = elements.size() - 1; i >= 0; i--) {
			PositionedElement element = elements.get(i);
			if (RenderUtil.isInRect(this, element.coordinate.x, element.coordinate.y, element.element.width, element.element.height, mouseX, mouseY)) {
				element.element.drawTooltip.execute(element.element, this, element.coordinate, mouseX, mouseY);
				break;
			}
		}
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
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

	@Override
	public RenderItem getRenderItem() {
		return itemRender;
	}
}
