package com.mageowlstudios.sacks.block;

import com.mageowlstudios.sacks.SacksMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

   public static final Block SACK = registerBlock("sack", new SackBlock(FabricBlockSettings.create().strength(0.1f).sounds(BlockSoundGroup.WOOL).nonOpaque()));
   public static final Item SACK_ITEM = registerBlockItem("sack", SACK, new FabricItemSettings().maxCount(1));

   private static Block registerBlock(String name, Block block) {
      return Registry.register(Registries.BLOCK, new Identifier("sacks", name), block);
   }

   private static Item registerBlockItem(String name, Block block, FabricItemSettings settings) {
      return Registry.register(Registries.ITEM, new Identifier("sacks", name), new BlockItem(block, settings));
   }

   public static void registerModBlocks() {
      SacksMod.LOGGER.debug("Registered blocks.");

      ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
         entries.add(SACK_ITEM);
      });
   }
}
