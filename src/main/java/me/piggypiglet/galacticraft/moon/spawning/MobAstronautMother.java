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

package me.piggypiglet.galacticraft.moon.spawning;

import me.piggypiglet.galacticraft.moon.MoonConstants;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.bukkit.entity.EntityType.*;

public final class MobAstronautMother implements Listener {
    private static final Set<EntityType> DISABLED_MOBS = Stream.of(WITCH, SPIDER, CREEPER, BAT, PHANTOM, ZOMBIE_VILLAGER)
            .collect(Collectors.toSet());
    private static final Set<EntityType> SPACE_MOBS = Stream.of(SKELETON, ZOMBIE)
            .collect(Collectors.toSet());
    private static final ItemStack HELMET = new ItemStack(Material.GLASS);

    @EventHandler
    public void onEntitySpawn(@NotNull final EntitySpawnEvent event) {
        final Entity entity = event.getEntity();

        if (!event.getLocation().getWorld().getName().equals(MoonConstants.NAME)) return;
        if (DISABLED_MOBS.contains(entity.getType())) {
            event.setCancelled(true);
            return;
        }
        if (SPACE_MOBS.contains(entity.getType())) {
            ((LivingEntity) entity).getEquipment().setHelmet(HELMET);
        }
    }
}
