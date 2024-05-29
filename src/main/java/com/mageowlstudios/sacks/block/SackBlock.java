package com.mageowlstudios.sacks.block;

import com.mageowlstudios.sacks.block.entity.SackBlockEntity;
import com.mageowlstudios.sacks.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SackBlock extends BlockWithEntity implements BlockEntityProvider {
   private static final VoxelShape BASE_SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 12.0, 15.0);
   private static final VoxelShape NECK_SHAPE = VoxelShapes.union(Block.createCuboidShape(4.8, 11.8, 4.8, 11.2, 13.2, 11.2), Block.createCuboidShape(4.1, 13.1, 4.1, 11.9, 15.9, 11.9));
   private static final VoxelShape OUTLINE_SHAPE = VoxelShapes.union(BASE_SHAPE, NECK_SHAPE);
   private static final VoxelShape COLLISION_SHAPE = VoxelShapes.union(BASE_SHAPE, Block.createCuboidShape(4.1, 11.8, 4.1, 11.9, 15.9, 11.9));

   public SackBlock(Settings settings) {
      super(settings);
   }

   @SuppressWarnings("deprecation")
   @Override
   public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      return OUTLINE_SHAPE;
   }

   @SuppressWarnings("deprecation")
   @Override
   public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      return COLLISION_SHAPE;
   }

   @Override
   public BlockRenderType getRenderType(BlockState blockState) {
      return BlockRenderType.MODEL;
   }

   @Nullable
   @Override
   public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
      return new SackBlockEntity(pos, state);
   }

   @SuppressWarnings("deprecation")
   @Override
   public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
      if (state.isOf(newState.getBlock())) return;

      BlockEntity blockEntity = world.getBlockEntity(pos);
      if (blockEntity instanceof SackBlockEntity) {
         world.updateComparators(pos, this);
      }
      super.onStateReplaced(state, world, pos, newState, moved);
   }

   @Override
   public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
      BlockEntity blockEntity = world.getBlockEntity(pos);
      if (blockEntity instanceof SackBlockEntity sackBlockEntity) {
         if (!world.isClient && player.isCreative() && !sackBlockEntity.isEmpty()) {
            ItemStack itemStack = new ItemStack(ModItems.SACK);
            blockEntity.setStackNbt(itemStack);
            if (sackBlockEntity.hasCustomName()) {
               itemStack.setCustomName(sackBlockEntity.getCustomName());
            }
            ItemEntity itemEntity = new ItemEntity(world, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, itemStack);
            itemEntity.setToDefaultPickupDelay();
            world.spawnEntity(itemEntity);
         } else {
            sackBlockEntity.checkLootInteraction(player);
         }
      }
      super.onBreak(world, pos, state, player);
   }

   @SuppressWarnings("deprecation")
   @Override
   public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
      if (!world.isClient) {
         NamedScreenHandlerFactory screenHandlerFactory = ((SackBlockEntity) world.getBlockEntity(pos));

         if (screenHandlerFactory != null) {

            player.openHandledScreen(screenHandlerFactory);
         }
      }

      return ActionResult.SUCCESS;
   }

   @Override
   public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
      BlockEntity blockEntity;
      if (itemStack.hasCustomName() && (blockEntity = world.getBlockEntity(pos)) instanceof SackBlockEntity) {
         ((SackBlockEntity) blockEntity).setCustomName(itemStack.getName());
      }
   }

   @Override
   public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
      super.appendTooltip(stack, world, tooltip, options);
      NbtCompound nbtCompound = BlockItem.getBlockEntityNbt(stack);
      if (nbtCompound != null) {
         if (nbtCompound.contains("LootTable", NbtElement.STRING_TYPE)) {
            tooltip.add(Text.literal("???????"));
         }
         if (nbtCompound.contains("Items", NbtElement.LIST_TYPE)) {
            DefaultedList<ItemStack> defaultedList = DefaultedList.ofSize(27, ItemStack.EMPTY);
            Inventories.readNbt(nbtCompound, defaultedList);
            int i = 0;
            int j = 0;
            for (ItemStack itemStack : defaultedList) {
               if (itemStack.isEmpty()) continue;
               ++j;
               if (i > 4) continue;
               ++i;
               MutableText mutableText = itemStack.getName().copy();
               mutableText.append(" x").append(String.valueOf(itemStack.getCount()));
               tooltip.add(mutableText);
            }
            if (j - i > 0) {
               tooltip.add(Text.translatable("container.shulkerBox.more", j - i).formatted(Formatting.ITALIC));
            }
         }
      }
   }

   @Override
   public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
      ItemStack itemStack = super.getPickStack(world, pos, state);
      world.getBlockEntity(pos, BlockEntityType.SHULKER_BOX).ifPresent(blockEntity -> blockEntity.setStackNbt(itemStack));
      return itemStack;
   }

   @SuppressWarnings("deprecation")
   @Override
   public List<ItemStack> getDroppedStacks(BlockState state, LootContextParameterSet.Builder builder) {
      BlockEntity blockEntity = builder.getOptional(LootContextParameters.BLOCK_ENTITY);
      if (blockEntity instanceof SackBlockEntity sackBlockEntity) {
         builder = builder.addDynamicDrop(ShulkerBoxBlock.CONTENTS_DYNAMIC_DROP_ID, lootConsumer -> {
            for (int i = 0; i < sackBlockEntity.size(); ++i) {
               lootConsumer.accept(sackBlockEntity.getStack(i));
            }
         });
      }
      return super.getDroppedStacks(state, builder);
   }


}
