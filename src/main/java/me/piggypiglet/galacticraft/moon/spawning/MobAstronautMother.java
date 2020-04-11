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

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class MobAstronautMother implements Listener {
    private static final ItemStack HELMET = new ItemStack(Material.GLASS);

    @EventHandler
    public void onEntitySpawn(@NotNull final EntitySpawnEvent event) {
        final Entity entity = event.getEntity();

        if (!event.getLocation().getWorld().getName().equals(MoonConstants.NAME)) return;
        if (!(entity instanceof LivingEntity)) return;
        if (entity.getType() == EntityType.WITCH || entity.getType() == EntityType.SPIDER
                || entity.getType() == EntityType.CREEPER) {
            event.setCancelled(true);
            return;
        }

        ((LivingEntity) entity).getEquipment().setHelmet(HELMET);
    }
}
