package me.piggypiglet.galacticraft.rocket.launch.tasks;

import me.piggypiglet.galacticraft.rocket.launch.tasks.framework.CountdownComponent;
import me.piggypiglet.galacticraft.rocket.launch.tasks.implementations.ParticleComponent;
import me.piggypiglet.galacticraft.rocket.launch.tasks.implementations.TitleComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class CountdownTask implements Runnable {
    private static final BukkitScheduler SCHEDULER = Bukkit.getScheduler();
    private static final Set<CountdownComponent> COUNTDOWN_COMPONENTS = Stream.of(new ParticleComponent(), new TitleComponent())
            .collect(Collectors.toSet());

    private final Player player;
    private final JavaPlugin main;
    private final CompletableFuture<Boolean> completed = new CompletableFuture<>();

    private int runs = 20;

    public CountdownTask(@NotNull final Player player, @NotNull final JavaPlugin main) {
        this.player = player;
        this.main = main;
    }

    @Override
    public void run() {
        forEach(component -> component.run(player, runs));
        --runs;

        if (runs != -1) {
            SCHEDULER.runTaskLaterAsynchronously(main, this, 20);
        } else {
            forEach(component -> component.complete(player));
            completed.complete(true);
        }
    }

    @NotNull
    public CompletableFuture<Boolean> completedFuture() {
        return completed;
    }

    private static void forEach(@NotNull final Consumer<CountdownComponent> function) {
        for (final CountdownComponent component : COUNTDOWN_COMPONENTS) {
            function.accept(component);
        }
    }
}
