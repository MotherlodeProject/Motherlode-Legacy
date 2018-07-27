package motherlode.recipe.ingredient;

import motherlode.util.OreDictUtil;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

public class OreIngredient implements IIngredient {
	String oreDict;
	int amount;
	NonNullList<ItemStack> displayStacks = NonNullList.create();

	public OreIngredient(String oreDict) {
		this(oreDict, 1);
	}

	public OreIngredient(String oreDict, int amount) {
		this.oreDict = oreDict;
		this.amount = amount;
	}

	@Override
	public boolean isStackGreaterOrEqual(ItemStack stack) {
		return stack.getCount() >= amount && OreDictUtil.isOre(stack, oreDict);
	}

	@Override
	public List<ItemStack> getItemsForDisplay() {
		if (displayStacks.isEmpty()) {
			List<ItemStack> ores = OreDictionary.getOres(oreDict);
			for (ItemStack stack : ores) {
				if (stack.getMetadata() == OreDictionary.WILDCARD_VALUE) {
					stack.getItem().getSubItems(CreativeTabs.SEARCH, displayStacks);
				} else {
					displayStacks.add(stack);
				}
			}
		}
		return displayStacks;
	}

	@Override
	public int getAmount() {
		return amount;
	}
}
