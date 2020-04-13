package me.piggypiglet.galacticraft.rocket.launch.countdown;

import me.piggypiglet.galacticraft.rocket.launch.countdown.framework.CountdownComponent;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class SoundComponent implements CountdownComponent {
    @Override
    public double getRunsPerSecond() {
        return 5;
    }

    @Override
    public void run(final @NotNull ArmorStand rocket, final int run) {
        play(rocket, Sound.ENTITY_WITHER_SHOOT);
        play(rocket, Sound.ENTITY_BLAZE_SHOOT);
    }

    @Override
    public void complete(final @NotNull ArmorStand rocket) {
        run(rocket, 0);
        play(rocket, Sound.ITEM_TOTEM_USE, 50);
        play(rocket, Sound.ITEM_FIRECHARGE_USE, 50);
    }

    private void play(@NotNull final ArmorStand rocket, @NotNull final Sound sound) {
        play(rocket, sound, 1);
    }

    private void play(@NotNull final ArmorStand rocket, @NotNull final Sound sound,
                      final int volume) {
        rocket.getWorld().playSound(rocket.getLocation(), sound, volume, 0.1f);
    }
}
