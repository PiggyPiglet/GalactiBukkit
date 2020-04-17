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
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.util.stream.Stream;

import static org.bukkit.block.BlockFace.*;

public final class RocketPlaceHandler implements Listener {
    @Inject private JavaPlugin main;

    @EventHandler
    public void onRocketPlace(@NotNull final BlockPlaceEvent event) {
        final ItemStack item = event.getItemInHand();
        final PersistentDataContainer data = item.getItemMeta().getPersistentDataContainer();

        if (!data.has(RocketConstants.ROCKET_KEY, PersistentDataType.STRING))
            return;

        event.setCancelled(true);

        final Block against = event.getBlockAgainst();

        if (against.getType() != RocketConstants.LAUNCH_PAD || Stream.of(NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST)
                .map(against::getRelative)
                .map(Block::getType)
                .anyMatch(type -> type != RocketConstants.LAUNCH_PAD)) {
            event.getPlayer().sendMessage("The launch platform is incomplete, or non-existent.");
            return;
        }

        final Location location = event.getBlockPlaced().getLocation().add(0.5, 0, 0.1);

        location.getWorld().spawn(location, ArmorStand.class, armorStand -> {
            armorStand.setMetadata("rocket", new FixedMetadataValue(main, data.get(RocketConstants.ROCKET_ID, PersistentDataType.LONG)));
            armorStand.setVisible(false);
            armorStand.setItem(EquipmentSlot.HEAD, RocketConstants.ROCKET);
        });

        final PlayerInventory inventory = event.getPlayer().getInventory();
        inventory.clear(inventory.getHeldItemSlot());
    }
}
