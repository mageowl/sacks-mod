package com.mageowlstudios.sacks.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class SackItem extends BlockItem {
    public SackItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public boolean canBeNested() {
        return false;
    }
}
