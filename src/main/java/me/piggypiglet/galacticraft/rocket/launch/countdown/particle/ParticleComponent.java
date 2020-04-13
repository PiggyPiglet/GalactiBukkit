package me.piggypiglet.galacticraft.rocket.launch.countdown.particle;

import me.piggypiglet.galacticraft.rocket.launch.countdown.framework.CountdownComponent;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public abstract class ParticleComponent implements CountdownComponent {
    @Override
    public double getRunsPerSecond() {
        return 5;
    }

    protected void spawnParticle(@NotNull final ArmorStand rocket, @NotNull final Particle particle,
                                 final int count) {
        rocket.getWorld().spawnParticle(particle, rocket.getLocation(), count,
                0.5, 0, 0.5, 0.02);
    }
}
