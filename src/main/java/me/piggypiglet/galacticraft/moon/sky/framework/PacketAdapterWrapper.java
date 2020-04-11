package me.piggypiglet.galacticraft.moon.sky.framework;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public abstract class PacketAdapterWrapper extends PacketAdapter {
    public PacketAdapterWrapper(@NotNull final JavaPlugin main, @NotNull final PacketType type) {
        super(PacketAdapter.params(main, type));
    }
}
