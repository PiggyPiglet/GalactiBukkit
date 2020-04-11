package me.piggypiglet.galacticraft.scanning.framework;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.util.Set;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public interface Scanner {
    <T> Set<Class<? extends T>> getSubTypesOf(@NotNull final Class<T> type);

    Set<Class<?>> getClassesAnnotatedWith(@NotNull final Class<? extends Annotation> annotation);
}
