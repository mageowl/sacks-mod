package com.mageowlstudios.sacks.screen;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class SackSlot
        extends Slot {
   public SackSlot(Inventory inventory, int i, int j, int k) {
      super(inventory, i, j, k);
   }

   @Override
   public boolean canInsert(ItemStack stack) {
      return stack.getItem().canBeNested();
   }
}
