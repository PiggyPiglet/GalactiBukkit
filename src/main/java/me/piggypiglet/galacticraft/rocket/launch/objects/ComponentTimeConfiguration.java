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

package me.piggypiglet.galacticraft.rocket.launch.objects;

import me.piggypiglet.galacticraft.rocket.launch.framework.LaunchComponent;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

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
