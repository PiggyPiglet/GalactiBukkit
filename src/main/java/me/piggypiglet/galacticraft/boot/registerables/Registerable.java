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

package me.piggypiglet.galacticraft.boot.registerables;

import com.google.inject.TypeLiteral;
import me.piggypiglet.galacticraft.guice.objects.AnnotatedBinding;
import me.piggypiglet.galacticraft.guice.objects.Binding;
import me.piggypiglet.galacticraft.guice.objects.SafeInjector;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class Registerable {
    protected SafeInjector injector;

    private final Set<Binding<?>> bindings = new HashSet<>();
    private final Set<Class<?>> staticInjections = new HashSet<>();

    protected abstract void execute();

    protected <T> void addBinding(@NotNull final Class<? super T> interfaze, @NotNull final T instance) {
        bindings.add(new Binding<>(interfaze, instance));
    }

    protected <T> void addBinding(@NotNull final TypeLiteral<? super T> type, @NotNull final T instance) {
        bindings.add(new Binding<>(type, instance));
    }

    protected <T> void addBinding(@NotNull final Class<? super T> interfaze, @NotNull final Annotation annotation,
                                  @NotNull final T instance) {
        bindings.add(new AnnotatedBinding<>(new Binding<>(interfaze, instance), annotation));
    }

    protected <T> void addBinding(@NotNull final Class<? super T> interfaze, @NotNull final Class<? extends Annotation> annotation,
                                  @NotNull final T instance) {
        bindings.add(new AnnotatedBinding<>(new Binding<>(interfaze, instance), annotation));
    }

    protected <T> void addBinding(@NotNull final TypeLiteral<? super T> type, @NotNull final Annotation annotation,
                                  @NotNull final T instance) {
        bindings.add(new AnnotatedBinding<>(new Binding<>(type, instance), annotation));
    }

    protected <T> void addBinding(@NotNull final TypeLiteral<? super T> type, @NotNull final Class<? extends Annotation> annotation,
                                  @NotNull final T instance) {
        bindings.add(new AnnotatedBinding<>(new Binding<>(type, instance), annotation));
    }

    protected void requestStaticInjections(@NotNull final Class<?>... classes) {
        staticInjections.addAll(Arrays.asList(classes));
    }

    public void run(@NotNull final SafeInjector injector) {
        this.injector = injector;
        execute();
    }

    @NotNull
    public Set<Binding<?>> getBindings() {
        return bindings;
    }

    @NotNull
    public Class<?>[] getStaticInjections() {
        return staticInjections.toArray(new Class<?>[]{});
    }
}
