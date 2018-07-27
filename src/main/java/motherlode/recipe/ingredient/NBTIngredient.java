package motherlode.recipe.ingredient;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

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

	public NBTIngredient(Block block, NBTTagCompound tag, boolean exactMatch) {
		this(block, 1, tag, exactMatch);
	}

	public NBTIngredient(Block block, int amount, NBTTagCompound tag, boolean exactMatch) {
		this(block, amount, 0, tag, exactMatch);
	}

	public NBTIngredient(Block block, int amount, int meta, NBTTagCompound tag, boolean exactMatch) {
		this(Item.getItemFromBlock(block), amount, meta, tag, exactMatch);
	}

	public NBTIngredient(Item item, NBTTagCompound tag, boolean exactMatch) {
		this(item, 1, tag, exactMatch);
	}

	public NBTIngredient(Item item, int amount, NBTTagCompound tag, boolean exactMatch) {
		this(item, amount, 0, tag, exactMatch);
	}

	public NBTIngredient(Item item, int amount, int meta, NBTTagCompound tag, boolean exactMatch) {
		this(new ItemStack(item, amount, meta), tag, exactMatch);
	}

	public NBTIngredient(ItemStack stack, NBTTagCompound tag, boolean exactMatch) {
		super(stack);
		this.tag = tag;
		this.exactMatch = exactMatch;
		for (ItemStack s : this.getDisplayStacks())
			s.setTagCompound(tag);
	}

	@Override
	public boolean apply(ItemStack input) {
		NBTTagCompound tag = input.getTagCompound();
		if (exactMatch) return super.apply(input) && this.tag.equals(tag);
		else return tag != null && super.apply(input) && matchesExistingTags(this.tag, tag);
	}

	@Override
	public String toString() {
		return String.format("NBTIngredient - Stack: %s, NBT: %s", stack, tag);
	}

	/**
	 * @return If the input param contains all tags with the same values as matcher.  Other tags on input will be ignored, only those present on matcher are checked for.
	 */
	public static boolean matchesExistingTags(NBTTagCompound matcher, NBTTagCompound input) {
		for (String s : matcher.getKeySet())
			if (!input.hasKey(s) || !matcher.getTag(s).equals(input.getTag(s))) return false;
		return true;
	}
}
