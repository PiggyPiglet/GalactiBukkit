package me.piggypiglet.galacticraft.rocket.launch.tasks.implementations;

import com.destroystokyo.paper.Title;
import me.piggypiglet.galacticraft.rocket.launch.tasks.framework.CountdownComponent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class TitleComponent implements CountdownComponent {
    @Override
    public void run(@NotNull final Player player, final int run) {
        player.sendTitle(Title.builder().title(run + " Seconds till launch").fadeIn(0).build());
    }

    @Override
    public void complete(@NotNull final Player player) {
        player.sendTitle(Title.builder().title("LAUNCH OFF!").fadeIn(0).build());
    }
}
