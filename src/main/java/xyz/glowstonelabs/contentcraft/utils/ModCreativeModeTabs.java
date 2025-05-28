package xyz.glowstonelabs.contentcraft.utils;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import xyz.glowstonelabs.contentcraft.ContentCraft;

public class ModCreativeModeTabs {

    /**
     * Registry for creative mode tabs
     */
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ContentCraft.MOD_ID);



    /** Creative tab for mod blocks */
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BLOCKS_TAB = CREATIVE_MODE_TABS.register(
            "block_creative_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.contentCraft.blocks"))
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(Items.DIAMOND_SWORD::getDefaultInstance)
                    .displayItems((parameters, output) -> output.accept(Items.DIAMOND_SWORD)).build()
    );

}
