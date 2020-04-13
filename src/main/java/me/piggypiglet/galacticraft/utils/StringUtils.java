package me.piggypiglet.galacticraft.utils;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class StringUtils {
    private StringUtils() {
        throw new AssertionError("This class cannot be initialized.");
    }

    public static String color(@NotNull final String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
