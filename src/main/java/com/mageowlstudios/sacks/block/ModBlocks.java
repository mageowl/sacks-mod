package com.mageowlstudios.sacks.block;

import com.mageowlstudios.sacks.SacksMod;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

   public static final Block SACK = registerBlock("sack", new SackBlock(FabricBlockSettings.create().strength(0.1f).sounds(BlockSoundGroup.WOOL).nonOpaque()));

   private static Block registerBlock(String name, Block block) {
      return Registry.register(Registries.BLOCK, new Identifier("sacks", name), block);
   }

   public static void registerBlocks() {
      SacksMod.LOGGER.debug("Registered blocks.");
   }
}
