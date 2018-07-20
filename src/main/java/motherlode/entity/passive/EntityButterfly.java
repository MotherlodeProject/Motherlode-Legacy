package motherlode.entity.passive;

import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityButterfly extends EntityFlyingInsect {
	
	public EntityButterfly(World worldIn) {
		super(worldIn);
		// If size is not small enough, butterflies become invalid after a couple of seconds and despawn
		this.setWidth(0.2F);
		this.setHeight(0.2F);
		this.setScale(0.3F);
		this.setSpeed(0.07F);
		this.setSpawnPos(Vec3d.ZERO);
		this.setupAI();
	}
	
}
