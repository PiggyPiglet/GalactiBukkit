package me.piggypiglet.galacticraft.rocket.listeners;

import me.piggypiglet.galacticraft.rocket.RocketConstants;
import me.piggypiglet.galacticraft.rocket.utils.RocketItemUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.stream.Stream;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class RocketCraftInterceptor implements Listener {
    @EventHandler
    public void onRocketCraftAttempt(@NotNull final PrepareItemCraftEvent event) {
        if (notRocketRecipe(event.getRecipe())) return;

        final CraftingInventory table = event.getInventory();
        final ItemStack[] matrix = table.getMatrix();

        if (Stream.of(1, 3, 5, 6, 8).anyMatch(i -> matrix[i].getAmount() != 64)) {
            table.setResult(null);
        }
    }

    @EventHandler
    public void onRocketCraft(@NotNull final CraftItemEvent event) {
        if (notRocketRecipe(event.getRecipe())) return;

        final CraftingInventory table = event.getInventory();
        table.setMatrix(new ItemStack[9]);
        event.setCurrentItem(RocketConstants.ROCKET.clone());

        RocketItemUtils.populateRocket(Objects.requireNonNull(event.getCurrentItem()));
    }

    private static boolean notRocketRecipe(@Nullable final Recipe recipe) {
        return recipe == null || !recipe.getResult().equals(RocketConstants.ROCKET);
    }
}
