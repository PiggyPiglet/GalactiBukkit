package me.piggypiglet.galacticraft.rocket;

import me.piggypiglet.galacticraft.boot.GalacticraftBootstrap;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class RocketConstants {
    private RocketConstants() {
        throw new AssertionError("This class cannot be initialized.");
    }

    private static final JavaPlugin MAIN = JavaPlugin.getProvidingPlugin(GalacticraftBootstrap.class);

    public static final ItemStack ROCKET = new ItemStack(Material.SAND);
    public static final NamespacedKey ROCKET_KEY = new NamespacedKey(MAIN, "rocket");
    public static final NamespacedKey ROCKET_ID = new NamespacedKey(MAIN, "rocket_id");

    static {
        final ItemMeta meta = ROCKET.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Rocket");
        meta.setCustomModelData(69420);
        meta.getPersistentDataContainer().set(ROCKET_KEY, PersistentDataType.STRING, "");
        ROCKET.setItemMeta(meta);
    }

    public static final Material LAUNCH_PAD = Material.PURPUR_SLAB;
}
