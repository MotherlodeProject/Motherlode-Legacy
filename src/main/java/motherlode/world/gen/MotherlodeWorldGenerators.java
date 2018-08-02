package motherlode.world.gen;

import motherlode.world.gen.caves.ReplaceVanillaCaveGen;
import motherlode.world.gen.feature.MotherlodeFeatureGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MotherlodeWorldGenerators {

	// Called by proxy
	public static void registerWorldGens() {
		GameRegistry.registerWorldGenerator(new MotherlodeFeatureGenerator(), 10);
		MinecraftForge.TERRAIN_GEN_BUS.register(ReplaceVanillaCaveGen.class);
	}
}
