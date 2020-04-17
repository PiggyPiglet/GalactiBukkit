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

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public final class RocketUtils {
    private RocketUtils() {
        throw new AssertionError("This class cannot be initialized.");
    }

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
