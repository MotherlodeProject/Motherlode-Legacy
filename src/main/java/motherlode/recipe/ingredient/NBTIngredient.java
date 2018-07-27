package motherlode.recipe.ingredient;

import java.util.List;

import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntLists;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;

/**
 * An Ingredient which can match NBT, either lenient or strictly.
 * @author Shadows
 *
 */
public class NBTIngredient extends SimpleIngredient {

	/**
	 * The matching NBTTagCompound.  Must not be mutated.  Might be some interest in making an Immutable Tag.
	 */
	NBTTagCompound tag;

	/**
	 * If the ingredient will try to match ALL tags exactly, allowing no other tags to exist, instead of trying to match only tags present on requiredNBT.
	 */
	boolean exactMatch;

	public NBTIngredient(Block block) {
		this(block, 0);
	}

	public NBTIngredient(Block block, int amount) {
		this(block, amount, 0);
	}

	public NBTIngredient(Block block, int amount, int meta) {
		this(block, amount, meta, null, false);
	}

	public NBTIngredient(Block block, int amount, int meta, NBTTagCompound tag, boolean exactMatch) {
		this(Item.getItemFromBlock(block), amount, meta, tag, exactMatch);
	}

	public NBTIngredient(Item item) {
		this(item, 0);
	}

	public NBTIngredient(Item item, int amount) {
		this(item, amount, 0);
	}

	public NBTIngredient(Item item, int amount, int meta) {
		this(item, amount, meta, null, false);
	}

	public NBTIngredient(Item item, int amount, int meta, NBTTagCompound tag, boolean exactMatch) {
		this(new ItemStack(item, amount, meta), tag, exactMatch);
	}

	public NBTIngredient(ItemStack stack, NBTTagCompound tag, boolean exactMatch) {
		super(stack);
		this.tag = tag;
		this.exactMatch = exactMatch;
	}

	@Override
	public boolean apply(ItemStack input) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSimple() {
		return false;
	}

	@Override
	public IntList getMatchingStacksPacked() {
		return IntLists.EMPTY_LIST;
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
							if (!stack.getTagCompound().getKeySet().contains(requiredKey)) { return false; }
							if (stack.getTagCompound().getTag(requiredKey).equals(requiredNBT.getTag(requiredKey))) { return false; }
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
}
