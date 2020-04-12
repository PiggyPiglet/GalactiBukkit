package me.piggypiglet.galacticraft.rocket.registerables;

import me.piggypiglet.galacticraft.boot.registerables.Registerable;
import me.piggypiglet.galacticraft.rocket.RocketConstants;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class RocketRecipeRegisterable extends Registerable {
    @Inject private JavaPlugin main;

    @Override
    protected void execute() {
        final NamespacedKey key = new NamespacedKey(main, "rocket");
        final ShapedRecipe recipe = new ShapedRecipe(key, RocketConstants.ROCKET);

        recipe.shape(
                " I ",
                "IFI",
                "IEI"
        );

        recipe.setIngredient('I', new ItemStack(Material.IRON_INGOT, 64));
        recipe.setIngredient('F', Material.SHULKER_BOX);
        recipe.setIngredient('E', Material.DRAGON_HEAD);

        Bukkit.addRecipe(recipe);
    }
}
