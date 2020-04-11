package me.piggypiglet.galacticraft.guice.objects;

import com.google.inject.Injector;
import me.piggypiglet.galacticraft.guice.exceptions.InjectionException;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class SafeInjector {
    private final Injector handle;

    public SafeInjector(@NotNull final Injector handle) {
        this.handle = handle;
    }

    @NotNull
    public <T> T getInstance(@NotNull final Class<T> clazz) {
        try {
            return handle.getInstance(clazz);
        } catch (Exception e) {
            throw new InjectionException(e.getMessage());
        }
    }

    @NotNull
    public Injector getHandle() {
        return handle;
    }
}
