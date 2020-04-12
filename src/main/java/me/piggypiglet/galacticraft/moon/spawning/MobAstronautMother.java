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

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class MobAstronautMother implements Listener {
    private static final Set<EntityType> DISABLED_MOBS = Stream.of(WITCH, SPIDER, CREEPER, BAT, PHANTOM)
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
