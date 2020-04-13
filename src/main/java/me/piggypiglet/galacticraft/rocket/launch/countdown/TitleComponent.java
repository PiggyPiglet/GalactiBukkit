package me.piggypiglet.galacticraft.rocket.launch.countdown;

import com.destroystokyo.paper.Title;
import me.piggypiglet.galacticraft.rocket.launch.countdown.framework.CountdownComponent;
import me.piggypiglet.galacticraft.rocket.utils.RocketUtils;
import org.bukkit.entity.ArmorStand;
import org.jetbrains.annotations.NotNull;

import static me.piggypiglet.galacticraft.utils.StringUtils.color;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class TitleComponent implements CountdownComponent {
    @Override
    public void run(@NotNull final ArmorStand rocket, final int run) {
        RocketUtils.getPlayer(rocket).ifPresent(player -> player.sendTitle(Title.builder()
                .title(color((run % 2 == 0 ? "&c" : "&f") + "&l00:" + (run > 9 ? "" : "0") + run))
                .subtitle("Till Launch")
                .fadeIn(0)
                .build()));
    }

    @Override
    public void complete(@NotNull final ArmorStand rocket) {
        RocketUtils.getPlayer(rocket)
                .ifPresent(player -> player.sendTitle(Title.builder().title("LIFT OFF!").fadeIn(0).build()));
    }
}
