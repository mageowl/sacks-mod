package com.mageowlstudios.sacks.screen;

import com.mageowlstudios.sacks.block.entity.SackBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class SackScreenHandler
        extends ScreenHandler {
   private static final int CONTAINER_SIZE = 9;
   private static final int INVENTORY_START = 9;
   private static final int INVENTORY_END = 36;
   private static final int HOTBAR_START = 36;
   private static final int HOTBAR_END = 45;
   private final Inventory inventory;

   public SackScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf) {
      this(syncId, inventory, (SackBlockEntity) inventory.player.getWorld().getBlockEntity(buf.readBlockPos()));
   }

   public SackScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
      super(ModScreens.SACK_SCREEN_HANDLER, syncId);
      int j;
      int i;
      net.minecraft.screen.Generic3x3ContainerScreenHandler.checkSize(inventory, 9);
      this.inventory = inventory;
      inventory.onOpen(playerInventory.player);
      for (i = 0; i < 3; ++i) {
         for (j = 0; j < 3; ++j) {
            this.addSlot(new SackSlot(inventory, j + i * 3, 62 + j * 18, 17 + i * 18));
         }
      }
      for (i = 0; i < 3; ++i) {
         for (j = 0; j < 9; ++j) {
            this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
         }
      }
      for (i = 0; i < 9; ++i) {
         this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
      }
   }

   @Override
   public boolean canUse(PlayerEntity player) {
      return this.inventory.canPlayerUse(player);
   }

   @Override
   public ItemStack quickMove(PlayerEntity player, int slot) {
      ItemStack itemStack = ItemStack.EMPTY;
      Slot slot2 = (Slot) this.slots.get(slot);
      if (slot2.hasStack()) {
         ItemStack itemStack2 = slot2.getStack();
         itemStack = itemStack2.copy();
         if (slot < 9 ? !this.insertItem(itemStack2, 9, 45, true) : !this.insertItem(itemStack2, 0, 9, false)) {
            return ItemStack.EMPTY;
         }
         if (itemStack2.isEmpty()) {
            slot2.setStack(ItemStack.EMPTY);
         } else {
            slot2.markDirty();
         }
         if (itemStack2.getCount() == itemStack.getCount()) {
            return ItemStack.EMPTY;
         }
         slot2.onTakeItem(player, itemStack2);
      }
      return itemStack;
   }

   @Override
   public void onClosed(PlayerEntity player) {
      super.onClosed(player);
      this.inventory.onClose(player);
   }
}
