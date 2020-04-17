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

package me.piggypiglet.galacticraft.boot;

import me.piggypiglet.galacticraft.boot.registerables.Registerable;
import me.piggypiglet.galacticraft.commands.registerables.CommandHandlerRegisterable;
import me.piggypiglet.galacticraft.commands.registerables.CommandsRegisterable;
import me.piggypiglet.galacticraft.guice.modules.DynamicModule;
import me.piggypiglet.galacticraft.guice.modules.InitialModule;
import me.piggypiglet.galacticraft.guice.objects.SafeInjector;
import me.piggypiglet.galacticraft.moon.resourcepack.registerables.CreditLoggingRegisterable;
import me.piggypiglet.galacticraft.moon.world.registerables.MoonWorldRegisterable;
import me.piggypiglet.galacticraft.rocket.launch.registerables.LaunchComponentsRegisterable;
import me.piggypiglet.galacticraft.rocket.registerables.RocketRecipeRegisterable;
import me.piggypiglet.galacticraft.scanning.auto.EventsRegisterable;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public final class GalacticraftBootstrap extends JavaPlugin {
    private static final List<Class<? extends Registerable>> REGISTERABLES = Arrays.asList(
            CreditLoggingRegisterable.class, LaunchComponentsRegisterable.class, CommandsRegisterable.class,
            CommandHandlerRegisterable.class, MoonWorldRegisterable.class, RocketRecipeRegisterable.class,
            EventsRegisterable.class/*, GravityRegisterable.class*/
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
