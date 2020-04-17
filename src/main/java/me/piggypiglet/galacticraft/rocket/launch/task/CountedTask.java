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
