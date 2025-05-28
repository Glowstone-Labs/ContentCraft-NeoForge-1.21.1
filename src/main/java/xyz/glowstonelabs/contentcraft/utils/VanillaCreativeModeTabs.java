package xyz.glowstonelabs.contentcraft.utils;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

public class VanillaCreativeModeTabs {
    public static void registerCreativeModeTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            // Add blocks to the Building Blocks tab
//            event.accept(Items.ACACIA_BOAT);
        }
        // Add more tab registrations as needed
    }
}