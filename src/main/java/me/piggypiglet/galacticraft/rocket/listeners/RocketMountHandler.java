package me.piggypiglet.galacticraft.rocket.listeners;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class RocketMountHandler implements Listener {
    @EventHandler
    public void onRocketMount(@NotNull final PlayerArmorStandManipulateEvent event) {
        final ArmorStand armorStand = event.getRightClicked();

        if (!armorStand.hasMetadata("rocket")) return;
        event.setCancelled(true);

        final Location location = armorStand.getLocation();
        final World world = location.getWorld();

        final Entity playerMount = world.spawn(location, ArmorStand.class, mount -> {
            mount.setVisible(false);
            mount.setSmall(true);
        });

        playerMount.addPassenger(event.getPlayer());
        armorStand.addPassenger(playerMount);
    }
}
