package me.piggypiglet.galacticraft.rocket.launch.lift;

import me.piggypiglet.galacticraft.rocket.launch.lift.framework.LiftComponent;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class FireComponent implements LiftComponent {
    @Override
    public double getRunsPerSecond() {
        return 10;
    }

    @Override
    public void run(final @NotNull ArmorStand rocket, final int run) {
        rocket.getWorld().spawnParticle(Particle.LAVA, rocket.getLocation().subtract(0, 2, 0), 50,
                0.5, 0, 0.5, 0.02);
    }

    @Override
    public void complete(final @NotNull ArmorStand rocket) {
        run(rocket, 0);
    }
}
