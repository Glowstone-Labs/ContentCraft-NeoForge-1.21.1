package xyz.glowstonelabs.contentcraft.utils;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import xyz.glowstonelabs.contentcraft.ContentCraft;
import xyz.glowstonelabs.contentcraft.block.ModBlocks;

public class ModCreativeModeTabs {

    /**
     * Registry for creative mode tabs
     */
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ContentCraft.MOD_ID);



    /** Creative tab for mod blocks */
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BLOCKS_TAB = CREATIVE_MODE_TABS.register(
            "block_creative_tab",
            () -> {
                return CreativeModeTab.builder()
                        .title(Component.translatable("itemGroup.gl_content_craft.blocks"))
                        .withTabsBefore(CreativeModeTabs.COMBAT)
                        .icon(() -> new ItemStack(ModBlocks.XAENON_BLOCK.get().asItem()))
                        .displayItems((parameters, output) -> {
                            output.accept(ModBlocks.XAENON_ORE);
                            output.accept(ModBlocks.RAW_XAENON_BLOCK);
                            output.accept(ModBlocks.XAENON_BLOCK);}
                        ).build();
            }
    );

}
