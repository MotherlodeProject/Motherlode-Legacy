package contrivitive.gui.element.sprite;

import contrivitive.lib.ContrivitiveConstants;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;

import java.util.HashMap;

public class Sprites {

	public static final HashMap<ResourceLocation, Sprite> SPRITES = new HashMap<>();
	public static final HashMap<ResourceLocation, ResourceLocation> TEXTURE_SHEETS = new HashMap<>();

	public static final ResourceLocation BACKGROUND = register("background", new ResourceLocation(ContrivitiveConstants.MOD_ID, "textures/gui/background.png"));
	public static final ResourceLocation SHEET = register("sheet", new ResourceLocation(ContrivitiveConstants.MOD_ID, "textures/gui/sheet.png"));

	public static final Sprite SLOT_NORMAL = register("slot_normal", new SheetSprite(SHEET, 0, 0, 18, 18));
	public static final Sprite SLOT_NORMAL_BUTTON = register("slot_button", new SheetSprite(SHEET, 18, 0, 18, 18));
	public static final Sprite SETTINGS_ICON = register("settings_icon", new SheetSprite(SHEET, 37, 1, 16, 16));
	public static final Sprite JEI_ICON = register("jei_icon", new SheetSprite(SHEET, 55, 1, 16, 16));
	public static final Sprite BURN_BAR_EMPTY = register("burn_bar_empty", new SheetSprite(SHEET, 74, 2, 14, 14));
	public static final Sprite BURN_BAR_FULL = register("burn_bar_full", new SheetSprite(SHEET, 92, 2, 14, 14));
	public static final Sprite X_ICON_ODD = register("x_icon_odd", new SheetSprite(SHEET, 109, 1, 15, 15));
	public static final Sprite X_ICON_EVEN = register("x_icon_even", new SheetSprite(SHEET, 127, 1, 16, 16));

	public static Sprite register(ResourceLocation resourceLocation, Sprite sprite) {
		SPRITES.put(resourceLocation, sprite);
		return sprite;
	}

	public static ResourceLocation register(ResourceLocation resourceLocation, ResourceLocation textureSheet) {
		TEXTURE_SHEETS.put(resourceLocation, textureSheet);
		return textureSheet;
	}

	public static Sprite register(String name, Sprite sprite) {
		return register(new ResourceLocation(Loader.instance().activeModContainer().getModId(), name), sprite);
	}

	public static ResourceLocation register(String name, ResourceLocation textureSheet) {
		return register(new ResourceLocation(Loader.instance().activeModContainer().getModId(), name), textureSheet);
	}
}
