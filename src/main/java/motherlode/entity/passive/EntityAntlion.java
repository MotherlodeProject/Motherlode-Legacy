package motherlode.entity.passive;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public class EntityAntlion extends EntityLiving {

	public EntityAntlion(World worldIn) {
		super(worldIn);
		this.height = 1.0F;
		this.width = 1.0F;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public float getEyeHeight() {
		return 0.5F;
	}

}
