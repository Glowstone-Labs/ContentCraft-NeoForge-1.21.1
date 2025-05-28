/*
 * Glowstone Labs - ContentCraft - DataGenerator
 * 2025 Glowstone Labs
 * changelog:
 *  - initial implementation - CodeRandomMc
 */
package xyz.glowstonelabs.contentcraft.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import xyz.glowstonelabs.contentcraft.ContentCraft;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Main data generation class for the ContentCraft mod.
 * Handles registration of various data providers for recipes, loot tables, tags, and models.
 * This class is automatically subscribed to mod events via EventBusSubscriber.
 */
@EventBusSubscriber(modid = ContentCraft.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerator {
    /**
     * Main data generation method that registers all data providers.
     * Called automatically during data generation phase.
     *
     * @param event The GatherDataEvent containing necessary context for data generation
     */
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        net.minecraft.data.DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // Register recipe generation provider
        generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput, lookupProvider));

        // Register loot table generation provider for blocks
        generator.addProvider(event.includeServer(), new LootTableProvider(
                packOutput,
                Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider)
        );

        // Create block tags provider for use in item tags
        BlockTagsProvider blockTagsProvider = new ModBlockTagProvider(packOutput, lookupProvider, ContentCraft.MOD_ID, existingFileHelper);

        // Register block tags provider
        generator.addProvider(event.includeServer(), blockTagsProvider);

        // Register item tags provider (depends on block tags)
        generator.addProvider(event.includeServer(), new ModItemTagProvider(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper));

        // Register block states and models provider
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, existingFileHelper));

        // Register item models provider
        generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, existingFileHelper));
    }
}
