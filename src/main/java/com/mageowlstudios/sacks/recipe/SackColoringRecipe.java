package com.mageowlstudios.sacks.recipe;

import com.mageowlstudios.sacks.block.SackBlock;
import net.minecraft.block.Block;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class SackColoringRecipe extends SpecialCraftingRecipe {
   public SackColoringRecipe(Identifier id, CraftingRecipeCategory category) {
      super(id, category);
   }

   @Override
   public boolean matches(RecipeInputInventory inventory, World world) {
      int i = 0;
      int j = 0;

      for(int k = 0; k < inventory.size(); ++k) {
         ItemStack itemStack = inventory.getStack(k);
         if (!itemStack.isEmpty()) {
            if (Block.getBlockFromItem(itemStack.getItem()) instanceof SackBlock) {
               ++i;
            } else {
               if (!(itemStack.getItem() instanceof DyeItem)) {
                  return false;
               }

               ++j;
            }

            if (j > 1 || i > 1) {
               return false;
            }
         }
      }

      return i == 1 && j == 1;
   }

   @Override
   public ItemStack craft(RecipeInputInventory inventory, DynamicRegistryManager registryManager) {
      ItemStack itemStack = ItemStack.EMPTY;
      DyeItem dyeItem = (DyeItem) Items.WHITE_DYE;

      for(int i = 0; i < inventory.size(); ++i) {
         ItemStack itemStack2 = inventory.getStack(i);
         if (!itemStack2.isEmpty()) {
            Item item = itemStack2.getItem();
            if (Block.getBlockFromItem(item) instanceof SackBlock) {
               itemStack = itemStack2;
            } else if (item instanceof DyeItem) {
               dyeItem = (DyeItem)item;
            }
         }
      }

      ItemStack itemStack3 = SackBlock.getItemStack(dyeItem.getColor());
      if (itemStack.hasNbt()) {
         assert itemStack.getNbt() != null;
         itemStack3.setNbt(itemStack.getNbt().copy());
      }

      return itemStack3;
   }

   public boolean fits(int width, int height) {
      return width * height >= 2;
   }

   public RecipeSerializer<?> getSerializer() {
      return ModRecipeSerializers.SACK_COLORING;
   }
}
