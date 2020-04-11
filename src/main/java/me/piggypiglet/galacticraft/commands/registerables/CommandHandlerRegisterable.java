package me.piggypiglet.galacticraft.commands.registerables;

import me.piggypiglet.galacticraft.boot.registerables.Registerable;
import me.piggypiglet.galacticraft.commands.CommandHandler;
import me.piggypiglet.galacticraft.commands.exceptions.NoRegisteredCommandException;
import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;
import java.util.Optional;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class CommandHandlerRegisterable extends Registerable {
    @Inject private JavaPlugin main;
    @Inject private CommandHandler commandHandler;

    @Override
    protected void execute() {
        Optional.ofNullable(main.getCommand("galacticraft"))
                .orElseThrow(() -> new NoRegisteredCommandException("The galacticraft command hasn't been registered in the plugin YAML"))
                .setExecutor(commandHandler);
    }
}
