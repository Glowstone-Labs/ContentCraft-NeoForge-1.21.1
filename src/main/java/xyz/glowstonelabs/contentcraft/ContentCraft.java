/*
 * Glowstone Labs - ContentCraft - ContentCraft Mod (Entrypoint)
 * 2025 Glowstone Labs
 * changelog:
 *  - initial implementation - CodeRandomMc
 */
package xyz.glowstonelabs.contentcraft;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;
import xyz.glowstonelabs.contentcraft.block.ModBlocks;
import xyz.glowstonelabs.contentcraft.item.ModItems;
import xyz.glowstonelabs.contentcraft.utils.ModCreativeModeTabs;
import xyz.glowstonelabs.contentcraft.utils.VanillaCreativeModeTabs;


/**
 * Main mod class for ContentCraft - A Minecraft mod adding new content and features
 * This class handles mod initialization, registry setup, and event management
 */
@Mod(ContentCraft.MOD_ID)
public class ContentCraft
{
    /**
     * Mod identifier used for registry names and logging
     */
    public static final String MOD_ID = "gl_content_craft";

    /**
     * Central logger for the mod
     */
    private static final Logger LOGGER = LogUtils.getLogger();

    // Entry point for the mod. Called by NeoForge during mod loading.
    public ContentCraft(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);

        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModCreativeModeTabs.CREATIVE_MODE_TABS.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(VanillaCreativeModeTabs::registerCreativeModeTabs);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    /**
     * Handles common setup tasks for both client and server
     * @param event The common setup event
     */
    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("ContentCraft server starting...");
    }

    /**
     * Handles client side only events and initialization
     */
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("Initializing ContentCraft" +
                    " client for player: {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
