package motherlode.util;

import motherlode.Motherlode;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ItemUtil {
	public static void setupItem(Item item, String name) {
		item.setRegistryName(new ResourceLocation(Motherlode.MOD_ID, name));
		item.setUnlocalizedName(Motherlode.MOD_ID + "." + name);
		item.setCreativeTab(Motherlode.TAB);
	}
}
