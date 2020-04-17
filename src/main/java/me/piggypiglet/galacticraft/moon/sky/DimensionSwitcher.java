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

package me.piggypiglet.galacticraft.moon.sky;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import me.piggypiglet.galacticraft.moon.MoonConstants;
import me.piggypiglet.galacticraft.moon.sky.framework.PacketAdapterWrapper;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import javax.inject.Inject;

public final class DimensionSwitcher extends PacketAdapterWrapper {
    private static final ProtocolManager PROTOCOL_MANAGER = ProtocolLibrary.getProtocolManager();

    @Inject private Logger logger;

    @Inject
    public DimensionSwitcher(@NotNull final JavaPlugin main) {
        super(main, PacketType.Play.Server.POSITION);
    }

    @Override
    public void onPacketSending(@NotNull final PacketEvent event) {
        final Player player = event.getPlayer();
        final World world = player.getWorld();

        if (!world.getName().equals(MoonConstants.NAME)) return;

        sendPacket(player, createDimensionSwitch(player, world.getWorldType(), MoonConstants.DIMENSION));
    }

    private void sendPacket(@NotNull final Player player, @NotNull final PacketContainer packet) {
        try {
            PROTOCOL_MANAGER.sendServerPacket(player, packet, false);
        } catch (Exception exception) {
            player.kickPlayer("Dimension switch failure.");
            logger.error("Something went wrong when switching " + player.getName() + "'s dimension.",
                    exception);
        }
    }

    private static PacketContainer createDimensionSwitch(@NotNull final Player player, @NotNull final WorldType worldType,
                                                         final int dimension) {
        final PacketContainer packet = PROTOCOL_MANAGER.createPacket(PacketType.Play.Server.RESPAWN);
        packet.getDimensions().write(0, dimension);
        packet.getGameModes().write(0, EnumWrappers.NativeGameMode.fromBukkit(player.getGameMode()));
        packet.getWorldTypeModifier().write(0, worldType);

        return packet;
    }
}
