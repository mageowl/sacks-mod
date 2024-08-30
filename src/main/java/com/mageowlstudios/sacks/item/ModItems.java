package com.mageowlstudios.sacks.item;

import com.mageowlstudios.sacks.block.ModBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final SackItem SACK = registerItem("sack", new SackItem(ModBlocks.SACK, new FabricItemSettings().maxCount(1)));
    public static final SackItem WHITE_SACK = registerItem("white_sack", new SackItem(ModBlocks.WHITE_SACK, new FabricItemSettings().maxCount(1)));
    public static final SackItem ORANGE_SACK = registerItem("orange_sack", new SackItem(ModBlocks.ORANGE_SACK, new FabricItemSettings().maxCount(1)));
    public static final SackItem MAGENTA_SACK = registerItem("magenta_sack", new SackItem(ModBlocks.MAGENTA_SACK, new FabricItemSettings().maxCount(1)));
    public static final SackItem LIGHT_BLUE_SACK = registerItem("light_blue_sack", new SackItem(ModBlocks.LIGHT_BLUE_SACK, new FabricItemSettings().maxCount(1)));
    public static final SackItem YELLOW_SACK = registerItem("yellow_sack", new SackItem(ModBlocks.YELLOW_SACK, new FabricItemSettings().maxCount(1)));
    public static final SackItem LIME_SACK = registerItem("lime_sack", new SackItem(ModBlocks.LIME_SACK, new FabricItemSettings().maxCount(1)));
    public static final SackItem PINK_SACK = registerItem("pink_sack", new SackItem(ModBlocks.PINK_SACK, new FabricItemSettings().maxCount(1)));
    public static final SackItem GRAY_SACK = registerItem("gray_sack", new SackItem(ModBlocks.GRAY_SACK, new FabricItemSettings().maxCount(1)));
    public static final SackItem LIGHT_GRAY_SACK = registerItem("light_gray_sack", new SackItem(ModBlocks.LIGHT_GRAY_SACK, new FabricItemSettings().maxCount(1)));
    public static final SackItem CYAN_SACK = registerItem("cyan_sack", new SackItem(ModBlocks.CYAN_SACK, new FabricItemSettings().maxCount(1)));
    public static final SackItem PURPLE_SACK = registerItem("purple_sack", new SackItem(ModBlocks.PURPLE_SACK, new FabricItemSettings().maxCount(1)));
    public static final SackItem BLUE_SACK = registerItem("blue_sack", new SackItem(ModBlocks.BLUE_SACK, new FabricItemSettings().maxCount(1)));
    public static final SackItem BROWN_SACK = registerItem("brown_sack", new SackItem(ModBlocks.BROWN_SACK, new FabricItemSettings().maxCount(1)));
    public static final SackItem GREEN_SACK = registerItem("green_sack", new SackItem(ModBlocks.GREEN_SACK, new FabricItemSettings().maxCount(1)));
    public static final SackItem RED_SACK = registerItem("red_sack", new SackItem(ModBlocks.RED_SACK, new FabricItemSettings().maxCount(1)));
    public static final SackItem BLACK_SACK = registerItem("black_sack", new SackItem(ModBlocks.BLACK_SACK, new FabricItemSettings().maxCount(1)));

    private static <T extends Item> T registerItem(String name, T item) {
        return Registry.register(Registries.ITEM, new Identifier("sacks", name), item);
    }

    private static void addSacks(FabricItemGroupEntries entries) {
        entries.add(SACK);
        entries.add(WHITE_SACK);
        entries.add(ORANGE_SACK);
        entries.add(MAGENTA_SACK);
        entries.add(LIGHT_BLUE_SACK);
        entries.add(YELLOW_SACK);
        entries.add(LIME_SACK);
        entries.add(PINK_SACK);
        entries.add(GRAY_SACK);
        entries.add(LIGHT_GRAY_SACK);
        entries.add(CYAN_SACK);
        entries.add(PURPLE_SACK);
        entries.add(BLUE_SACK);
        entries.add(BROWN_SACK);
        entries.add(GREEN_SACK);
        entries.add(RED_SACK);
        entries.add(BLACK_SACK);
    }

    public static void registerItems() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(ModItems::addSacks);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS).register(ModItems::addSacks);
    }
}
