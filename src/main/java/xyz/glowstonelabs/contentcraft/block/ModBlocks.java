/*
 * Glowstone Labs - ContentCraft - ModBlocks (Register block instances)
 * 2025 Glowstone Labs
 * changelog:
 *  - initial implementation - CodeRandomMc
 */
package xyz.glowstonelabs.contentcraft.block;

import net.neoforged.neoforge.registries.DeferredRegister;
import xyz.glowstonelabs.contentcraft.ContentCraft;

/**
 * Registry handler for all blocks in the ContentCraft mod.
 * This class centralizes the registration of custom blocks and manages their lifecycle.
 */
public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ContentCraft.MOD_ID);
}
