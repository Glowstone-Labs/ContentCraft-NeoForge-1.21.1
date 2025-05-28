/*
 * Glowstone Labs - ContentCraft - LootTableProvider
 * 2025 Glowstone Labs
 * changelog:
 *  - initial implementation - CodeRandomMc
 */
package xyz.glowstonelabs.contentcraft.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;
import xyz.glowstonelabs.contentcraft.block.ModBlocks;

import java.util.Set;

/**
 * Provider class for generating loot tables for ContentCraft mod blocks.
 * Extends Minecraft's BlockLootSubProvider to handle custom block drops.
 */
public class ModBlockLootTableProvider extends BlockLootSubProvider {
    /** Set of items that are resistant to explosions when dropped */
    private static final Set<Item> EXPLOSION_RESISTANT = Set.of();

    /**
     * Constructs a new ModBlockLootTableProvider.
     *
     * @param registries The registry provider for looking up game objects
     */
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags(), registries);
    }

    /**
     * Generates loot tables for all ContentCraft mod blocks.
     * Called during data generation to create block loot table JSON files.
     */
    @Override
    protected void generate() {

    }

    /**
     * Gets all registered blocks from the ContentCraft mod.
     *
     * @return An Iterable of all known mod blocks
     */
    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }

    /**
     * Creates a loot table builder for ore blocks that drop multiple items.
     * Includes fortune enchantment bonus and supports silk touch.
     *
     * @param block The block to create drops for
     * @param drop The item to be dropped
     * @param min Minimum number of items to drop
     * @param max Maximum number of items to drop
     * @return A LootTable.Builder configured with the specified drops
     */
    protected LootTable.Builder createMultiOreDrops(Block block, Item drop, int min, int max ) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(block, this.applyExplosionDecay(block, LootItem.lootTableItem(drop)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }
}
