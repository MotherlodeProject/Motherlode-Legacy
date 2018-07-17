package contrivitive.util;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class ContrivitivePlayerSlot extends Slot {
	public SlotFilter filter = (slot, stack) -> true;

	public ContrivitivePlayerSlot(InventoryPlayer inventory, int index, int xPosition, int yPosition) {
		super(inventory, index, xPosition, yPosition);
	}

	public ContrivitivePlayerSlot setFilter(SlotFilter filter) {
		this.filter = filter;
		return this;
	}

	@SideOnly(Side.CLIENT)
	public ContrivitivePlayerSlot setSprite(ResourceLocation backgroundLocation) {
		this.setBackgroundLocation(backgroundLocation);
		return this;
	}

	@Override
	public boolean isItemValid(
		@Nonnull
			ItemStack stack) {
		return !stack.isEmpty() && filter.isItemValid(this, stack) && super.isItemValid(stack);
	}

	public interface SlotFilter {
		public boolean isItemValid(ContrivitivePlayerSlot slot, ItemStack stack);
	}
}
