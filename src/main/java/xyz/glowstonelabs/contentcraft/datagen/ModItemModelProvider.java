/*
 * Glowstone Labs - ContentCraft - ItemModelProvider
 * 2025 Glowstone Labs
 * changelog:
 *  - initial implementation - CodeRandomMc
 */
package xyz.glowstonelabs.contentcraft.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;
import xyz.glowstonelabs.contentcraft.ContentCraft;

/**
 * Provides item model generation functionality for ContentCraft mod items.
 * This class handles the automatic generation of JSON model files for items
 * during the data generation phase of mod building.
 */
public class ModItemModelProvider extends ItemModelProvider {
    /**
     * Creates a new ModItemModelProvider instance.
     *
     * @param output The PackOutput instance for writing generated files
     * @param existingFileHelper Helper utility for accessing existing files
     */
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ContentCraft.MOD_ID, existingFileHelper);
    }

    /**
     * Registers all item models for the mod.
     * This method is called during data generation to create model files
     * for each item that needs a model.
     */
    @Override
    protected void registerModels() {

    }

    /**
     * Registers a basic item model for the given deferred item.
     *
     * @param deferredItem The DeferredItem to generate a basic item model for
     */
    protected void item(DeferredItem<Item> deferredItem) {
        basicItem(deferredItem.get());
    }
}
