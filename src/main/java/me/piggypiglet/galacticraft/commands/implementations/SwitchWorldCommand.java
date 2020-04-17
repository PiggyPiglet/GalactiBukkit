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

package me.piggypiglet.galacticraft.commands.implementations;

import me.piggypiglet.galacticraft.commands.framework.Command;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

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
