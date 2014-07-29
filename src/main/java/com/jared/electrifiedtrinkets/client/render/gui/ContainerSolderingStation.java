package com.jared.electrifiedtrinkets.client.render.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.inventory.ContainerEnchantment;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.jared.electrifiedtrinkets.client.render.gui.slot.SlotAddon;
import com.jared.electrifiedtrinkets.client.render.gui.slot.SlotCircuit;
import com.jared.electrifiedtrinkets.client.render.gui.slot.SlotCopperNugget;
import com.jared.electrifiedtrinkets.client.render.gui.slot.SlotLeadWire;
import com.jared.electrifiedtrinkets.client.render.gui.slot.SlotSolderingIron;
import com.jared.electrifiedtrinkets.tileEntity.TileEntitySolderingStation;

public class ContainerSolderingStation extends Container {

	public ContainerSolderingStation(InventoryPlayer invPlayer, TileEntitySolderingStation solderingStation) {

		for (int x = 0; x < 9; x++) {
			addSlotToContainer(new Slot(invPlayer, x, 36 + 18 * x, 195));

		}

		// for (int y = 0; y < 3; y++) {
		// for (int x = 0; x < 9; x++) {
		// addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9, 36 + 18 * x,
		// 137 + y * 20));
		// }
		// }
		//
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9, 36 + 18 * x, 137 + y * 18));
			}
		}

		addSlotToContainer(new SlotSolderingIron(solderingStation, 1, 14, 110));
		addSlotToContainer(new SlotCircuit(solderingStation, 2, 108, 110));
		addSlotToContainer(new SlotLeadWire(solderingStation, 3, 200, 110));

		addSlotToContainer(new SlotAddon(solderingStation, 4, 9, 42));
		addSlotToContainer(new SlotAddon(solderingStation, 5, 27, 42));
		addSlotToContainer(new SlotAddon(solderingStation, 6, 27, 24));
		addSlotToContainer(new SlotAddon(solderingStation, 7, 27, 6));

		addSlotToContainer(new SlotCopperNugget(solderingStation, 8, 151, 6));
		addSlotToContainer(new SlotCopperNugget(solderingStation, 9, 151, 24));
		addSlotToContainer(new SlotCopperNugget(solderingStation, 10, 151, 42));
		addSlotToContainer(new SlotCopperNugget(solderingStation, 11, 151, 60));

		addSlotToContainer(new SlotCopperNugget(solderingStation, 12, 169, 60));
		addSlotToContainer(new SlotCopperNugget(solderingStation, 13, 187, 60));
		addSlotToContainer(new SlotCopperNugget(solderingStation, 14, 205, 60));

	}

	@Override
	public boolean canInteractWith(EntityPlayer arg0) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		ItemStack stack = null;
		Slot slotObject = (Slot) inventorySlots.get(slot);

		// null checks and checks if the item can be stacked (maxStackSize > 1)
		if (slotObject != null && slotObject.getHasStack()) {
			ItemStack stackInSlot = slotObject.getStack();
			stack = stackInSlot.copy();

			// merges the item into player inventory since its in the tileEntity
			if (slot < 9) {
				if (!this.mergeItemStack(stackInSlot, 0, 35, true)) {
					return null;
				}
			}
			// places it into the tileEntity is possible since its in the player
			// inventory
			else if (!this.mergeItemStack(stackInSlot, 0, 9, false)) {
				return null;
			}

			if (stackInSlot.stackSize == 0) {
				slotObject.putStack(null);
			} else {
				slotObject.onSlotChanged();
			}

			if (stackInSlot.stackSize == stack.stackSize) {
				return null;
			}
			slotObject.onPickupFromSlot(player, stackInSlot);
		}
		return stack;
	}
	


}
