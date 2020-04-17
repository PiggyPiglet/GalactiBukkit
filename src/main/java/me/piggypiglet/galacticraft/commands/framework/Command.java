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

package me.piggypiglet.galacticraft.commands.framework;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class Command {
    private final String prefix;

    private boolean playerOnly = false;
    private String description = "null";
    private String usage = "<required args> [optional args]";
    private List<String> permissions = Collections.emptyList();
    private boolean def = false;

    protected final Options options = new Options();

    protected Command(@NotNull final String prefix) {
        this.prefix = prefix;
    }

    public abstract boolean execute(@NotNull final CommandSender sender, @NotNull final String[] args);

    @NotNull
    public String getPrefix() {
        return prefix;
    }

    public boolean isPlayerOnly() {
        return playerOnly;
    }

    @NotNull
    public String getDescription() {
        return description;
    }

    @NotNull
    public String getUsage() {
        return usage;
    }

    @NotNull
    public List<String> getPermissions() {
        return permissions;
    }

    public boolean isDefault() {
        return def;
    }

    protected final class Options {
        @NotNull
        public Options playerOnly(final boolean value) {
            playerOnly = value;
            return this;
        }

        @NotNull
        public Options description(@NotNull final String value) {
            description = value;
            return this;
        }

        @NotNull
        public Options usage(@NotNull final String value) {
            usage = value;
            return this;
        }

        @NotNull
        public Options permissions(@NotNull final String... values) {
            permissions = Arrays.asList(values);
            return this;
        }

        @NotNull
        public Options def(final boolean value) {
            def = value;
            return this;
        }
    }
}
