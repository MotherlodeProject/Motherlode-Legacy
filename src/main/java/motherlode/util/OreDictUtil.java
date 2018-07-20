package motherlode.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictUtil {
	public static boolean isOre(ItemStack stack, String oreName) {
		if (!stack.isEmpty() && oreName != null) {
			int id = OreDictionary.getOreID(oreName);
			int[] ids = OreDictionary.getOreIDs(stack);
			for (int i : ids) {
				if (id == i) {
					return true;
				}
			}
		}
		return false;
	}
}
