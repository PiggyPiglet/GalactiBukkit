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

package me.piggypiglet.galacticraft.scanning.implementations;

import me.piggypiglet.galacticraft.boot.GalacticraftBootstrap;
import me.piggypiglet.galacticraft.scanning.exceptions.ScanningException;
import me.piggypiglet.galacticraft.scanning.framework.Scanner;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.security.CodeSource;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public final class ZISScanner implements Scanner {
    private static final Class<?> MAIN = GalacticraftBootstrap.class;
    private static final String PACKAGE = "me/piggypiglet/galacticraft";

    private final Set<Class<?>> classes;

    private ZISScanner(@NotNull final Set<Class<?>> classes) {
        this.classes = classes;
    }

    @NotNull
    public static ZISScanner create() {
        final ClassLoader loader = MAIN.getClassLoader();
        final CodeSource src = MAIN.getProtectionDomain().getCodeSource();

        if (src == null) {
            return new ZISScanner(Collections.emptySet());
        }

        final Set<Class<?>> classes = new HashSet<>();

        try (final ZipInputStream zip = new ZipInputStream(src.getLocation().openStream())) {
            ZipEntry entry;

            while ((entry = zip.getNextEntry()) != null) {
                final String name = entry.getName();

                if (!name.endsWith(".class") || !name.startsWith(PACKAGE)) {
                    continue;
                }

                try {
                    classes.add(loader.loadClass(name.replace('/', '.').replace(".class", "")));
                } catch (Exception ignored) {}
            }
        } catch (Exception e) {
            throw new ScanningException(e);
        }

        return new ZISScanner(classes);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Set<Class<? extends T>> getSubTypesOf(@NotNull final Class<T> type) {
        return classes.stream()
                .filter(clazz -> !clazz.isInterface() && !Modifier.isAbstract(clazz.getModifiers()))
                .filter(type::isAssignableFrom)
                .map(clazz -> (Class<? extends T>) clazz)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Class<?>> getClassesAnnotatedWith(final @NotNull Class<? extends Annotation> annotation) {
        return classes.stream()
                .filter(clazz -> clazz.isAnnotationPresent(annotation))
                .collect(Collectors.toSet());
    }
}
