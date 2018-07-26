package motherlode.entity.passive;

import net.minecraft.world.World;

public class EntityFireflyInferno extends EntityFirefly {

	public EntityFireflyInferno(World worldIn) {
		super(worldIn);
		this.setColor("f75531");
		this.isImmuneToFire = true;
	}

}
