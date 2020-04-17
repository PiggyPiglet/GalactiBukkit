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

package me.piggypiglet.galacticraft.rocket.utils;

import me.piggypiglet.galacticraft.rocket.RocketConstants;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public final class RocketItemUtils {
    private RocketItemUtils() {
        throw new AssertionError("This class cannot be initialized.");
    }

    public static void populateRocket(@NotNull final ItemStack itemStack) {
        populateRocket(itemStack, System.currentTimeMillis());
    }

    public static void populateRocket(@NotNull final ItemStack itemStack, final long id) {
        final ItemMeta meta = itemStack.getItemMeta();
        final PersistentDataContainer data = meta.getPersistentDataContainer();

        if (!data.has(RocketConstants.ROCKET_KEY, PersistentDataType.STRING)) return;

        data.set(RocketConstants.ROCKET_ID, PersistentDataType.LONG, id);
        itemStack.setItemMeta(meta);
    }
}
