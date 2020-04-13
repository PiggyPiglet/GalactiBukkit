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

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class Launch {
    private static final int COUNTDOWN_SECONDS = 20;
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
