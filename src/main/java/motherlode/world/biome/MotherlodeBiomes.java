package motherlode.world.biome;

import motherlode.registry.MotherlodeRegistry;

public class MotherlodeBiomes {
	public static final BiomeMotherlode SPIDER_FOREST = register(new BiomeSpiderForest());

	private static BiomeMotherlode register(BiomeMotherlode biome) {
		MotherlodeRegistry.BIOMES.add(biome);
		return biome;
	}

	public static void load() {}
}
