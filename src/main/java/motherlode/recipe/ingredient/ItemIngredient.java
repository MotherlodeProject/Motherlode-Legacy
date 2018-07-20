package motherlode.recipe.ingredient;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

public class ItemIngredient implements IRecipeIngredient {

	Item item;
	int amount;
	int meta;
	NBTTagCompound requiredNBT;
	//If true, tag compound must be .equal(), if false tag compount must contain the same keys and values as in requiredNBT, and ignore others
	boolean exactTagMatch;
	NonNullList<ItemStack> displayStacks = NonNullList.create();

	public ItemIngredient(Block block) {
		this(block, 0);
	}

	public ItemIngredient(Block block, int amount) {
		this(block, amount, 0);
	}

	public ItemIngredient(Block block, int amount, int meta) {
		this(block, amount, meta, null, false);
	}

	public ItemIngredient(Block block, int amount, int meta, NBTTagCompound requiredNBT, boolean exactTagMatch) {
		this(Item.getItemFromBlock(block), amount, meta, requiredNBT, exactTagMatch);
	}

	public ItemIngredient(Item item) {
		this(item, 0);
	}

	public ItemIngredient(Item item, int amount) {
		this(item, amount, 0);
	}

	public ItemIngredient(Item item, int amount, int meta) {
		this(item, amount, meta, null, false);
	}

	public ItemIngredient(Item item, int amount, int meta, NBTTagCompound requiredNBT, boolean exactTagMatch) {
		this.item = item;
		this.amount = amount;
		this.meta = meta;
		this.requiredNBT = requiredNBT;
	}

	@Override
	public boolean isStackGreaterOrEqual(ItemStack stack) {
		if (stack.getItem() == item && stack.getMetadata() == meta && stack.getCount() >= amount) {
			if (requiredNBT != null) {
				if (stack.getTagCompound() != null) {
					if (exactTagMatch) {
						return stack.getTagCompound().equals(requiredNBT);
					} else {
						for (String requiredKey : requiredNBT.getKeySet()) {
							if (!stack.getTagCompound().getKeySet().contains(requiredKey)) {
								return false;
							}
							if (stack.getTagCompound().getTag(requiredKey).equals(requiredNBT.getTag(requiredKey))) {
								return false;
							}
						}
						return true;
					}
				}
			} else {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<ItemStack> getItemsForDisplay() {
		if (displayStacks.isEmpty()) {
			if (meta == OreDictionary.WILDCARD_VALUE) {
				item.getSubItems(CreativeTabs.SEARCH, displayStacks);
			} else {
				ItemStack stack = new ItemStack(item, amount, meta);
				stack.setTagCompound(requiredNBT);
				displayStacks.add(stack);
			}
		}
		return displayStacks;
	}

	@Override
	public int getAmount() {
		return amount;
	}
}
