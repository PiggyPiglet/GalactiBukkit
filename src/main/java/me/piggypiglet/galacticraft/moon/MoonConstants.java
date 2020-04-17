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

package me.piggypiglet.galacticraft.moon;

import org.bukkit.Material;

import static org.bukkit.Material.*;

public final class MoonConstants {
    private MoonConstants() {
        throw new AssertionError("This class cannot be initialized.");
    }

    public static final String NAME = "moon";

    public static final int DIMENSION = 1;
    public static final double GRAVITY = 0.7;

    public static final Material[] MOON_SURFACE = new Material[]{DEAD_BRAIN_CORAL_BLOCK, DEAD_BRAIN_CORAL_BLOCK, DEAD_BRAIN_CORAL_BLOCK,
            DEAD_BRAIN_CORAL_BLOCK, DEAD_BUBBLE_CORAL_BLOCK, DEAD_FIRE_CORAL_BLOCK, DEAD_HORN_CORAL_BLOCK, DEAD_TUBE_CORAL_BLOCK};
    public static final Material[] MOON_INSIDE = new Material[]{DEAD_HORN_CORAL_BLOCK, DEAD_FIRE_CORAL_BLOCK, DEAD_TUBE_CORAL_BLOCK,
            DEAD_BUBBLE_CORAL_BLOCK};

    public static final String MOON_RP = "https://www.dropbox.com/s/bhrs5bhrlkf6vj3/rp.zip?dl=1";
    public static final String MOON_RP_HASH = "5903998b3f033d3da47fd2587c8398068564401d";
}
