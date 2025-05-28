package xyz.glowstonelabs.contentcraft.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import xyz.glowstonelabs.contentcraft.ContentCraft;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ContentCraft.MOD_ID);

    public static void init(IEventBus eventBus) {
        eventBus.register(ITEMS);
    }
}
