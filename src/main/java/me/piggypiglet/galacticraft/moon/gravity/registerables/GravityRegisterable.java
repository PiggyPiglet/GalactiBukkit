package me.piggypiglet.galacticraft.moon.gravity.registerables;

import me.piggypiglet.galacticraft.boot.registerables.Registerable;
import me.piggypiglet.galacticraft.moon.gravity.task.GravityUpdateTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class GravityRegisterable extends Registerable {
    @Inject private JavaPlugin main;
    @Inject private GravityUpdateTask gravityUpdateTask;

    @Override
    protected void execute() {
        Bukkit.getScheduler().runTaskLater(main, gravityUpdateTask, 1L);
    }
}
