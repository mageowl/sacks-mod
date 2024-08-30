package com.mageowlstudios.sacks.block;

import com.mageowlstudios.sacks.SacksMod;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

public class ModBlocks {
   public static final Block SACK = registerBlock("sack", new SackBlock(FabricBlockSettings.create().strength(0.1f).sounds(BlockSoundGroup.WOOL).nonOpaque(), null));
   public static final Block WHITE_SACK = registerBlock("white_sack", new SackBlock(FabricBlockSettings.create().strength(0.1f).sounds(BlockSoundGroup.WOOL).nonOpaque(), DyeColor.WHITE));
   public static final Block ORANGE_SACK = registerBlock("orange_sack", new SackBlock(FabricBlockSettings.create().strength(0.1f).sounds(BlockSoundGroup.WOOL).nonOpaque(), DyeColor.ORANGE));
   public static final Block MAGENTA_SACK = registerBlock("magenta_sack", new SackBlock(FabricBlockSettings.create().strength(0.1f).sounds(BlockSoundGroup.WOOL).nonOpaque(), DyeColor.MAGENTA));
   public static final Block LIGHT_BLUE_SACK = registerBlock("light_blue_sack", new SackBlock(FabricBlockSettings.create().strength(0.1f).sounds(BlockSoundGroup.WOOL).nonOpaque(), DyeColor.LIGHT_BLUE));
   public static final Block YELLOW_SACK = registerBlock("yellow_sack", new SackBlock(FabricBlockSettings.create().strength(0.1f).sounds(BlockSoundGroup.WOOL).nonOpaque(), DyeColor.YELLOW));
   public static final Block LIME_SACK = registerBlock("lime_sack", new SackBlock(FabricBlockSettings.create().strength(0.1f).sounds(BlockSoundGroup.WOOL).nonOpaque(), DyeColor.LIME));
   public static final Block PINK_SACK = registerBlock("pink_sack", new SackBlock(FabricBlockSettings.create().strength(0.1f).sounds(BlockSoundGroup.WOOL).nonOpaque(), DyeColor.PINK));
   public static final Block GRAY_SACK = registerBlock("gray_sack", new SackBlock(FabricBlockSettings.create().strength(0.1f).sounds(BlockSoundGroup.WOOL).nonOpaque(), DyeColor.GRAY));
   public static final Block LIGHT_GRAY_SACK = registerBlock("light_gray_sack", new SackBlock(FabricBlockSettings.create().strength(0.1f).sounds(BlockSoundGroup.WOOL).nonOpaque(), DyeColor.LIGHT_GRAY));
   public static final Block CYAN_SACK = registerBlock("cyan_sack", new SackBlock(FabricBlockSettings.create().strength(0.1f).sounds(BlockSoundGroup.WOOL).nonOpaque(), DyeColor.CYAN));
   public static final Block PURPLE_SACK = registerBlock("purple_sack", new SackBlock(FabricBlockSettings.create().strength(0.1f).sounds(BlockSoundGroup.WOOL).nonOpaque(), DyeColor.PURPLE));
   public static final Block BLUE_SACK = registerBlock("blue_sack", new SackBlock(FabricBlockSettings.create().strength(0.1f).sounds(BlockSoundGroup.WOOL).nonOpaque(), DyeColor.BLUE));
   public static final Block BROWN_SACK = registerBlock("brown_sack", new SackBlock(FabricBlockSettings.create().strength(0.1f).sounds(BlockSoundGroup.WOOL).nonOpaque(), DyeColor.BROWN));
   public static final Block GREEN_SACK = registerBlock("green_sack", new SackBlock(FabricBlockSettings.create().strength(0.1f).sounds(BlockSoundGroup.WOOL).nonOpaque(), DyeColor.GREEN));
   public static final Block RED_SACK = registerBlock("red_sack", new SackBlock(FabricBlockSettings.create().strength(0.1f).sounds(BlockSoundGroup.WOOL).nonOpaque(), DyeColor.RED));
   public static final Block BLACK_SACK = registerBlock("black_sack", new SackBlock(FabricBlockSettings.create().strength(0.1f).sounds(BlockSoundGroup.WOOL).nonOpaque(), DyeColor.BLACK));


   private static Block registerBlock(String name, Block block) {
      return Registry.register(Registries.BLOCK, new Identifier("sacks", name), block);
   }

   public static void registerBlocks() {
      SacksMod.LOGGER.debug("Registered blocks.");
   }
}
