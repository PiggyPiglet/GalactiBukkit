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

package me.piggypiglet.galacticraft.rocket.console;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public final class ConsoleGUI implements InventoryHolder {
    private static final ItemStack[] ITEMS = new ItemStack[9];

    static {
        final ItemStack black = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);

        for (int i = 0; i <= 8; i = i + 4) {
            ITEMS[i] = black;
        }

        final ItemStack blue = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);

        for (int i = 1; i <= 7; i = i + 2) {
            ITEMS[i] = blue;
        }

        ITEMS[2] = create(Material.FIRE_CHARGE, "Launch", ChatColor.RED);
        ITEMS[6] = create(Material.BUCKET, "Fuel", ChatColor.YELLOW);
    }

    private static ItemStack create(@NotNull final Material material, @NotNull final String name,
                                    @NotNull final ChatColor color) {
        final ItemStack item = new ItemStack(material);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(color + name);
        item.setItemMeta(meta);

        return item;
    }

    private final Inventory inventory;

    private ConsoleGUI() {
        inventory = Bukkit.createInventory(this, 9, ChatColor.DARK_GRAY + "Rocket Console");
    }

    public static ConsoleGUI create() {
        final ConsoleGUI gui = new ConsoleGUI();
        gui.inventory.setContents(ITEMS);
        return gui;
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
