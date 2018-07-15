package motherlode.util;

import motherlode.Motherlode;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class InitUtil {
	public static void setup(Item item, String name) {
		item.setRegistryName(new ResourceLocation(Motherlode.MOD_ID, name));
		item.setUnlocalizedName(Motherlode.MOD_ID + "." + name);
		item.setCreativeTab(Motherlode.TAB);
	}

	public static void setup(Block block, String name) {
		block.setRegistryName(new ResourceLocation(Motherlode.MOD_ID, name));
		block.setUnlocalizedName(Motherlode.MOD_ID + "." + name);
		block.setCreativeTab(Motherlode.TAB);
	}
}
