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

package me.piggypiglet.galacticraft.rocket.listeners;

import me.piggypiglet.galacticraft.rocket.RocketConstants;
import me.piggypiglet.galacticraft.rocket.utils.RocketItemUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.stream.Stream;

public final class RocketCraftInterceptor implements Listener {
    @EventHandler
    public void onRocketCraftAttempt(@NotNull final PrepareItemCraftEvent event) {
        if (notRocketRecipe(event.getRecipe())) return;

        final CraftingInventory table = event.getInventory();
        final ItemStack[] matrix = table.getMatrix();

        if (Stream.of(1, 3, 5, 6, 8).anyMatch(i -> matrix[i].getAmount() != 64)) {
            table.setResult(null);
        }
    }

    @EventHandler
    public void onRocketCraft(@NotNull final CraftItemEvent event) {
        if (notRocketRecipe(event.getRecipe())) return;

        final CraftingInventory table = event.getInventory();
        table.setMatrix(new ItemStack[9]);
        event.setCurrentItem(RocketConstants.ROCKET.clone());

        RocketItemUtils.populateRocket(Objects.requireNonNull(event.getCurrentItem()));
    }

    private static boolean notRocketRecipe(@Nullable final Recipe recipe) {
        return recipe == null || !recipe.getResult().equals(RocketConstants.ROCKET);
    }
}
