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

package me.piggypiglet.galacticraft.commands;

import me.piggypiglet.galacticraft.commands.exceptions.NoDefaultCommandException;
import me.piggypiglet.galacticraft.commands.framework.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

public final class CommandHandler implements CommandExecutor {
    private final Set<Command> commands;
    private final Command defaultCommand;

    @Inject
    public CommandHandler(@NotNull final Set<Command> commands) {
        this.commands = commands;
        defaultCommand = commands.stream()
                .filter(Command::isDefault)
                .findAny().orElseThrow(() -> new NoDefaultCommandException("No default command is present in the plugin."));
    }

    @Override
    public boolean onCommand(@NotNull final CommandSender sender, @NotNull final org.bukkit.command.Command bukkitCommand,
                             @NotNull final String label, @NotNull String[] args) {
        if (args.length == 0) {
            defaultCommand.execute(sender, args);
            return true;
        }

        final Optional<Command> optionalCommand = commands.stream()
                .filter(cmd -> cmd.getPrefix().equalsIgnoreCase(args[0]))
                .findAny();

        if (!optionalCommand.isPresent()) {
            sender.sendMessage("Unknown command.");
            return true;
        }

        final Command command = optionalCommand.get();

        if (command.isPlayerOnly() && !(sender instanceof Player)) {
            sender.sendMessage("This command can only be ran by a player.");
            return true;
        }

        if (!command.getPermissions().isEmpty() && command.getPermissions().stream().noneMatch(sender::hasPermission)) {
            sender.sendMessage("You do not have permission to use this command.");
            return true;
        }

        final boolean result = command.execute(sender, Arrays.copyOfRange(args, 1, args.length));

        if (!result) {
            sender.sendMessage("Incorrect usage, correct usage is /galacticraft " + args[0] + " " + command.getUsage());
        }

        return true;
    }
}
