package me.piggypiglet.galacticraft.rocket.launch.tasks.framework;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public interface CountdownComponent {
    void run(@NotNull final Player player, final int run);

    void complete(@NotNull final Player player);
}
