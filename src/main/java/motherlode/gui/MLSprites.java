package motherlode.gui;

import contrivitive.gui.element.sprite.SheetSprite;
import contrivitive.gui.element.sprite.Sprite;
import contrivitive.gui.element.sprite.Sprites;
import motherlode.Motherlode;
import net.minecraft.util.ResourceLocation;

public class MLSprites {
	public static final ResourceLocation INVENTORY_SHEET = Sprites.register("inventory", new ResourceLocation(Motherlode.MOD_ID, "textures/gui/inventory.png"));
	public static final ResourceLocation CRAFTING_SHEET = Sprites.register("crafting", new ResourceLocation(Motherlode.MOD_ID, "textures/gui/crafting.png"));

	public static final Sprite INVENTORY_BACKGROUND = Sprites.register("inventory_background", new SheetSprite(INVENTORY_SHEET, 0, 0, 176, 187));
	public static final Sprite CRAFTING_BACKGROUND = Sprites.register("crafting_background", new SheetSprite(CRAFTING_SHEET, 0, 0, 140, 170));
	public static final Sprite SEARCH_BAR = Sprites.register("search_bar", new SheetSprite(CRAFTING_SHEET, 0, 210, 116, 13));
	public static final Sprite SELECTED_SEARCH_BAR = Sprites.register("selected_search_bar", new SheetSprite(CRAFTING_SHEET, 0, 223, 116, 13));
	public static final Sprite CRAFTING_SLOT = Sprites.register("crafting_slot", new SheetSprite(CRAFTING_SHEET, 0, 170, 140, 20));
	public static final Sprite HOVERED_CRAFTING_SLOT = Sprites.register("hovered_crafting_slot", new SheetSprite(CRAFTING_SHEET, 0, 190, 140, 20));
	public static final Sprite EXPAND_BUTTON = Sprites.register("expand_button", new SheetSprite(CRAFTING_SHEET, 192, 210, 13, 13));
	public static final Sprite SELECTED_EXPAND_BUTTON = Sprites.register("selected_expand_button", new SheetSprite(CRAFTING_SHEET, 192, 223, 13, 13));
	public static final Sprite COLLAPSE_BUTTON = Sprites.register("collapse_button", new SheetSprite(CRAFTING_SHEET, 116, 210, 13, 13));
	public static final Sprite SELECTED_COLLAPSE_BUTTON = Sprites.register("selected_collapse_button", new SheetSprite(CRAFTING_SHEET, 116, 223, 13, 13));
}
