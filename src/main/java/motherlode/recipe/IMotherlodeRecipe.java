package motherlode.recipe;

import contrivitive.gui.element.sprite.ItemStackSprite;
import contrivitive.gui.element.sprite.Sprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public interface IMotherlodeRecipe {
	public ItemStack getOutput();

	public List<Object> getInputs();

	public default int getProcessTime() {
		return 0;
	}

	public ResourceLocation getRegistryName();

	public IRecipeTable getRecipeTable();

	public default boolean canCraft(EntityPlayer player) {
		return true;
	}

	public default String getDisplayName() {
		return getOutput().getDisplayName();
	}

	public default Sprite getSprite() {
		return new ItemStackSprite(getOutput());
	}

}
