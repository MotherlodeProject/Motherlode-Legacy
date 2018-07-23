package motherlode.entity.passive;

import net.minecraft.world.World;

public class EntityMoth extends EntityButterfly {
	
	public EntityMoth(World worldIn) {
		super(worldIn);
		this.setScale(0.4F);
		this.setTextureIndex(0);
	}

	@Override
    public boolean getCanSpawnHere() {
		return !this.getEntityWorld().isDaytime() && super.getCanSpawnHere();
    }
	
}
