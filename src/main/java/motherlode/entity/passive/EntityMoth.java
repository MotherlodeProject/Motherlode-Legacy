package motherlode.entity.passive;

import motherlode.Motherlode;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityMoth extends EntityButterfly {
	
//	private static ResourceLocation[] TEXTURES = {
//			new ResourceLocation(Motherlode.MOD_ID, "textures/entity/butterfly_moth.png")
//	};

	public EntityMoth(World worldIn) {
		super(worldIn);
		this.setScale(0.4F);
//		setTextures(TEXTURES);
		this.setTextureIndex(0);
	}
	
//	@Override
//	public ResourceLocation getTexture() {
//		return TEXTURES[this.getTextureIndex()];
//	}

	@Override
    public boolean getCanSpawnHere() {
		return !this.getEntityWorld().isDaytime() && super.getCanSpawnHere();
    }
	
}
