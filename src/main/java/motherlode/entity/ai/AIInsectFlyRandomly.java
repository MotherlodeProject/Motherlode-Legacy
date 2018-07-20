package motherlode.entity.ai;

import java.util.Random;

import motherlode.entity.passive.EntityFlyingInsect;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

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

public class AIInsectFlyRandomly extends EntityAIBase {

	private EntityFlyingInsect insect;
	private int newDirectionTimer;
	Random rng;
	private Vec3d targetDirection = Vec3d.ZERO;
	private Vec3d baseVec1 = Vec3d.ZERO;
	private Vec3d baseVec2 = Vec3d.ZERO;
	private Vec3d toSpawn = Vec3d.ZERO;
	private int totalSitTime;
	private int minimumSitTime;
	private int totalFlightTime;
	private boolean isFlyingRandomly = true;
	private boolean isFlyingUp = false;
	private boolean isFlyingDown = false;
	
	public AIInsectFlyRandomly(EntityFlyingInsect entity) {
		this.insect = entity;
		this.setMutexBits(1);
		this.rng = insect.getRNG();
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
		if (insect.getIsSitting()) {
			insect.setIsSitting(false);
			insect.updateClients();
		}
		totalSitTime = 0;
		if (newDirectionTimer % 2 == 0) {
			double periodicFactor1 = 0.07 * MathHelper.sin((float)newDirectionTimer / 5.0F);
			double periodicFactor2 = 0.07 * MathHelper.sin(((float)newDirectionTimer + (float)Math.PI / 2F) / 5.0F);
			switch (flag) {
			case 0: Vec3d dir = targetDirection.add(baseVec1.scale(periodicFactor1)).add(baseVec2.scale(periodicFactor2));
					insect.moveInDirection(dir);
//					System.out.println("flying randomly");
					break;
			case 1: insect.moveInDirection(new Vec3d(periodicFactor1, insect.getSpeed(), periodicFactor2));
//					System.out.println("flying up");
					break;
			case 2: insect.moveInDirection(new Vec3d(periodicFactor1, -insect.getSpeed(), periodicFactor2));
//					System.out.println("flying down");
					break;
			}
		}
		newDirectionTimer--;
		totalFlightTime++;
	}
	
	public void doSit() {
		if (!insect.getIsSitting()) {
			insect.setIsSitting(true);
			insect.updateClients();
		}
		totalSitTime++;
		totalFlightTime = 0;
		insect.moveInDirection(Vec3d.ZERO);
	}
	
	public boolean shouldFlyDown() {
		if (!shouldFlyUp()) {
			if (rng.nextFloat() < 0.8) {
				return this.isBlockBeneath(Blocks.AIR, 2, 3);
			}
		}
		return !insect.isOverWater();
	}
	
	public boolean shouldFlyUp() {
		return insect.isOverWater() || totalSitTime > minimumSitTime || rng.nextFloat() < 0.25; 
	}
	
	public void generateRandomDirectionVectors() {
		if (insect.getSpawnPos() != null) {
			toSpawn = insect.getSpawnPos().subtract(insect.getPositionVector());
		}
		
		if (toSpawn.lengthSquared() > 20) {
			targetDirection = toSpawn.normalize().scale(insect.getSpeed());
		} else {
			targetDirection = new Vec3d(
					rng.nextDouble() - 0.5D,
					rng.nextDouble() - 0.2D,
					rng.nextDouble() - 0.5D).scale(insect.getSpeed() * rng.nextDouble());
		}
		baseVec1 = new Vec3d(targetDirection.y, -targetDirection.x, 0D).normalize();
			baseVec2 = targetDirection.crossProduct(baseVec1).normalize();
	}
	
	public boolean isBlockBeneath(Block block, int minDistance, int maxDistance) {
		for (int i=minDistance; i<maxDistance; i++) {
			BlockPos pos = insect.getPosition().down(i);
			if (insect.getEntityWorld().getBlockState(pos).getBlock() == block) {
				return true;
			}
		}
		return false;
	}
	
	// Entity#collidedVertically or conllidedHoriontally don't work well enough
	public boolean isTouchingBlock() {
		// east = (1, 0, 0)
        // up = (0, 1, 0)
		// south = (0, 0, 1)
		
		double d0 = insect.posX - Math.round(insect.posX);
		double d1 = insect.posY - Math.round(insect.posY);
		double d2 = insect.posZ - Math.round(insect.posZ);
		
		if (Math.abs(d0) < insect.getWidth() / 2.0D + 0.01D) {
			if (d0 <= 0.0D) {
				if (insect.world.getBlockState(insect.getPosition().east()).getBlock() != Blocks.AIR) {
					insect.setFacing(EnumFacing.WEST);
					return true;
				}
			} else {
				if (insect.world.getBlockState(insect.getPosition().west()).getBlock() != Blocks.AIR) {
					insect.setFacing(EnumFacing.EAST);
					return true;
				}
			}
		} else if (Math.abs(d1) < insect.getHeight() / 2.0D + 0.01D) {
			if (d1 <= 0.0D) {
				// No BlockPos#up() for this one, after testing ingame
				if (insect.world.getBlockState(insect.getPosition()).getBlock() != Blocks.AIR) {
					insect.setFacing(EnumFacing.DOWN);
					return true;
				}
			} else {
				if (insect.world.getBlockState(insect.getPosition().down()).getBlock() != Blocks.AIR) {
					insect.setFacing(EnumFacing.UP);
					return true;
				}
			}
		} else if (Math.abs(d2) < insect.getWidth() / 2.0D + 0.01D) {
			if (d2 < 0.0D) {
				if (insect.world.getBlockState(insect.getPosition().south()).getBlock() != Blocks.AIR) {
					insect.setFacing(EnumFacing.NORTH);
					return true;
				}
			} else {
				if (insect.world.getBlockState(insect.getPosition().north()).getBlock() != Blocks.AIR) {
					insect.setFacing(EnumFacing.SOUTH);
					return true;
				}
			}
			
		}
		return false;
	}
}
