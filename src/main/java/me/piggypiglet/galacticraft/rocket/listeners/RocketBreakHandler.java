package me.piggypiglet.galacticraft.rocket.listeners;

import me.piggypiglet.galacticraft.rocket.RocketConstants;
import me.piggypiglet.galacticraft.rocket.utils.RocketItemUtils;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class RocketBreakHandler implements Listener {
    @EventHandler
    public void onRocketBreak(@NotNull final EntityDamageByEntityEvent event) {
        final Entity entity = event.getEntity();
        if (!entity.hasMetadata("rocket")) return;
        event.setCancelled(true);
        entity.getPassengers().stream()
                .filter(passenger -> !(passenger instanceof Player))
                .forEach(Entity::remove);
        entity.remove();

        final Location location = entity.getLocation();
        location.getWorld().spawn(location, Item.class, item -> {
            final ItemStack rocket = RocketConstants.ROCKET.clone();
            RocketItemUtils.populateRocket(rocket);
            item.setItemStack(rocket);
        });
    }
}
