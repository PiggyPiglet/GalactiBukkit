package me.piggypiglet.galacticraft.annotations.scanning;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
@Retention(RetentionPolicy.RUNTIME) @Target(ElementType.TYPE)
public @interface Hidden {
}
