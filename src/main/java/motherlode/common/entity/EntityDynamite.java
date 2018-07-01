package motherlode.common.entity;

import motherlode.common.item.ItemDynamite;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;

public class EntityDynamite extends EntityThrowable {
	
	private static final double FRICTION_COEFFICIENT = 0.8D;
	private float rotationYaw;
	private World world;
	private float width = 0.5F;
	private float height = 0.5F;
	private int fuseStepTicks; // Number of ticks for each of the four Dynamite update steps. Or an inverse speed of burning
	private int fuseTicksAlive; // Total fuse lifetime
	private float explosionStrength;
	
	public EntityDynamite(World worldIn) {
		super(worldIn);
		this.world = worldIn;
		this.setSize(this.width, this.height); //bounding box
		this.rotationYaw = 0;
		this.rotationPitch = 0;
		this.setFuseStepTicks(ItemDynamite.FUSE_TICKS);
		this.setFuseTicksAlive(0);
		this.setExplosionStrength(ItemDynamite.EXPLOSION_STRENGTH);
	}
	
	public EntityDynamite(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
		this.world = worldIn;
		this.setSize(this.width, this.height); //bounding box
		this.rotationYaw = throwerIn.rotationYaw;
		this.rotationPitch = throwerIn.rotationPitch;
		this.setFuseStepTicks(ItemDynamite.FUSE_TICKS);
		this.setFuseTicksAlive(0);
		this.setExplosionStrength(ItemDynamite.EXPLOSION_STRENGTH);
    }
	
	public EntityDynamite(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
		this.world = worldIn;
		this.setSize(this.width, this.height); //bounding box
		this.rotationYaw = 0;
		this.rotationPitch = 0;
		this.setFuseStepTicks(ItemDynamite.FUSE_TICKS);
		this.setFuseTicksAlive(0);
		this.setExplosionStrength(ItemDynamite.EXPLOSION_STRENGTH);
    }
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		this.pushOutOfBlocks(this.posX, this.posY + this.height/2, this.posZ);
		this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
		this.fuseTicksAlive++;
		
		// From other entities, stops them moving around somewhat (it seems)
		if (this.onGround) {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
            this.motionY *= -0.5D;
        }
		
//		if (this.fuseTicksAlive >= this.fuseStepTicks) {
//			this.world.createExplosion(this, this.posX, this.posY, this.posZ, this.explosionStrength, true);
//			this.setDead();
//		}
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (result.typeOfHit == Type.BLOCK) {
			
			BlockPos pos = result.getBlockPos();
			EnumFacing side = result.sideHit;
			
			if (world.isSideSolid(pos, side)) {
				// east = (1, 0, 0)
				// up = (0, 1, 0)
				// south = (0, 0, 1)
				if ((side == EnumFacing.NORTH) || (side == EnumFacing.SOUTH)) {
					this.motionX = FRICTION_COEFFICIENT*this.motionX;
					this.motionY = FRICTION_COEFFICIENT*this.motionY;
					this.motionZ = -FRICTION_COEFFICIENT*this.motionZ;
				} else if ((side == EnumFacing.EAST) || (side == EnumFacing.WEST)) {
					this.motionX = -FRICTION_COEFFICIENT*this.motionX;
					this.motionY = FRICTION_COEFFICIENT*this.motionY;
					this.motionZ = FRICTION_COEFFICIENT*this.motionZ;
				} else {
					this.motionX = FRICTION_COEFFICIENT*this.motionX;
					this.motionY = -FRICTION_COEFFICIENT*this.motionY;
					this.motionZ = FRICTION_COEFFICIENT*this.motionZ;
				}
			}
		}
	}
	
	public void setExplosionStrength(float strength) {
		this.explosionStrength = strength;
	}
	
	public void setFuseStepTicks(int ticks) {
		this.fuseStepTicks = ticks;
	}
	
	public void setFuseTicksAlive(int ticks) {
		this.fuseTicksAlive = ticks;
	}
	
	@Override
	protected float getGravityVelocity() {
        return 0.05F;
    }
	
	@Override
	protected boolean canTriggerWalking() {
		return false;
	}
	
	// ItemDynamite sets rotation based on the player at the moment of throwing,
	// RenderDynamite calls the getter
	// Doesn't work
	public void setRotationYaw(float yaw) {
		this.rotationYaw = yaw;
	}
	
	public float getRotationYaw() {
		return this.rotationYaw;
	}
	
	public double getVelocitySquared() {
		return this.motionX*this.motionX + this.motionY*this.motionY + this.motionZ*this.motionZ;
	}
}
