package me.piggypiglet.galacticraft.rocket.launch.countdown.particle;

import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class SmokeComponent extends ParticleComponent {
    @Override
    public void run(final @NotNull ArmorStand rocket, final int run) {
        spawnParticle(rocket, Particle.CLOUD, 200);
    }

    @Override
    public void complete(@NotNull final ArmorStand rocket) {
        run(rocket, 0);
    }
}
