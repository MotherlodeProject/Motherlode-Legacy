package motherlode.entity.passive;

import motherlode.entity.ai.AIInsectFlyRandomly;
import motherlode.network.MotherlodeNetwork;
import motherlode.network.packet.PacketEntityFlyingInsectUpdate;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityFlyingInsect extends EntityFlying {

	private float scale;
	private float width;
	private float height;
	private float speed;
	private Vec3d spawnPos;
	private boolean isSitting;
	private EnumFacing facing;
	private int renderDistance;
	
	public EntityFlyingInsect(World worldIn) {
		super(worldIn);
		this.isSitting = false;
		this.facing = EnumFacing.UP;
		this.setupAI();
		this.renderDistance = 2000;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double distance) {
		EntityPlayer player = Minecraft.getMinecraft().player;
		double x = player.posX - this.posX;
		double y = player.posY - this.posY;
		double z = player.posZ - this.posZ;
		
		return x * x + y * y + z * z < this.renderDistance;
	}
	
	@Override
    public void writeEntityToNBT(NBTTagCompound compound) {
    	super.writeEntityToNBT(compound);
    	compound.setBoolean("isSitting", this.isSitting);
    	compound.setInteger("facing", this.facing.ordinal());
	}
	
	@Override
    public void readEntityFromNBT(NBTTagCompound compound) {
    	super.readEntityFromNBT(compound);
    	if (compound.hasKey("isSitting")) {
    		this.isSitting = compound.getBoolean("isSitting");
    	}
    	if (compound.hasKey("facing")) {
    		this.facing = EnumFacing.getFront(compound.getInteger("facing"));
    	}
    	this.updateClients();
	}
	
	public void updateClients() {
		if (!this.world.isRemote) {
			EntityTracker tracker = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(0).getEntityTracker();
			tracker.sendToTracking(this,
					MotherlodeNetwork.networkWrapper.getPacketFrom(
							new PacketEntityFlyingInsectUpdate(this.isSitting, this.facing.ordinal(), this.getEntityId())));
		}
	}
	
	@Override
	public boolean canBePushed() {
		return false;
	}
	
	@Override
	protected boolean canTriggerWalking() {
		return false;
	}
	
	public EnumFacing getFacing() {
		return this.facing;
	}
	
	public void setFacing(int facing) {
		this.facing = EnumFacing.getFront(facing);
	}
	
	public void setFacing(EnumFacing facing) {
		this.facing = facing;
	}
	
	public int getRenderDistance() {
		return renderDistance;
	}

	public void setRenderDistance(int renderDistance) {
		this.renderDistance = renderDistance;
	}
	
	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
		this.setSize(this.scale * this.width, this.scale * this.height);
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
		this.setSize(this.scale * this.width, this.scale * this.height);
	}
	
	public void setIsSitting(boolean b) {
		this.isSitting = b;
	}
	
	public boolean getIsSitting() {
		return this.isSitting;
	}
	
	public void setSpawnPos(Vec3d pos) {
		this.spawnPos = pos;
	}
	
	public Vec3d getSpawnPos() {
		return this.spawnPos;
	}
	
	public void setScale(float scale) {
		this.scale = scale;
		this.setSize(this.scale * this.width, this.scale * this.height);
	}
	
	public float getScale() {
		return this.scale;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public float getSpeed() {
		return this.speed;
	}
	
	public void moveInDirection(Vec3d v) {
		this.rotationYaw = (float)MathHelper.atan2(v.z, v.x) * 180F / (float)Math.PI;
		this.setRenderYawOffset(rotationYaw);
		this.setPositionAndRotation(this.posX, this.posY, this.posZ, rotationYaw, 0.0F);
		
		this.motionX = v.x;
		this.motionY = v.y;
		this.motionZ = v.z;
		this.velocityChanged = true;
	}
    
	public void setupAI() {
		this.tasks.taskEntries.clear();
		this.targetTasks.taskEntries.clear();
		this.tasks.addTask(10, new AIInsectFlyRandomly(this));
	}
	
}
