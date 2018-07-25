package motherlode.entity.passive;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Items;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityLizard extends EntityAnimal {

	public static final float SPEED = 2.8F;

	public EntityLizard(World worldIn) {
		super(worldIn);
		setSize(0.5F, 0.25F);
		setScaleForAge(true);
		setAIMoveSpeed(SPEED);
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, SPEED));
		this.tasks.addTask(2, new EntityAIMate(this, SPEED));
		this.tasks.addTask(3, new EntityAITempt(this, SPEED, Items.PAINTING, false));
		this.tasks.addTask(4, new EntityAIWanderAvoidWater(this, SPEED));
		this.tasks.addTask(5, new EntityAILookIdle(this));
	}

	@Nullable
	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return new EntityLizard(world);
	}
}
