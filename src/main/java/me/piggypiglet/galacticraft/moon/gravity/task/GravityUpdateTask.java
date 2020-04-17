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

package me.piggypiglet.galacticraft.moon.gravity.task;

import me.piggypiglet.galacticraft.moon.MoonConstants;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class GravityUpdateTask implements Runnable {
    private final Map<UUID, Vector> velocities = new HashMap<>();
    private final Map<UUID, Boolean> onGround = new HashMap<>();
    private final Map<UUID, Location> positions = new HashMap<>();
    private final World moon = Bukkit.getWorld(MoonConstants.NAME);

    @Inject private JavaPlugin main;

    @Override
    public void run() {
        for (final Entity entity : moon.getEntities()) {
            final UUID uuid = entity.getUniqueId();
            final Vector newVector = entity.getVelocity().clone();

            final boolean isFlying;

            if (entity instanceof Player) {
                isFlying = ((Player) entity).isFlying();
            } else {
                isFlying = false;
            }

            if (velocities.containsKey(uuid) && onGround.containsKey(uuid) && !entity.isOnGround() && !entity.isInsideVehicle() && !isFlying) {
                final Vector oldVector = velocities.get(entity.getUniqueId());

                if (!onGround.get(uuid)) {
                    final Vector gravitalVector = oldVector.clone();
                    gravitalVector.subtract(newVector);
                    final double gravitalY = gravitalVector.getY();

                    if (gravitalY > 0.0 && (newVector.getY() < -0.01 || newVector.getY() > 0.01)) {
                        final Location location = entity.getLocation().clone();

                        while (location.getBlockY() >= 0) {
                            final Block block = location.getBlock();

                            if (block.getType() != Material.AIR) break;

                            location.setY(location.getY() - 1.0);
                        }

                        newVector.setY(oldVector.getY() - gravitalY * MoonConstants.GRAVITY);
                        final boolean newXChanged = newVector.getX() < -0.001 || newVector.getX() > 0.001;
                        final boolean oldXChanged = oldVector.getX() < -0.001 || oldVector.getX() > 0.001;

                        if (newXChanged && oldXChanged) {
                            newVector.setX(oldVector.getX());
                        }

                        final boolean newZChanged = newVector.getZ() < -0.001 || newVector.getZ() > 0.001;
                        final boolean oldZChanged = oldVector.getZ() < -0.001 || oldVector.getZ() > 0.001;

                        if (newZChanged && oldZChanged) {
                            newVector.setZ(oldVector.getZ());
                        }
                    }
                } else if (entity instanceof Player && positions.containsKey(uuid)) {
                    final Vector position = entity.getLocation().toVector();
                    final Vector oldPosition = positions.get(uuid).toVector();
                    final Vector velocity = position.subtract(oldPosition);

                    newVector.setX(velocity.getX());
                    newVector.setZ(velocity.getZ());
                }

                entity.setVelocity(newVector.clone());
            }

            velocities.put(uuid, newVector.clone());
            onGround.put(uuid, entity.isOnGround());
            positions.put(uuid, entity.getLocation());
        }

        Bukkit.getScheduler().runTaskLater(main, this, 1L);
    }
}
