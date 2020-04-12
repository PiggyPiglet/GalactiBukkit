package me.piggypiglet.galacticraft.moon.world.generation;

import me.piggypiglet.galacticraft.moon.MoonConstants;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class MoonChunkGenerator extends ChunkGenerator {
    @NotNull
    @Override
    public ChunkData generateChunkData(@NotNull final World world, @NotNull final Random random, final int chunkX,
                                       final int chunkZ, @NotNull final BiomeGrid biome) {
        final SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
        final ChunkData chunk = createChunkData(world);
        generator.setScale(0.007D);

        for (int x = 0; x < 16; ++x) {
            for (int z = 0; z < 16; ++z) {
                final int currentHeight = (int) ((generator.noise(chunkX * 16 + x, chunkZ * 16 + z, 0.005D, 0.05D, true) + 1) * 25D + 50D);
                chunk.setBlock(x, currentHeight, z, MoonConstants.MOON_SURFACE[random.nextInt(8)]);

                for (int i = currentHeight - 1; i > 0; --i)
                    chunk.setBlock(x, i, z, MoonConstants.MOON_INSIDE[random.nextInt(4)]);

                chunk.setBlock(x, 0, z, Material.BEDROCK);
            }
        }

        return chunk;
    }
}
