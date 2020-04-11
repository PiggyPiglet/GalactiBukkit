package me.piggypiglet.galacticraft.commands.implementations;

import me.piggypiglet.galacticraft.commands.framework.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class HelpCommand extends Command {
    public HelpCommand() {
        super("help");
        options.def(true);
    }

    @Override
    public boolean execute(final @NotNull CommandSender sender, @NotNull final String[] args) {
        sender.sendMessage("soon");
        return true;
    }
}
