/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020 PiggyPiglet
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of Galacticraft and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of Galacticraft.
 *
 * GALACTICRAFT IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.piggypiglet.galacticraft.rocket.launch.countdown;

import me.piggypiglet.galacticraft.rocket.launch.countdown.framework.CountdownComponent;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

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
