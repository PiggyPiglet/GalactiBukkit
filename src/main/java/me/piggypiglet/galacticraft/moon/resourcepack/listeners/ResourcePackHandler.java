package me.piggypiglet.galacticraft.moon.resourcepack.listeners;

import me.piggypiglet.galacticraft.annotations.scanning.Hidden;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

import static me.piggypiglet.galacticraft.moon.MoonConstants.MOON_RP;
import static me.piggypiglet.galacticraft.moon.MoonConstants.MOON_RP_HASH;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
@Hidden
public final class ResourcePackHandler implements Listener {
    @EventHandler
    public void onMoonJoin(@NotNull final PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        player.setResourcePack(MOON_RP, MOON_RP_HASH);
    }
}
