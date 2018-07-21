package motherlode.entity.passive;

import motherlode.Motherlode;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityButterflyExotic extends EntityButterfly {
	
//	private static ResourceLocation[] TEXTURES = {
//			new ResourceLocation(Motherlode.MOD_ID, "textures/entity/butterfly_exotic_0.png"),
//			new ResourceLocation(Motherlode.MOD_ID, "textures/entity/butterfly_exotic_1.png"),
//			new ResourceLocation(Motherlode.MOD_ID, "textures/entity/butterfly_exotic_2.png")
//	};

	public EntityButterflyExotic(World worldIn) {
		super(worldIn);
//		setTextures(TEXTURES);
//		this.setTextureIndex(this.getRNG().nextInt(TEXTURES.length));
		this.setTextureIndex(this.getRNG().nextInt(3));
	}
	
//	@Override
//	public ResourceLocation getTexture() {
//		return TEXTURES[this.getTextureIndex()];
//	}

}
