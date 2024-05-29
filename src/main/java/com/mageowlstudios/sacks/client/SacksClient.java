package com.mageowlstudios.sacks.client;

import com.mageowlstudios.sacks.screen.ModScreens;
import com.mageowlstudios.sacks.screen.SackScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class SacksClient implements ClientModInitializer {
   @Override
   public void onInitializeClient() {
      HandledScreens.register(ModScreens.SACK_SCREEN_HANDLER, SackScreen::new);
   }
}
