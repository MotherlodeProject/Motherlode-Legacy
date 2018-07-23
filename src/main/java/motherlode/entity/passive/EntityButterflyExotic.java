package motherlode.entity.passive;

import net.minecraft.world.World;

public class EntityButterflyExotic extends EntityButterfly {
	

	public EntityButterflyExotic(World worldIn) {
		super(worldIn);
		this.setTextureIndex(this.getRNG().nextInt(3));
	}

}
