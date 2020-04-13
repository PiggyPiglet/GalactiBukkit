package me.piggypiglet.galacticraft.guice.objects;

import com.google.inject.TypeLiteral;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public class Binding<T> {
    private final Object type;
    private final T instance;

    public Binding(@NotNull final TypeLiteral<? super T> type, @NotNull final T instance) {
        this.type = type;
        this.instance = instance;
    }

    public Binding(@NotNull final Class<? super T> interfaze, @NotNull final T instance) {
        this.type = interfaze;
        this.instance = instance;
    }

    protected Binding(@NotNull final Binding<T> binding) {
        type = binding.type;
        instance = binding.instance;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public TypeLiteral<? super T> getTypeLiteral() {
        return (TypeLiteral<? super T>) type;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public Class<? super T> getInterface() {
        return (Class<? super T>) type;
    }

    public boolean isTypeLiteral() {
        return type instanceof TypeLiteral;
    }

    @NotNull
    public T getInstance() {
        return instance;
    }
}
