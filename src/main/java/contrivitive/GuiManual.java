package contrivitive;

import contrivitive.gui.ContrivitiveGuiScreen;
import contrivitive.gui.GuiBlueprint;
import contrivitive.gui.element.Coordinate;
import contrivitive.gui.element.Element;
import contrivitive.gui.element.sprite.AnimatedSprite;
import contrivitive.gui.element.sprite.AnimationFrame;
import contrivitive.gui.element.sprite.Sprites;

public class GuiManual extends ContrivitiveGuiScreen {

	public GuiManual() {
		super(new GuiBlueprint(300, 250)
			.at(10, 10, new Element().sprite(Sprites.SLOT_NORMAL))
			.at(10, 30, new Element().sprite(new AnimatedSprite(18, 18,
					new AnimationFrame(Sprites.SLOT_NORMAL, 20),
					new AnimationFrame(Sprites.SLOT_NORMAL_BUTTON, 80)
				)
			))
			.at(10, 50, new Element().sprite(new AnimatedSprite(18, 18,
					new AnimationFrame(Sprites.SLOT_NORMAL, 2),
					new AnimationFrame(Sprites.SLOT_NORMAL_BUTTON, 2)
				)
			))
			.at(30, 10, new Element().sprite(new AnimatedSprite(18, 18,
					new AnimationFrame(Sprites.SLOT_NORMAL, 30),
					new AnimationFrame(Sprites.SLOT_NORMAL_BUTTON, 12)
				)
			))
			.at(30, 30, new Element().sprite(new AnimatedSprite(18, 18,
					new AnimationFrame(Sprites.SETTINGS_ICON, new Coordinate(1, 1), 51),
					new AnimationFrame(Sprites.SLOT_NORMAL_BUTTON, 37)
				)
			))
			.at("all", 50, 30, new Element().sprite(new AnimatedSprite(18, 18,
					new AnimationFrame(Sprites.SETTINGS_ICON, 1),
					new AnimationFrame(Sprites.JEI_ICON, new Coordinate(10, 10), 1)
				)
			))
		);
	}
}
