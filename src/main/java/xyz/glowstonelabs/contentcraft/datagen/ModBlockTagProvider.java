/*
 * Glowstone Labs - ContentCraft - BlockTagProvider
 * 2025 Glowstone Labs
 * changelog:
 *  - initial implementation - CodeRandomMc
 */
package xyz.glowstonelabs.contentcraft.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.glowstonelabs.contentcraft.ContentCraft;

import java.util.concurrent.CompletableFuture;

/**
 * Provides block tag data generation functionality for the ContentCraft mod.
 * This class is responsible for defining which blocks belong to which tags,
 * particularly focusing on tool requirements and mining tiers.
 */
public class ModBlockTagProvider extends BlockTagsProvider {
    /**
     * Constructs a new ModBlockTagProvider.
     *
     * @param output The PackOutput instance for data generation
     * @param lookupProvider Provider for registry lookups
     * @param modId The mod ID (automatically set to ContentCraft.MOD_ID)
     * @param existingFileHelper Helper for checking existing files
     */
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ContentCraft.MOD_ID, existingFileHelper);
    }

    /**
     * Adds block tags for the ContentCraft mod.
     * This method defines various mining requirements for blocks including:
     * - Tool type requirements (pickaxe, axe, hoe, shovel)
     * - Tool tier requirements (stone, iron, diamond)
     *
     * @param provider The registry lookup provider
     */
    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        // Blocks that require a pickaxe to mine
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE);
        // Blocks that require a shovel to mine
        this.tag(BlockTags.MINEABLE_WITH_AXE);
        // Blocks that require a hoe to mine
        this.tag(BlockTags.MINEABLE_WITH_HOE);
        // Blocks that require a shovel to mine
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL);
        //Blocks that need tool tier stone+
        this.tag(BlockTags.NEEDS_STONE_TOOL);
        //Blocks that need tool tier iron+
        this.tag(BlockTags.NEEDS_IRON_TOOL);
        //Blocks that need tool tier diamond+
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL);
    }
}
