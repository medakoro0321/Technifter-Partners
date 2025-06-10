package io.github.medakoro0321.items;

import io.github.medakoro0321.TechnifterPartners;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TestModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TechnifterPartners.MODID);

    public static final DeferredItem<Item> EXAMPLE_ITEM;

    static {
        EXAMPLE_ITEM = ITEMS.registerItem(
                "super_item",
                Item::new, // The factory that the properties will be passed into.
                new Item.Properties() // The properties to use.
        );
    }
}
