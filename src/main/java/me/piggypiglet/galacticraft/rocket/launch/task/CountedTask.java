package me.piggypiglet.galacticraft.rocket.launch.task;

import me.piggypiglet.galacticraft.rocket.launch.framework.LaunchComponent;
import me.piggypiglet.galacticraft.rocket.launch.objects.ComponentTimeConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class CountedTask implements Runnable {
    private static final BukkitScheduler SCHEDULER = Bukkit.getScheduler();

    private final ArmorStand rocket;
    private final JavaPlugin main;
    private final Map<LaunchComponent, ComponentTimeConfiguration> components;
    private final CompletableFuture<Void> isComplete = new CompletableFuture<>();
    private final int seconds;

    public CountedTask(@NotNull final Map<LaunchComponent, ComponentTimeConfiguration> components, @NotNull final ArmorStand rocket,
                       @NotNull final JavaPlugin main, final int seconds) {
        this.components = components;
        this.rocket = rocket;
        this.main = main;
        this.seconds = seconds;
    }

    @Override
    public void run() {
        startCompletionScanning();

        for (final Map.Entry<LaunchComponent, ComponentTimeConfiguration> entry : components.entrySet()) {
            run(entry.getKey(), entry.getValue());
        }
    }

    @NotNull
    public CompletableFuture<Void> getCompletableFuture() {
        return isComplete;
    }

    private void startCompletionScanning() {
        SCHEDULER.runTaskAsynchronously(main, () -> {
            final Collection<ComponentTimeConfiguration> times = components.values();

            while (true) {
                if (times.stream().map(ComponentTimeConfiguration::getCompletableFuture).allMatch(Future::isDone)) {
                    isComplete.complete(null);
                    break;
                }
            }
        });
    }

    private void run(@NotNull final LaunchComponent component, @NotNull final ComponentTimeConfiguration time) {
        component.run(rocket, time.getCurrent());
        time.decrementCurrent();

        if (time.getCurrent() > (time.getTotalRuns() / seconds) - 2) {
            SCHEDULER.runTaskLater(main, () -> run(component, time), (int) (seconds / time.getRunsPerSecond()));
        } else {
            component.complete(rocket);
            time.getCompletableFuture().complete(null);
        }
    }
}
