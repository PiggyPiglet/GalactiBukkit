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

import com.google.inject.TypeLiteral;
import org.jetbrains.annotations.NotNull;

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
