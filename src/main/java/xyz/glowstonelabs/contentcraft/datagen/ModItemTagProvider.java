/*
 * Glowstone Labs - ContentCraft - ItemTagsProvider
 * 2025 Glowstone Labs
 * changelog:
 *  - initial implementation - CodeRandomMc
 */
package xyz.glowstonelabs.contentcraft.datagen;

/**
 * Provider for generating item tags for ContentCraft mod.
 * Handles the data generation of item tags which are used for item categorization and recipe requirements.
 */

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.glowstonelabs.contentcraft.ContentCraft;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {

    /**
     * Constructs a new ModItemTagProvider.
     *
     * @param output             The pack output directory
     * @param lookupProvider     Provider for registry lookups
     * @param blockTags          Future containing block tags
     * @param existingFileHelper Helper for checking existing files
     */
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, ContentCraft.MOD_ID, existingFileHelper);
    }

    /**
     * Adds item tags to the data generation.
     * Called during data generation to define item tags for the mod.
     *
     * @param provider The registry lookup provider
     */
    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {

    }
}
