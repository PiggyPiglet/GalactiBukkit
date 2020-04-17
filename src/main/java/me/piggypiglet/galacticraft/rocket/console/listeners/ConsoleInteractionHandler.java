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

package me.piggypiglet.galacticraft.rocket.console.listeners;

import me.piggypiglet.galacticraft.rocket.console.ConsoleGUI;
import me.piggypiglet.galacticraft.rocket.launch.Launch;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public final class ConsoleInteractionHandler implements Listener {
    @Inject private Launch launch;

    @EventHandler
    public void onConsoleInteraction(@NotNull final InventoryClickEvent event) {
        if (event.getClickedInventory() == null || !(event.getClickedInventory().getHolder() instanceof ConsoleGUI)) return;
        event.setCancelled(true);

        final ItemStack clicked = event.getCurrentItem();

        if (clicked == null) return;

        final Player clicker = (Player) event.getWhoClicked();

        if (clicked.getType() == Material.FIRE_CHARGE) {
            launch.launch(clicker);
            clicker.closeInventory();
        }
    }
}
