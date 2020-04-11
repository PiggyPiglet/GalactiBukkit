package me.piggypiglet.galacticraft.scanning.auto;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import me.piggypiglet.galacticraft.boot.registerables.Registerable;
import me.piggypiglet.galacticraft.scanning.framework.Scanner;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class EventsRegisterable extends Registerable {
    private static final ProtocolManager PROTOCOL_MANAGER = ProtocolLibrary.getProtocolManager();

    @Inject private Scanner scanner;
    @Inject private JavaPlugin main;

    @Override
    protected void execute() {
        scanner.getSubTypesOf(Listener.class).stream()
                .map(injector::getInstance)
                .forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, main));

        scanner.getSubTypesOf(PacketAdapter.class).stream()
                .map(injector::getInstance)
                .forEach(PROTOCOL_MANAGER::addPacketListener);
    }
}
