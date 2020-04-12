package me.piggypiglet.galacticraft.rocket.utils;

import me.piggypiglet.galacticraft.rocket.RocketConstants;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class RocketItemUtils {
    private RocketItemUtils() {
        throw new AssertionError("This class cannot be initialized.");
    }

    public static void populateRocket(@NotNull final ItemStack itemStack) {
        populateRocket(itemStack, System.currentTimeMillis());
    }

    public static void populateRocket(@NotNull final ItemStack itemStack, final long id) {
        final ItemMeta meta = itemStack.getItemMeta();
        final PersistentDataContainer data = meta.getPersistentDataContainer();

        if (!data.has(RocketConstants.ROCKET_KEY, PersistentDataType.STRING)) return;

        data.set(RocketConstants.ROCKET_ID, PersistentDataType.LONG, id);
        itemStack.setItemMeta(meta);
    }
}
