/*
 * Glowstone Labs - ContentCraft - ContentCraft Mod (Entrypoint)
 * 2025 Glowstone Labs
 * changelog:
 *  - initial implementation - CodeRandomMc
 */
package xyz.glowstonelabs.contentcraft;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
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
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;
import xyz.glowstonelabs.contentcraft.block.ModBlocks;
import xyz.glowstonelabs.contentcraft.item.ModItems;


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

    /**
     * Registry for creative mode tabs
     */
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    /** Creative tab for mod blocks and items */
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BLOCKS_TAB = CREATIVE_MODE_TABS.register(
            "block_creative_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.contentCraft.blocks"))
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(Items.DIAMOND_SWORD::getDefaultInstance)
                    .displayItems((parameters, output) -> output.accept(Items.DIAMOND_SWORD)).build()
    );

    // Entry point for the mod. Called by NeoForge during mod loading.
    public ContentCraft(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        ModBlocks.init(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ModItems.init(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    /**
     * Handles common setup tasks for both client and server
     * @param event The common setup event
     */
    private void commonSetup(final FMLCommonSetupEvent event) {
        if (Config.logDirtBlock) {
            LOGGER.info("Registered blocks: {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
        }
    }

    // Add items to vanilla creative tabs
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        //Register to vanilla creative tabs example
//        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
//            event.accept(EXAMPLE_BLOCK_ITEM);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    /**
     * Handles client side only events and initialization
     */
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("Initializing client for player: {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
