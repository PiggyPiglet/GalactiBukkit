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

package me.piggypiglet.galacticraft.guice.objects;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;

public final class AnnotatedBinding<T> extends Binding<T> {
    private final Object annotation;

    public AnnotatedBinding(@NotNull final Binding<T> binding, @NotNull final Annotation annotation) {
        super(binding);
        this.annotation = annotation;
    }

    public AnnotatedBinding(@NotNull final Binding<T> binding, @NotNull final Class<? extends Annotation> annotation) {
        super(binding);
        this.annotation = annotation;
    }

    @NotNull
    public Annotation getAnnotationInstance() {
        return (Annotation) annotation;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public Class<? extends Annotation> getAnnotationClass() {
        return (Class<? extends Annotation>) annotation;
    }

    public boolean isAnnotationInstance() {
        return annotation instanceof Annotation;
    }
}
