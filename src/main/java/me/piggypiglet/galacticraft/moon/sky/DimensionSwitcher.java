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

import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class DimensionSwitcher extends PacketAdapterWrapper {
    private static final ProtocolManager PROTOCOL_MANAGER = ProtocolLibrary.getProtocolManager();

    private final Logger logger;

    @Inject
    public DimensionSwitcher(@NotNull final JavaPlugin main) {
        super(main, PacketType.Play.Server.POSITION);
        logger = main.getLogger();
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
            logger.log(Level.SEVERE, "Something went wrong when switching " + player.getName() + "'s dimension.",
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
