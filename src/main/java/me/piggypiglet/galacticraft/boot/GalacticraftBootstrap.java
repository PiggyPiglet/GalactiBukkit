package me.piggypiglet.galacticraft.boot;

import me.piggypiglet.galacticraft.boot.registerables.Registerable;
import me.piggypiglet.galacticraft.commands.registerables.CommandHandlerRegisterable;
import me.piggypiglet.galacticraft.commands.registerables.CommandsRegisterable;
import me.piggypiglet.galacticraft.guice.modules.DynamicModule;
import me.piggypiglet.galacticraft.guice.modules.InitialModule;
import me.piggypiglet.galacticraft.guice.objects.SafeInjector;
import me.piggypiglet.galacticraft.moon.gravity.registerables.GravityRegisterable;
import me.piggypiglet.galacticraft.moon.world.registerables.MoonWorldRegisterable;
import me.piggypiglet.galacticraft.scanning.auto.EventsRegisterable;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class GalacticraftBootstrap extends JavaPlugin {
    private static final List<Class<? extends Registerable>> REGISTERABLES = Arrays.asList(
            CommandsRegisterable.class, CommandHandlerRegisterable.class, MoonWorldRegisterable.class,
            EventsRegisterable.class, GravityRegisterable.class
    );

    @Override
    public void onEnable() {
        final AtomicReference<SafeInjector> injector = new AtomicReference<>(
                new SafeInjector(new InitialModule(this).createInjector())
        );

        for (final Class<? extends Registerable> registerableClass : REGISTERABLES) {
            final SafeInjector safeInjector = injector.get();
            final Registerable registerable = safeInjector.getInstance(registerableClass);
            registerable.run(safeInjector);

            if (!registerable.getBindings().isEmpty() || registerable.getStaticInjections().length > 0) {
                injector.set(new SafeInjector(injector.get().getHandle().createChildInjector(new DynamicModule(
                        registerable.getBindings(),
                        registerable.getStaticInjections()
                ))));
            }
        }
    }
}
