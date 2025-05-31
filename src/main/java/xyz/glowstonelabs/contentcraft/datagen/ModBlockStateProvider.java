/*
 * Glowstone Labs - ContentCraft - BlockStateProvider
 * 2025 Glowstone Labs
 * changelog:
 *  - initial implementation - CodeRandomMc
 */
package xyz.glowstonelabs.contentcraft.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import xyz.glowstonelabs.contentcraft.ContentCraft;
import xyz.glowstonelabs.contentcraft.block.ModBlocks;

/**
 * Provider class for generating block states and models for ContentCraft mod.
 * Handles the automatic generation of block states, models and item models
 * for registered blocks in the mod.
 */
public class ModBlockStateProvider extends BlockStateProvider {
    /**
     * Constructor for ModBlockStateProvider.
     *
     * @param output       The PackOutput instance for generating data
     * @param exFileHelper Helper for accessing existing files in data/resource packs
     */
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ContentCraft.MOD_ID, exFileHelper);
    }

    /**
     * Main method for registering block states and models.
     * Called during data generation to create block states and models for mod blocks.
     */
    @Override
    protected void registerStatesAndModels() {
        // Register block states and models here
        blockWithItem(ModBlocks.XAENON_ORE);
        blockWithItem(ModBlocks.XAENON_BLOCK);
        blockWithItem(ModBlocks.RAW_XAENON_BLOCK);
    }

    /**
     * Helper method to generate both block and item models for a block.
     *
     * @param deferredBlock The block to generate models for.
     */
    protected void blockWithItem(DeferredBlock<Block> deferredBlock) {
        // Generate a simple block model and corresponding item model using the cube all texture
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
