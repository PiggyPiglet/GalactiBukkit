package me.piggypiglet.galacticraft.commands.implementations;

import me.piggypiglet.galacticraft.commands.framework.Command;
import me.piggypiglet.galacticraft.rocket.launch.tasks.CountdownTask;
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

    public TestCommand() {
        super("test");
    }

    @Override
    public boolean execute(final @NotNull CommandSender sender, @NotNull final String[] args) {
        final Player player = (Player) sender;

        new CountdownTask(player, main).run();

        return true;
    }
}
