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

package me.piggypiglet.galacticraft.rocket.launch.countdown;

import com.destroystokyo.paper.Title;
import me.piggypiglet.galacticraft.rocket.launch.countdown.framework.CountdownComponent;
import me.piggypiglet.galacticraft.rocket.utils.RocketUtils;
import org.bukkit.entity.ArmorStand;
import org.jetbrains.annotations.NotNull;

import static me.piggypiglet.galacticraft.utils.StringUtils.color;

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
