package me.piggypiglet.galacticraft.rocket.launch.lift;

import me.piggypiglet.galacticraft.rocket.launch.lift.framework.LiftComponent;
import org.bukkit.entity.ArmorStand;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class SoundComponent implements LiftComponent {
    @Override
    public void run(final @NotNull ArmorStand rocket, final int run) {
        play(rocket);
    }

    @Override
    public void complete(final @NotNull ArmorStand rocket) {
        run(rocket, 0);
    }

    private void play(@NotNull final ArmorStand rocket) {
//        RocketUtils.getPlayer(rocket).get().playSound();
    }
}
