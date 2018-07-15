package motherlode.client.model;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;

import java.util.List;

public interface ItemVariantGetter {
	public List<ModelResourceLocation> getItemVariants(List<ModelResourceLocation> resourceLocations);
}
