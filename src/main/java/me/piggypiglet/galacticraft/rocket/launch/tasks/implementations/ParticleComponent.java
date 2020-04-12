package me.piggypiglet.galacticraft.rocket.launch.tasks.implementations;

import me.piggypiglet.galacticraft.rocket.launch.tasks.framework.CountdownComponent;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class ParticleComponent implements CountdownComponent {
    @Override
    public void run(final @NotNull Player player, final int run) {
        player.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, player.getLocation().subtract(0, 1.5, 0), 100,
                1, 0, 1, 0);
    }

    @Override
    public void complete(final @NotNull Player player) {
        player.getWorld().spawnParticle(Particle.FLAME, player.getLocation().subtract(0, 1.5, 0), 300,
                1, 0, 1, 0);
    }
}
