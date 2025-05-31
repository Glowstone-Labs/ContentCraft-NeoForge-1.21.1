/*
 * Glowstone Labs - ContentCraft - ModBlocks (Register block instances)
 * 2025 Glowstone Labs
 * changelog:
 *  - initial implementation - CodeRandomMc
 */
package xyz.glowstonelabs.contentcraft.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import xyz.glowstonelabs.contentcraft.ContentCraft;
import xyz.glowstonelabs.contentcraft.item.ModItems;

import java.util.function.Supplier;

/**
 * Registry handler for all blocks in the ContentCraft mod.
 * This class centralizes the registration of custom blocks and manages their lifecycle.
 */
public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ContentCraft.MOD_ID);

    public static final DeferredBlock<Block> XAENON_ORE = registerBlock("xaenon_ore",
            () -> new Block(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));

    public static final DeferredBlock<Block> RAW_XAENON_BLOCK = registerBlock("raw_xaenon_block",
            () -> new Block(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK)
            ));

    public static final DeferredBlock<Block> XAENON_BLOCK = registerBlock("xaenon_block",
            () -> new Block(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK)
            ));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> deferredBlock = BLOCKS.register(name, block);
        registerBlockWithItem(name, deferredBlock);
        return deferredBlock;
    }

    private static <T extends Block> void registerBlockWithItem(String name, DeferredBlock<T> deferredBlock) {
        ModItems.ITEMS.register(name, () -> new BlockItem(deferredBlock.get(), new Item.Properties()));
    }
}
