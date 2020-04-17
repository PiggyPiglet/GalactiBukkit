package me.piggypiglet.galacticraft.guice.modules;

import com.google.inject.*;
import me.piggypiglet.galacticraft.scanning.framework.Scanner;
import me.piggypiglet.galacticraft.scanning.implementations.ZISScanner;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class InitialModule extends AbstractModule {
    private final JavaPlugin main;

    public InitialModule(@NotNull final JavaPlugin main) {
        this.main = main;
    }

    @NotNull
    public Injector createInjector() {
        return Guice.createInjector(this);
    }

    @Provides
    @Singleton
    public JavaPlugin providesMain() {
        return main;
    }

    @Provides
    @Singleton
    public Scanner providesScanner() {
        return ZISScanner.create();
    }

    @Provides
    @Singleton
    public Logger providesLogger() {
        return main.getSLF4JLogger();
    }
}
