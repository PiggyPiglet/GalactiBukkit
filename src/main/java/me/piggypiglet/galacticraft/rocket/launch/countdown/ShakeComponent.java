package me.piggypiglet.galacticraft.rocket.launch.countdown;

import me.piggypiglet.galacticraft.rocket.launch.countdown.framework.CountdownComponent;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class ShakeComponent implements CountdownComponent {
    @Override
    public double getRunsPerSecond() {
        return 20;
    }

    @Override
    public void run(final @NotNull ArmorStand rocket, final int run) {
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        rocket.setHeadPose(new EulerAngle(randomPose(random), randomPose(random), randomPose(random)));
    }

    @Override
    public void complete(final @NotNull ArmorStand rocket) {
        rocket.setHeadPose(EulerAngle.ZERO);
    }

    private static double randomPose(@NotNull final ThreadLocalRandom random) {
        return random.nextDouble(-0.002, 0.004);
    }
}
