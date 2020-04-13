package me.piggypiglet.galacticraft.rocket.launch.framework;

import org.bukkit.entity.ArmorStand;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public interface LaunchComponent {
    default double getRunsPerSecond() {
        return 1;
    }

    void run(@NotNull final ArmorStand rocket, final int run);

    void complete(@NotNull final ArmorStand rocket);
}
