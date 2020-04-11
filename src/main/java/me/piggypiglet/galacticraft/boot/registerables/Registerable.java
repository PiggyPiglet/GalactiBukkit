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

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
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
