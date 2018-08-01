package motherlode.entity.passive;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public class EntityMole extends EntityLiving {

	public EntityMole(World worldIn) {
		super(worldIn);
		this.height = 0.6F;
		this.width = 1.0F;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public float getEyeHeight() {
		return 0.2F;
	}

}
