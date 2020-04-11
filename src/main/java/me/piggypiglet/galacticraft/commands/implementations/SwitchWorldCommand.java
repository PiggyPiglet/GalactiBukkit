package me.piggypiglet.galacticraft.commands.implementations;

import me.piggypiglet.galacticraft.commands.framework.Command;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class SwitchWorldCommand extends Command {
    public SwitchWorldCommand() {
        super("switch-world");
        options
                .playerOnly(true)
                .usage("<world>");
    }

    @Override
    public boolean execute(final @NotNull CommandSender sender, @NotNull final String[] args) {
        if (args.length == 0) {
            return false;
        }

        ((Player) sender).teleport(new Location(Bukkit.getWorld(args[0]), 0, 100, 0));

        return true;
    }
}
