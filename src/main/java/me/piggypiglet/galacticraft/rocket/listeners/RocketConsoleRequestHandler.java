package me.piggypiglet.galacticraft.rocket.listeners;

import me.piggypiglet.galacticraft.rocket.console.ConsoleGUI;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class RocketConsoleRequestHandler implements Listener {
    private final Map<UUID, Integer> clicks = ExpiringMap.builder()
            .expiration(500, TimeUnit.MILLISECONDS)
            .expirationPolicy(ExpirationPolicy.CREATED)
            .build();

    @EventHandler
    public void onRocketConsoleRequest(@NotNull final PlayerInteractEvent event) {
        final Player player = event.getPlayer();

        if (event.getAction() != Action.LEFT_CLICK_AIR && event.getAction() != Action.LEFT_CLICK_BLOCK) return;
        if (!player.isInsideVehicle() || !player.getVehicle().isInsideVehicle() ||
                !player.getVehicle().getVehicle().hasMetadata("rocket")) return;

        if (!clicks.containsKey(player.getUniqueId())) {
            clicks.put(player.getUniqueId(), 1);
            return;
        }

        player.openInventory(ConsoleGUI.create().getInventory());
    }
}
