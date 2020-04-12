package me.piggypiglet.galacticraft.rocket.console;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class ConsoleGUI implements InventoryHolder {
    private static final ItemStack[] ITEMS = new ItemStack[27];

    static {
        final ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);

        for (int i = 0; i < 27; ++i) {
            ITEMS[i] = filler;
        }

        ITEMS[10] = create(Material.FIRE_CHARGE, "Launch", ChatColor.RED);
        ITEMS[16] = create(Material.BUCKET, "Fuel", ChatColor.YELLOW);
    }

    private static ItemStack create(@NotNull final Material material, @NotNull final String name,
                                    @NotNull final ChatColor color) {
        final ItemStack item = new ItemStack(material);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(color + name);
        item.setItemMeta(meta);

        return item;
    }

    private final Inventory inventory;

    private ConsoleGUI() {
        inventory = Bukkit.createInventory(this, 27, ChatColor.DARK_GRAY + "Rocket Console");
    }

    public static ConsoleGUI create() {
        final ConsoleGUI gui = new ConsoleGUI();
        gui.inventory.setContents(ITEMS);
        return gui;
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
