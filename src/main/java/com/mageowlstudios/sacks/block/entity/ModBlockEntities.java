package com.mageowlstudios.sacks.block.entity;

import com.mageowlstudios.sacks.SacksMod;
import com.mageowlstudios.sacks.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
   public static final BlockEntityType<SackBlockEntity> SACK = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(SacksMod.MOD_ID, "sack"), FabricBlockEntityTypeBuilder.create(SackBlockEntity::new, ModBlocks.SACK).build());

   public static void registerBlockEntities() {
      SacksMod.LOGGER.debug("Registered block entities.");
   }
}
