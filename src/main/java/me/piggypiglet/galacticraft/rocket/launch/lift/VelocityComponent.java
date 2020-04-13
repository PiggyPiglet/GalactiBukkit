package me.piggypiglet.galacticraft.rocket.launch.lift;

import me.piggypiglet.galacticraft.rocket.launch.lift.framework.LiftComponent;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class VelocityComponent implements LiftComponent {
    @Override
    public double getRunsPerSecond() {
        return 1;
    }

    @Override
    public void run(final @NotNull ArmorStand rocket, final int run) {
        rocket.setVelocity(new Vector(0, 3, 0));
    }

    @Override
    public void complete(final @NotNull ArmorStand rocket) {
        run(rocket, 0);
    }
}
