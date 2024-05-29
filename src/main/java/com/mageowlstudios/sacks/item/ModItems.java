package com.mageowlstudios.sacks.item;

import com.mageowlstudios.sacks.block.ModBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final SackItem SACK = registerItem("sack", new SackItem(ModBlocks.SACK, new FabricItemSettings().maxCount(1)));

    private static <T extends Item> T registerItem(String name, T item) {
        return Registry.register(Registries.ITEM, new Identifier("sacks", name), item);
    }

    public static void registerItems() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.add(SACK);
        });
    }
}
