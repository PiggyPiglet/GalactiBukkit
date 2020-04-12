package me.piggypiglet.galacticraft.rocket.console.listeners;

import me.piggypiglet.galacticraft.rocket.console.ConsoleGUI;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class ConsoleInteractionHandler implements Listener {
    @EventHandler
    public void onConsoleInteraction(@NotNull final InventoryClickEvent event) {
        if (event.getClickedInventory().getHolder() != null && !(event.getClickedInventory().getHolder() instanceof ConsoleGUI)) return;
        event.setCancelled(true);

        final ItemStack clicked = event.getCurrentItem();
        final ArmorStand rocket = (ArmorStand) event.getWhoClicked().getVehicle().getVehicle();

        if (clicked.getType() == Material.FIRE_CHARGE) {

        }
    }
}
