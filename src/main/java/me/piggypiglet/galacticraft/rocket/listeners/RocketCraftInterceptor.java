package me.piggypiglet.galacticraft.rocket.listeners;

import me.piggypiglet.galacticraft.rocket.utils.RocketItemUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class RocketCraftInterceptor implements Listener {
    @EventHandler
    public void onRocketCraft(@NotNull final CraftItemEvent event) {
        RocketItemUtils.populateRocket(event.getCurrentItem());
    }
}
