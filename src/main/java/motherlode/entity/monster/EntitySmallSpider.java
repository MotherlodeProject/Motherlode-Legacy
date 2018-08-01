package motherlode.entity.monster;

import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.world.World;

public class EntitySmallSpider extends EntitySpider {

	public EntitySmallSpider(World worldIn) {
		super(worldIn);
		this.width = 0.8F;
		this.height = 0.5F;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public float getEyeHeight() {
		return 0.2F;
	}

}
