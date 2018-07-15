package motherlode.world.biome;

import motherlode.Motherlode;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;

public class BiomeMotherlode extends Biome {
	public int grassColor = -1, foliageColor = -1, skyColor = -1;

	public BiomeMotherlode(String name, BiomeProperties properties) {
		super(properties);
		setRegistryName(new ResourceLocation(Motherlode.MOD_ID, name));
	}

	public void setGrassColor(Integer grassColor) {
		this.grassColor = grassColor;
	}

	public void setFoliageColor(Integer foliageColor) {
		this.foliageColor = foliageColor;
	}

	public void setSkyColor(Integer skyColor) {
		this.skyColor = skyColor;
	}

	@Override
	public int getModdedBiomeGrassColor(int original) {
		return grassColor >= 0 ? grassColor : super.getModdedBiomeGrassColor(original);
	}

	@Override
	public int getModdedBiomeFoliageColor(int original) {
		return foliageColor >= 0 ? foliageColor : super.getModdedBiomeFoliageColor(original);
	}

	@Override
	public int getSkyColorByTemp(float currentTemperature) {
		return skyColor >= 0 ? skyColor : super.getSkyColorByTemp(currentTemperature);
	}
}
