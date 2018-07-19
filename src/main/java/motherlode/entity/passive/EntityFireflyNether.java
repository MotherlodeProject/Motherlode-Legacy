package motherlode.entity.passive;

import net.minecraft.world.World;

public class EntityFireflyNether extends EntityFirefly {

	public EntityFireflyNether(World worldIn) {
		super(worldIn);
		this.setColor("f75531");
		this.isImmuneToFire = true;
	}

}
