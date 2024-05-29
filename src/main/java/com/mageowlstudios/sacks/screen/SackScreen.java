package com.mageowlstudios.sacks.screen;

import com.mageowlstudios.sacks.SacksMod;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class SackScreen extends HandledScreen<SackScreenHandler> {
   private static final Identifier TEXTURE = new Identifier(SacksMod.MOD_ID, "textures/gui/sack.png");

   public SackScreen(SackScreenHandler handler, PlayerInventory inventory, Text title) {
      super(handler, inventory, title);
   }

   @Override
   protected void init() {
      super.init();
      this.titleX = (this.backgroundWidth - this.textRenderer.getWidth(this.title)) / 2;
   }

   @Override
   protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
      int i = (this.width - this.backgroundWidth) / 2;
      int j = (this.height - this.backgroundHeight) / 2;
      context.drawTexture(TEXTURE, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);
   }

   @Override
   public void render(DrawContext context, int mouseX, int mouseY, float delta) {
      this.renderBackground(context);
      super.render(context, mouseX, mouseY, delta);
      drawMouseoverTooltip(context, mouseX, mouseY);
   }
}
