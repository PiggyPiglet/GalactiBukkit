package me.piggypiglet.galacticraft.rocket.utils;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class RocketUtils {
    @SuppressWarnings("ConstantConditions")
    @NotNull
    public static Optional<ArmorStand> getRocket(@NotNull final Player player) {
        if (!player.isInsideVehicle() || !player.getVehicle().isInsideVehicle() ||
                !player.getVehicle().getVehicle().hasMetadata("rocket")) return Optional.empty();

        return Optional.of((ArmorStand) player.getVehicle().getVehicle());
    }

    public static Optional<Player> getPlayer(@NotNull final ArmorStand rocket) {
        if (rocket.getPassengers().isEmpty()
                || rocket.getPassengers().stream().noneMatch(entity -> entity instanceof ArmorStand)) return Optional.empty();
        final Entity passenger = rocket.getPassengers().stream().filter(entity -> entity instanceof ArmorStand).findAny().get();
        if (passenger.getPassengers().isEmpty()
                || passenger.getPassengers().stream().noneMatch(entity -> entity instanceof Player)) return Optional.empty();

        return passenger.getPassengers().stream().filter(entity -> entity instanceof Player).findAny()
                .map(entity -> (Player) entity);
    }
}
