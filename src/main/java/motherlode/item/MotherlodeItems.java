package motherlode.item;

import motherlode.registry.MotherlodeRegistry;
import net.minecraft.item.Item;

public class MotherlodeItems {

	private static final String MATERIAL = "material";

	public static final Item AMETHYST = register(new ItemMotherlode("amethyst", MATERIAL));
	public static final Item COPPER = register(new ItemMotherlode("copper", MATERIAL));
	public static final Item ETHERIUM = register(new ItemMotherlode("etherium", MATERIAL));
	public static final Item GOLD = register(new ItemMotherlode("gold", MATERIAL));
	public static final Item IRON = register(new ItemMotherlode("iron", MATERIAL));
	public static final Item NETHERITE = register(new ItemMotherlode("netherite", MATERIAL));
	public static final Item PLANT_FIBRE = register(new ItemMotherlode("plant_fibre", MATERIAL));
	public static final Item PLATINUM = register(new ItemMotherlode("platinum", MATERIAL));
	public static final Item RUBY = register(new ItemMotherlode("ruby", MATERIAL));
	public static final Item SALTPETER = register(new ItemMotherlode("saltpeter", MATERIAL));
	public static final Item SAPPHIRE = register(new ItemMotherlode("sapphire", MATERIAL));
	public static final Item SILVER = register(new ItemMotherlode("silver", MATERIAL));
	public static final Item SULPHUR = register(new ItemMotherlode("sulphur", MATERIAL));
	public static final Item TITANIUM = register(new ItemMotherlode("titanium", MATERIAL));
	public static final Item TOPAZ = register(new ItemMotherlode("topaz", MATERIAL));

	public static final Item BOMB = register(new ItemBomb());
	public static final Item DYNAMITE = register(new ItemDynamite());
	public static final Item BANDAGE = register(new ItemBandage());

	private static Item register(Item item) {
		MotherlodeRegistry.ITEMS.add(item);
		return item;
	}

	public static void load() {}
}
