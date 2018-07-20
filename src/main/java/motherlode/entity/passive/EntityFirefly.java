package motherlode.entity.passive;

import javax.annotation.Nullable;

import motherlode.client.particle.ParticleFireflyTail;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityFirefly extends EntityFlyingInsect {

	private float red;
	private float green;
	private float blue;
	
	public EntityFirefly(World worldIn) {
		super(worldIn);
		this.setWidth(0.2F);
		this.setHeight(0.2F);
		this.setScale(0.8F);
		this.setSpeed(0.07F);
		this.setSpawnPos(Vec3d.ZERO);
		this.setRenderDistance(4000);
		this.setupAI();
	}
	
	@Override
	public void setupAI() {
		super.setupAI();
		tasks.addTask(0, new AIFlyToGroundAndDespawn(this));
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
	@Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
		this.setSpawnPos(this.getPositionVector());
		return super.onInitialSpawn(difficulty, livingdata);
	}
	
	@Override
    public boolean getCanSpawnHere() {
		return !this.getEntityWorld().isDaytime() && super.getCanSpawnHere();
    }
	
	@Override
    public void writeEntityToNBT(NBTTagCompound compound) {
    	super.writeEntityToNBT(compound);
    	compound.setTag("spawnPos", this.newDoubleNBTList(this.getSpawnPos().x, this.getSpawnPos().y, this.getSpawnPos().z));
    }
    
	@Override
    public void readEntityFromNBT(NBTTagCompound compound) {
    	super.readEntityFromNBT(compound);
    	if (compound.hasKey("spawnPos") && compound.getTag("spawnPos") != null) {
			NBTTagList tag = (NBTTagList)compound.getTag("spawnPos");
			this.setSpawnPos(new Vec3d(tag.getDoubleAt(0), tag.getDoubleAt(1), tag.getDoubleAt(2)));
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
	
	@Override
    public void onLivingUpdate() {
		if (this.world.isRemote) {
			
			if (this.ticksExisted % 5 == 0) {
				ParticleFireflyTail tailParticle = new ParticleFireflyTail(
						this.world, this.prevPosX, this.prevPosY, this.prevPosZ, 0, 0, 0);
				tailParticle.setRBGColorF(this.red, this.green, this.blue);
				Minecraft.getMinecraft().effectRenderer.addEffect(tailParticle);
			}
		}
		
		super.onLivingUpdate();
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
				Vec3d dir = new Vec3d(periodicFactor1, -firefly.getSpeed(), periodicFactor2);
				firefly.moveInDirection(dir);
			}
			timer++;
		}
		
		public boolean isTouchingBlock() {
			return firefly.collided || firefly.isInsideOfMaterial(Material.GRASS);
		}
	}
}