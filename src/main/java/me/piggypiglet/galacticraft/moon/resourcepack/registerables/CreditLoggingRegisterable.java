package me.piggypiglet.galacticraft.moon.resourcepack.registerables;

import me.piggypiglet.galacticraft.boot.registerables.Registerable;
import org.slf4j.Logger;

import javax.inject.Inject;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class CreditLoggingRegisterable extends Registerable {
    @Inject private Logger logger;

    @Override
    protected void execute() {
        logger.info("Rocket model is by Hightingale - https://sketchfab.com/hightingale");
    }
}
