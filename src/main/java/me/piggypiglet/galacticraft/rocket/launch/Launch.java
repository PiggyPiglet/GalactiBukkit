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

package me.piggypiglet.galacticraft.rocket.launch;

import me.piggypiglet.galacticraft.rocket.launch.countdown.framework.CountdownComponent;
import me.piggypiglet.galacticraft.rocket.launch.framework.LaunchComponent;
import me.piggypiglet.galacticraft.rocket.launch.lift.framework.LiftComponent;
import me.piggypiglet.galacticraft.rocket.launch.objects.ComponentTimeConfiguration;
import me.piggypiglet.galacticraft.rocket.launch.task.CountedTask;
import me.piggypiglet.galacticraft.rocket.utils.RocketUtils;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class Launch {
    private static final int COUNTDOWN_SECONDS = 10;
    private static final int LIFT_SECONDS = 10;

    private final Set<LaunchComponent> countdownComponents;
    private final Set<LaunchComponent> liftComponents;

    @Inject private JavaPlugin main;

    @Inject
    public Launch(@NotNull final Set<LaunchComponent> components) {
        countdownComponents = filter(components, component -> component instanceof CountdownComponent);
        liftComponents = filter(components, component -> component instanceof LiftComponent);
    }

    public boolean launch(@NotNull final Player player) {
        final ArmorStand rocket = RocketUtils.getRocket(player).orElse(null);

        if (rocket == null) return false;

        final CountedTask countdown = new CountedTask(initializeComponentTimes(COUNTDOWN_SECONDS, countdownComponents),
                rocket, main, COUNTDOWN_SECONDS);
        countdown.run();

        countdown.getCompletableFuture()
                .whenComplete((v, t) -> new CountedTask(initializeComponentTimes(LIFT_SECONDS, liftComponents),
                        rocket, main, LIFT_SECONDS).run());

        return true;
    }

    @NotNull
    private Map<LaunchComponent, ComponentTimeConfiguration> initializeComponentTimes(final int seconds,
                                                                                      @NotNull final Set<LaunchComponent> components) {
        return new ConcurrentHashMap<>(components.stream()
                .collect(Collectors.toMap(component -> component, component -> new ComponentTimeConfiguration(seconds, component))));
    }

    @NotNull
    private static Set<LaunchComponent> filter(@NotNull final Set<LaunchComponent> components, @NotNull final Predicate<LaunchComponent> filter) {
        return components.stream()
                .filter(filter)
                .collect(Collectors.toSet());
    }
}
