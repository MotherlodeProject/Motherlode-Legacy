package motherlode.entity.passive;

import java.util.Random;

import javax.annotation.Nullable;

import motherlode.client.particle.ParticleFireflyTail;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityFirefly extends EntityFlying {

	private float scale = 0.8F;
	private float width = 0.2F * scale;
	private float height = 0.2F * scale;
	private double speed = 0.07D;
	private Vec3d spawnPos = Vec3d.ZERO;
	private float red;
	private float green;
	private float blue;
	
	public EntityFirefly(World worldIn) {
		super(worldIn);
		this.setSize(this.width, this.height);
		this.setupAI();
	}
	
	public void setColor(String colorHex) {
		String sRed = colorHex.substring(0, 2);
		String sGreen = colorHex.substring(2, 4);
		String sBlue = colorHex.substring(4, 6);
		
		float fRed = (float)Integer.parseInt(sRed, 16) / 255F;
		float fGreen = (float)Integer.parseInt(sGreen, 16) / 255F;
		float fBlue = (float)Integer.parseInt(sBlue, 16) / 255F;
		
		this.setRGB(fRed, fGreen, fBlue);
	}
	
	@Override
	public boolean canBePushed() {
		return false;
	}
	
	@Override
	protected boolean canTriggerWalking() {
		return false;
	}
	
	public float getScale() {
		return this.scale;
	}
	
	@Override
	@Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
		this.spawnPos = this.getPositionVector();
		return super.onInitialSpawn(difficulty, livingdata);
	}
	
	@Override
    public boolean getCanSpawnHere() {
		return !this.getEntityWorld().isDaytime() && super.getCanSpawnHere();
    }
	
	@Override
    public void writeEntityToNBT(NBTTagCompound compound) {
    	super.writeEntityToNBT(compound);
    	compound.setTag("spawnPos", this.newDoubleNBTList(this.spawnPos.x, this.spawnPos.y, this.spawnPos.z));
    }
    
	@Override
    public void readEntityFromNBT(NBTTagCompound compound) {
    	super.readEntityFromNBT(compound);
    	if (compound.hasKey("spawnPos") && compound.getTag("spawnPos") != null) {
			NBTTagList tag = (NBTTagList)compound.getTag("spawnPos");
			this.spawnPos = new Vec3d(tag.getDoubleAt(0), tag.getDoubleAt(1), tag.getDoubleAt(2));
    	}
    }
	
	@Override
    public int getBrightnessForRender() {
		return 15728880;
	}
	
	public float getRed() {
		return this.red;
	}
	
	public float getGreen() {
		return this.green;
	}
	
	public float getBlue() {
		return this.blue;
	}
	
	public void setRGB(float red, float green, float blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	public void moveInDirection(Vec3d v) {
		motionX = v.x;
		motionY = v.y;
		motionZ = v.z;
		velocityChanged = true;
		
		rotationYaw = (float)MathHelper.atan2(v.z, v.x) * 180F / (float)Math.PI;
		setRenderYawOffset(rotationYaw);
	}
	
	@Override
    public void onLivingUpdate() {
		if (!this.world.isRemote) {
			
			if (this.ticksExisted % 5 == 0) {
				ParticleFireflyTail tailParticle = new ParticleFireflyTail(
						this.world, this.prevPosX, this.prevPosY, this.prevPosZ, 0, 0, 0);
				tailParticle.setRBGColorF(this.red, this.green, this.blue);
				Minecraft.getMinecraft().effectRenderer.addEffect(tailParticle);
			}
		}
		
		super.onLivingUpdate();
	}
    
	public void setupAI() {
		tasks.taskEntries.clear();
		targetTasks.taskEntries.clear();
		tasks.addTask(1, new AIFlyRandomly(this));
		tasks.addTask(0, new AIFlyToGroundAndDespawn(this));
	}
	
	// Default AI. Can be seen as repeating a flight cycle, with exceptions.
	// One flight cycle lasts for newDirectionTimer amount of ticks. At start of cycle,
	// a random vector, targetDirection, is generated, representing the direction in which
	// it will fly for the duration of the cycle. baseVec1 and baseVec2 are perpendicular
	// to each other and both are perpendicular to targetDirection; targetDirection is a
	// normal vector to the plane spanned by baseVec1 and baseVec2.
	// Exceptions:
	// If firefly is too far up, shouldFlyDown() is true and it flies down.
	// If firefly is sitting on a block, shouldFlyUp() is true, and it flies up.
	// If firefly collides with a block, flight is cancelled and it sits in place for 
	// a while.
	static class AIFlyRandomly extends EntityAIBase {
		
		private EntityFirefly firefly;
		private int newDirectionTimer;
		Random rng;
		private Vec3d targetDirection = Vec3d.ZERO;
		private Vec3d baseVec1 = Vec3d.ZERO;
		private Vec3d baseVec2 = Vec3d.ZERO;
		private int totalSitTime;
		private int minimumSitTime;
		private int totalFlightTime;
		private boolean isFlyingRandomly = true;
		private boolean isFlyingUp = false;
		private boolean isFlyingDown = false;
		
		public AIFlyRandomly(EntityFirefly entity) {
			this.firefly = entity;
			this.setMutexBits(1);
			this.rng = firefly.getRNG();
			this.totalSitTime = 0;
			this.minimumSitTime = 20;
			this.totalFlightTime = 0;
		}

		@Override
		public boolean shouldExecute() {
			return true;
		}
		
		// Called when newDirectionTimer reaches 0 or when has sat for enough time
		@Override
		public void startExecuting() {
			newDirectionTimer = 40 + rng.nextInt(80);
			minimumSitTime = 20 + rng.nextInt(80);

			if (shouldFlyUp()) {
				newDirectionTimer = 20;
				isFlyingUp = true;
				isFlyingDown = false;
				isFlyingRandomly = false;
			} else if (shouldFlyDown()) {
				newDirectionTimer = 20;
				isFlyingDown = true;
				isFlyingUp = false;
				isFlyingRandomly = false;
			} else {
				generateRandomDirectionVectors();
				isFlyingRandomly = true;
				isFlyingUp = false;
				isFlyingDown = false;
			}
		}
		
		// Called every tick, goes to next AI task if false 
		@Override
		public boolean shouldContinueExecuting() {
			// If should sit
			if (isTouchingBlock() && totalFlightTime > 20) {
				isFlyingRandomly = false;
				isFlyingUp = false;
				isFlyingDown = false;
			}
			
			if (isFlyingRandomly) {
				doFly(0);
			} else if (isFlyingUp) {
				doFly(1);
			} else if(isFlyingDown) {
				doFly(2);
			} else {
				doSit();
				return totalSitTime < minimumSitTime; // Restart AI if sat for enough time
			}
			return newDirectionTimer > 0; // Restart AI if timer <= 0
		}
		
		// Flag: 0 for random movement, 1 for up, 2 for down
		public void doFly(int flag) {
			totalSitTime = 0;
			if (newDirectionTimer % 2 == 0) {
				double periodicFactor1 = 0.07D * MathHelper.sin((float)newDirectionTimer/10F);
				double periodicFactor2 = 0.07D * MathHelper.sin((float)newDirectionTimer/10F + (float)Math.PI / 2F);
				switch (flag) {
				case 0: Vec3d dir = targetDirection.add(baseVec1.scale(periodicFactor1)).add(baseVec2.scale(periodicFactor2));
						firefly.moveInDirection(dir);
						break;
				case 1: firefly.moveInDirection(new Vec3d(periodicFactor1, firefly.speed, periodicFactor2));
						break;
				case 2: firefly.moveInDirection(new Vec3d(periodicFactor1, -firefly.speed, periodicFactor2));
						break;
				}
			}
			newDirectionTimer--;
			totalFlightTime++;
		}
		
		public void doSit() {
			totalSitTime++;
			totalFlightTime = 0;
			firefly.moveInDirection(Vec3d.ZERO);
		}
		
		public boolean shouldFlyDown() {
			if (rng.nextFloat() < 0.8) {
				BlockPos pos = firefly.getPosition().down(2);
				Block block = firefly.getEntityWorld().getBlockState(pos).getBlock();
				return block == Blocks.AIR;
			}
			return firefly.isOverWater();
		}
		
		public boolean shouldFlyUp() {
			if (!shouldFlyDown()) {
				return rng.nextFloat() < 0.1 || totalSitTime > minimumSitTime; 
			}
			return false;
		}
		
		public void generateRandomDirectionVectors() {
			
			Vec3d toSpawn = firefly.spawnPos.subtract(firefly.getPositionVector());
			if (toSpawn.lengthSquared() > 20) {
				targetDirection = toSpawn.normalize().scale(firefly.speed);
			} else {
				targetDirection = new Vec3d(
						rng.nextDouble() - 0.5D,
						rng.nextDouble() - 0.2D,
						rng.nextDouble() - 0.5D).scale(firefly.speed * rng.nextDouble());
			}
			baseVec1 = new Vec3d(targetDirection.y, -targetDirection.x, 0D).normalize();
				baseVec2 = targetDirection.crossProduct(baseVec1).normalize();
		}
		
		public boolean isTouchingBlock() {
			return firefly.collided || firefly.isInsideOfMaterial(Material.GRASS);
		}
		
	}

	// Has priority over any other AI task. Causes the firefly to fly downwards and despawn
	// when it hits a block whenever the sun goes up. Uses same type of movement as 
	// AIFlyRandomly
	static class AIFlyToGroundAndDespawn extends EntityAIBase {

		private EntityFirefly firefly;
		private int timer;
		
		public AIFlyToGroundAndDespawn(EntityFirefly firefly) {
			this.firefly = firefly;
			this.setMutexBits(1);
		}
		
		@Override
		public boolean shouldExecute() {
			return firefly.getEntityWorld().isDaytime();
		}
		
		@Override
		public void startExecuting() {
			timer = 0;
		}
		
		@Override
		public boolean shouldContinueExecuting() {
			doFlyDown();
			if (isTouchingBlock()) {
				firefly.setDead();
				return false;
			}
			return true;
		}
		
		public void doFlyDown() {
			if (timer % 2 == 0) {
				double periodicFactor1 = 0.07D * MathHelper.sin((float)timer/10F);
				double periodicFactor2 = 0.07D * MathHelper.sin((float)timer/10F + (float)Math.PI / 2F);
				Vec3d dir = new Vec3d(periodicFactor1, -firefly.speed, periodicFactor2);
				firefly.moveInDirection(dir);
			}
			timer++;
		}
		
		public boolean isTouchingBlock() {
			return firefly.collided || firefly.isInsideOfMaterial(Material.GRASS);
		}
	}
}
