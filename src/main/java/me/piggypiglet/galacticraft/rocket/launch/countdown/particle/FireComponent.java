package me.piggypiglet.galacticraft.rocket.launch.countdown.particle;

import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class FireComponent extends ParticleComponent {
    @Override
    public void run(@NotNull final ArmorStand rocket, final int run) {
        spawnParticle(rocket, Particle.FLAME, 20);
    }

    @Override
    public void complete(final @NotNull ArmorStand rocket) {
        spawnParticle(rocket, Particle.FLASH, 5);
        spawnParticle(rocket, Particle.EXPLOSION_LARGE, 1);
        spawnParticle(rocket, Particle.LAVA, 200);
    }
}
