package me.piggypiglet.galacticraft.rocket.console.listeners;

import me.piggypiglet.galacticraft.rocket.console.ConsoleGUI;
import me.piggypiglet.galacticraft.rocket.launch.Launch;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class ConsoleInteractionHandler implements Listener {
    @Inject private Launch launch;

    @EventHandler
    public void onConsoleInteraction(@NotNull final InventoryClickEvent event) {
        if (event.getClickedInventory() == null || !(event.getClickedInventory().getHolder() instanceof ConsoleGUI)) return;
        event.setCancelled(true);

        final ItemStack clicked = event.getCurrentItem();

        if (clicked == null) return;

        final Player clicker = (Player) event.getWhoClicked();

        if (clicked.getType() == Material.FIRE_CHARGE) {
            launch.launch(clicker);
            clicker.closeInventory();
        }
    }
}
