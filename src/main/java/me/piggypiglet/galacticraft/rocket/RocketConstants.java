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

package me.piggypiglet.galacticraft.rocket;

import me.piggypiglet.galacticraft.boot.GalacticraftBootstrap;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public final class RocketConstants {
    private RocketConstants() {
        throw new AssertionError("This class cannot be initialized.");
    }

    private static final JavaPlugin MAIN = JavaPlugin.getProvidingPlugin(GalacticraftBootstrap.class);

    public static final ItemStack ROCKET = new ItemStack(Material.SAND);
    public static final NamespacedKey ROCKET_KEY = new NamespacedKey(MAIN, "rocket");
    public static final NamespacedKey ROCKET_ID = new NamespacedKey(MAIN, "rocket_id");

    static {
        final ItemMeta meta = ROCKET.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Rocket");
        meta.setCustomModelData(69420);
        meta.getPersistentDataContainer().set(ROCKET_KEY, PersistentDataType.STRING, "");
        ROCKET.setItemMeta(meta);
    }

    public static final Material LAUNCH_PAD = Material.PURPUR_SLAB;
}
