package contrivitive.gui;

import contrivitive.util.ContrivitivePlayerSlot;
import contrivitive.util.ContrivitiveSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.MutableTriple;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

public class ContrivitiveContainer extends Container {
	public final GuiContainerBlueprint blueprint;
	public final EntityPlayer player;
	public final Page page;
	public final ArrayList<MutableTriple<IntSupplier, IntConsumer, Integer>> integerValues = new ArrayList<>();
	public final ArrayList<MutableTriple<IntSupplier, IntConsumer, Short>> shortValues = new ArrayList<>();
	private Integer[] integerParts;

	public ContrivitiveContainer(GuiContainerBlueprint blueprint, String page, EntityPlayer player) {
		this.blueprint = blueprint;
		this.page = blueprint.pages.get(page);
		this.player = player;
		for (Pair<IntSupplier, IntConsumer> syncable : this.page.shortSyncables)
			this.shortValues.add(MutableTriple.of(syncable.getLeft(), syncable.getRight(), (short) 0));
		this.shortValues.trimToSize();

		for (Pair<IntSupplier, IntConsumer> syncable : this.page.intSyncables) {
			this.integerValues.add(MutableTriple.of(syncable.getLeft(), syncable.getRight(), 0));
		}
		this.integerValues.trimToSize();

		this.integerParts = new Integer[this.integerValues.size()];
		addSlots();
		addPlayerSlots();
	}

	private void addSlots() {
		for (int index : page.slots.keySet()) {
			if (index >= 0) {
				addSlotToContainer(new ContrivitiveSlot(page.slots.get(index).getInventory(), index, page.slots.get(index).getElement().getSlotX(), page.slots.get(index).getElement().getSlotY()).setFilter(page.slots.get(index).getElement().getFilter()));
			}
		}
	}

	private void addPlayerSlots() {
		for (int index : page.playerSlots.keySet()) {
			addSlotToContainer(new ContrivitivePlayerSlot(player.inventory, index, page.playerSlots.get(index).getX(), page.playerSlots.get(index).getY()).setFilter(page.playerSlots.get(index).getFilter()));
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index < page.slots.size()) {
				if (!this.mergeItemStack(itemstack1, page.slots.size(), this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, page.slots.size(), false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
		//		return provider.canInteractWith(playerIn);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (final IContainerListener listener : this.listeners) {

			int i = 0;
			if (!this.shortValues.isEmpty())
				for (final MutableTriple<IntSupplier, IntConsumer, Short> value : this.shortValues) {
					final short supplied = (short) value.getLeft().getAsInt();
					if (supplied != value.getRight()) {

						listener.sendWindowProperty(this, i, supplied);
						value.setRight(supplied);
					}
					i++;
				}

			if (!this.integerValues.isEmpty())
				for (final MutableTriple<IntSupplier, IntConsumer, Integer> value : this.integerValues) {
					final int supplied = value.getLeft().getAsInt();
					if (supplied != value.getRight()) {

						listener.sendWindowProperty(this, i, supplied >> 16);
						listener.sendWindowProperty(this, i + 1, (short) (supplied & 0xFFFF));
						value.setRight(supplied);
					}
					i += 2;
				}
		}
	}

	@Override
	public void addListener(final IContainerListener listener) {
		//if (!page.justPlayerInv) 
			super.addListener(listener);

		int i = 0;
		if (!this.shortValues.isEmpty())
			for (final MutableTriple<IntSupplier, IntConsumer, Short> value : this.shortValues) {
				final short supplied = (short) value.getLeft().getAsInt();

				listener.sendWindowProperty(this, i, supplied);
				value.setRight(supplied);
				i++;
			}

		if (!this.integerValues.isEmpty())
			for (final MutableTriple<IntSupplier, IntConsumer, Integer> value : this.integerValues) {
				final int supplied = value.getLeft().getAsInt();

				listener.sendWindowProperty(this, i, supplied >> 16);
				listener.sendWindowProperty(this, i + 1, (short) (supplied & 0xFFFF));
				value.setRight(supplied);
				i += 2;
			}

	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(final int id, final int value) {

		if (id < this.shortValues.size()) {
			this.shortValues.get(id).getMiddle().accept((short) value);
			this.shortValues.get(id).setRight((short) value);
		} else if (id - this.shortValues.size() < this.integerValues.size() * 2) {

			if ((id - this.shortValues.size()) % 2 == 0)
				this.integerParts[(id - this.shortValues.size()) / 2] = value;
			else {
				this.integerValues.get((id - this.shortValues.size()) / 2).getMiddle().accept(
					(this.integerParts[(id - this.shortValues.size()) / 2] & 0xFFFF) << 16 | value & 0xFFFF);
			}
		}
	}
}
