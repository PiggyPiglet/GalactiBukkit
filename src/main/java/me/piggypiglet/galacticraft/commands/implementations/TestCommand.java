package me.piggypiglet.galacticraft.commands.implementations;

import me.piggypiglet.galacticraft.commands.framework.Command;
import me.piggypiglet.galacticraft.rocket.launch.Launch;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class TestCommand extends Command {
    @Inject private JavaPlugin main;
    @Inject private Launch launch;

    public TestCommand() {
        super("test");
    }

    @Override
    public boolean execute(final @NotNull CommandSender sender, @NotNull final String[] args) {
        final Player player = (Player) sender;

        player.getWorld().playSound(player.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1.5f, 0.1f);
        player.getWorld().playSound(player.getLocation(), Sound.ITEM_TOTEM_USE, 0.3f, 0.1f);

//        launch.launch(player);

        return true;
    }
}
