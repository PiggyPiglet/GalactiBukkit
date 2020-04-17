package me.piggypiglet.galacticraft.moon;

import org.bukkit.Material;

import static org.bukkit.Material.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
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
