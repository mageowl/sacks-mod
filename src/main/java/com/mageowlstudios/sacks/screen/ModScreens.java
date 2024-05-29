package com.mageowlstudios.sacks.screen;

import com.mageowlstudios.sacks.SacksMod;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreens {
   public static final ScreenHandlerType<SackScreenHandler> SACK_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER, new Identifier(SacksMod.MOD_ID, "sack"), new ExtendedScreenHandlerType<SackScreenHandler>(SackScreenHandler::new));

   public static void registerScreens() {
      SacksMod.LOGGER.debug("Registered screens.");
   }
}
