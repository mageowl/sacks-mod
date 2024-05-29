package com.mageowlstudios.sacks;

import com.mageowlstudios.sacks.block.ModBlocks;
import com.mageowlstudios.sacks.block.entity.ModBlockEntities;
import com.mageowlstudios.sacks.item.ModItems;
import com.mageowlstudios.sacks.screen.ModScreens;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SacksMod implements ModInitializer {
   public static final String MOD_ID = "sacks";
   public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

   @Override
   public void onInitialize() {
      ModBlocks.registerBlocks();
      ModItems.registerItems();
      ModBlockEntities.registerBlockEntities();
      ModScreens.registerScreens();
   }
}
