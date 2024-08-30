package com.mageowlstudios.sacks.recipe;

import com.mageowlstudios.sacks.SacksMod;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipeSerializers {
   public static final SpecialRecipeSerializer<SackColoringRecipe> SACK_COLORING = register("crafting_special_sackcoloring", new SpecialRecipeSerializer<>(SackColoringRecipe::new));

   @SuppressWarnings("SameParameterValue")
   static <S extends RecipeSerializer<T>, T extends Recipe<?>> S register(String id, S serializer) {
      return Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(SacksMod.MOD_ID, id), serializer);
   }

   public static void registerRecipeSerializers() {
      SacksMod.LOGGER.debug("Registered recipe serializers.");
   }
}
