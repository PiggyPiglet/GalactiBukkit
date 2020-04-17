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

package me.piggypiglet.galacticraft.guice.modules;

import com.google.inject.AbstractModule;
import com.google.inject.binder.AnnotatedBindingBuilder;
import com.google.inject.binder.LinkedBindingBuilder;
import me.piggypiglet.galacticraft.guice.objects.AnnotatedBinding;
import me.piggypiglet.galacticraft.guice.objects.Binding;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public final class DynamicModule extends AbstractModule {
    private final Set<Binding<?>> bindings;
    private final Class<?>[] staticInjections;

    public DynamicModule(@NotNull final Set<Binding<?>> bindings, @NotNull final Class<?>... staticInjections) {
        this.bindings = bindings;
        this.staticInjections = staticInjections;
    }

    @Override
    protected void configure() {
        bindings.forEach(this::bind);
        requestStaticInjection(staticInjections);
    }

    private <T> void bind(@NotNull final Binding<T> binding) {
        final AnnotatedBindingBuilder<? super T> bind;

        if (binding.isTypeLiteral()) {
            bind = bind(binding.getTypeLiteral());
        } else {
            bind = bind(binding.getInterface());
        }

        final AnnotatedBinding<T> annotatedBinding = binding instanceof AnnotatedBinding ? (AnnotatedBinding<T>) binding : null;
        final LinkedBindingBuilder<? super T> link;

        if (annotatedBinding != null) {
            if (annotatedBinding.isAnnotationInstance()) {
                link = bind.annotatedWith(annotatedBinding.getAnnotationInstance());
            } else {
                link = bind.annotatedWith(annotatedBinding.getAnnotationClass());
            }
        } else {
            link = bind;
        }

        link.toInstance(binding.getInstance());
    }
}
