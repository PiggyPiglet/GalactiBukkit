package me.piggypiglet.galacticraft.rocket.launch.objects;

import me.piggypiglet.galacticraft.rocket.launch.framework.LaunchComponent;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class ComponentTimeConfiguration {
    private final CompletableFuture<Void> complete = new CompletableFuture<>();
    private final double runsPerSecond;
    private final int totalRuns;
    private int current;

    public ComponentTimeConfiguration(final int seconds, @NotNull final LaunchComponent component) {
        runsPerSecond = component.getRunsPerSecond();
        totalRuns = (int) (runsPerSecond * seconds);
        current = totalRuns;
    }

    public double getRunsPerSecond() {
        return runsPerSecond;
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public int getCurrent() {
        return current;
    }

    public void decrementCurrent() {
        --current;
    }

    @NotNull
    public CompletableFuture<Void> getCompletableFuture() {
        return complete;
    }

    @Override
    public String toString() {
        return "CountdownTimeConfiguration{" +
                "runsPerSecond=" + runsPerSecond +
                ", totalRuns=" + totalRuns +
                ", current=" + current +
                '}';
    }
}
