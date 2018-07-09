package motherlode.item;

import motherlode.Motherlode;
import motherlode.block.MotherlodeBlocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber(modid = Motherlode.MOD_ID)
@GameRegistry.ObjectHolder(Motherlode.MOD_ID)
public class MotherlodeItems {

	//Util
	private static final Item DUMMY = Items.AIR;
	private static RegistryEvent.Register<Item> event;
	private static final String MATERIAL = "material";

	//Materials
	public static final Item AMETHYST = DUMMY;
	public static final Item COPPER = DUMMY;
	public static final Item ETHERIUM = DUMMY;
	public static final Item GOLD = DUMMY;
	public static final Item IRON = DUMMY;
	public static final Item NETHERITE = DUMMY;
	public static final Item PLANT_FIBRE = DUMMY;
	public static final Item PLATINUM = DUMMY;
	public static final Item RUBY = DUMMY;
	public static final Item SALTPETER = DUMMY;
	public static final Item SAPPHIRE = DUMMY;
	public static final Item SILVER = DUMMY;
	public static final Item SULPHUR = DUMMY;
	public static final Item TITANIUM = DUMMY;
	public static final Item TOPAZ = DUMMY;

	//Items
	public static final Item BOMB = DUMMY;
	public static final Item DYNAMITE = DUMMY;
	public static final Item BANDAGE = DUMMY;

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> e) {
		event = e;

		register(new ItemMotherlode("amethyst", MATERIAL));
		register(new ItemMotherlode("copper", MATERIAL));
		register(new ItemMotherlode("etherium", MATERIAL));
		register(new ItemMotherlode("gold", MATERIAL));
		register(new ItemMotherlode("iron", MATERIAL));
		register(new ItemMotherlode("netherite", MATERIAL));
		register(new ItemMotherlode("plant_fibre", MATERIAL));
		register(new ItemMotherlode("platinum", MATERIAL));
		register(new ItemMotherlode("ruby", MATERIAL));
		register(new ItemMotherlode("saltpeter", MATERIAL));
		register(new ItemMotherlode("sapphire", MATERIAL));
		register(new ItemMotherlode("silver", MATERIAL));
		register(new ItemMotherlode("sulphur", MATERIAL));
		register(new ItemMotherlode("titanium", MATERIAL));
		register(new ItemMotherlode("topaz", MATERIAL));

		register(new ItemBomb("bomb"));
		register(new ItemDynamite("dynamite"));
		register(new ItemBandage("bandage"));

		for (ItemBlock item : MotherlodeBlocks.getItemBlocks()) {
			register(item);
		}
	}

	private static void register(Item item) {
		event.getRegistry().register(item);
	}
}
