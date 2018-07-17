package motherlode.gui;

import contrivitive.gui.element.sprite.SheetSprite;
import contrivitive.gui.element.sprite.Sprite;
import contrivitive.gui.element.sprite.Sprites;
import motherlode.Motherlode;
import net.minecraft.util.ResourceLocation;

public class MLSprites {
	public static final ResourceLocation INVENTORY = Sprites.register("inventory", new ResourceLocation(Motherlode.MOD_ID, "textures/gui/inventory.png"));

	public static final Sprite INVENTORY_BACKGROUND = Sprites.register("inventory_background", new SheetSprite(INVENTORY, 0, 0, 176, 187));
	public static final Sprite CRAFTING_BACKGROUND = Sprites.register("crafting", new SheetSprite(INVENTORY, 176, 0, 70, 170));
	public static final Sprite SEARCH_BAR = Sprites.register("search_bar", new SheetSprite(INVENTORY, 176, 170, 55, 8));
	public static final Sprite SELECTED_SEARCH_BAR = Sprites.register("selected_search_bar", new SheetSprite(INVENTORY, 176, 178, 55, 8));
	public static final Sprite CRAFTING_SLOT = Sprites.register("crafting_slot", new SheetSprite(INVENTORY, 194, 186, 18, 18));
	public static final Sprite CRAFTING_TEXT_BACKGROUND = Sprites.register("crafting_text_background", new SheetSprite(INVENTORY, 212, 186, 37, 18));
	public static final Sprite HOVERED_CRAFTING_SLOT = Sprites.register("hovered_crafting_slot", new SheetSprite(INVENTORY, 176, 186, 18, 18));
	public static final Sprite SCROLL_BAR = Sprites.register("scroll_bar", new SheetSprite(INVENTORY, 246, 7, 7, 164));
	public static final Sprite SCROLL_BAR_THUMB = Sprites.register("scroll_bar_thumb", new SheetSprite(INVENTORY, 246, 0, 7, 7));
}
