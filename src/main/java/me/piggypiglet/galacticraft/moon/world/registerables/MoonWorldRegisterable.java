package me.piggypiglet.galacticraft.moon.world.registerables;

import me.piggypiglet.galacticraft.boot.registerables.Registerable;
import me.piggypiglet.galacticraft.moon.world.generation.MoonChunkGenerator;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import javax.inject.Inject;
import java.util.Objects;
import java.util.Optional;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class MoonWorldRegisterable extends Registerable {
    @Inject private MoonChunkGenerator generator;

    @Override
    protected void execute() {
        final World world = Optional.ofNullable(Bukkit.getWorld("moon"))
            .orElse(Objects.requireNonNull(new WorldCreator("moon")
                    .generator(generator)
                    .createWorld()));

        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        world.setTime(18000);
    }
}
