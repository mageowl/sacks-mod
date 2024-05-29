package com.mageowlstudios.sacks.block.entity;

import com.mageowlstudios.sacks.screen.SackScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

import java.util.stream.IntStream;

public class SackBlockEntity extends LootableContainerBlockEntity implements SidedInventory, ExtendedScreenHandlerFactory {

   private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(9, ItemStack.EMPTY);
   private static final int[] AVAILABLE_SLOTS = IntStream.range(0, 9).toArray();

   public SackBlockEntity(BlockPos blockPos, BlockState blockState) {
      super(ModBlockEntities.SACK, blockPos, blockState);
   }

   @Override
   protected DefaultedList<ItemStack> getInvStackList() {
      return inventory;
   }

   @Override
   protected void setInvStackList(DefaultedList<ItemStack> list) {
      inventory = list;
   }

   @Override
   protected Text getContainerName() {
      return Text.translatable("container.sacks.sack");
   }

   @Override
   protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
      return new SackScreenHandler(syncId, playerInventory, this);
   }

   @Override
   public int size() {
      return inventory.size();
   }

   @Override
   public void readNbt(NbtCompound nbt) {
      super.readNbt(nbt);
      this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
      if (!this.deserializeLootTable(nbt)) {
         Inventories.readNbt(nbt, this.inventory);
      }
   }

   @Override
   protected void writeNbt(NbtCompound nbt) {
      super.writeNbt(nbt);
      if (!this.serializeLootTable(nbt)) {
         Inventories.writeNbt(nbt, this.inventory);
      }

   }

   @Override
   public void onOpen(PlayerEntity player) {
      player.getWorld().playSound(null, pos, SoundEvents.ITEM_BUNDLE_INSERT, SoundCategory.BLOCKS, 1f, 1f);
   }

   @Override
   public void onClose(PlayerEntity player) {
      player.getWorld().playSound(null, pos, SoundEvents.ITEM_BUNDLE_REMOVE_ONE, SoundCategory.BLOCKS, 1f, 1f);
   }

   @Override
   public int[] getAvailableSlots(Direction side) {
      return AVAILABLE_SLOTS;
   }

   @Override
   public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
      return stack.getItem().canBeNested();
   }

   @Override
   public boolean canExtract(int slot, ItemStack stack, Direction dir) {
      return true;
   }

   @Override
   public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
      buf.writeBlockPos(pos);
   }
}
