package motherlode.entity.passive;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityButterfly extends EntityFlyingInsect {
	
	private int textureIndex;
	
	public EntityButterfly(World worldIn) {
		super(worldIn);
		// If size is not small enough, butterflies become invalid after a couple of seconds and despawn
		this.setWidth(0.2F);
		this.setHeight(0.2F);
		this.setScale(0.3F);
		this.setSpeed(0.07F);
		this.setupAI();
		this.setTextureIndex(this.getRNG().nextInt(2));
	}
	
	@Override
    public void writeEntityToNBT(NBTTagCompound compound) {
    	super.writeEntityToNBT(compound);
    	compound.setInteger("textureIndex", this.textureIndex);
	}
	
	@Override
    public void readEntityFromNBT(NBTTagCompound compound) {
    	super.readEntityFromNBT(compound);
    	if (compound.hasKey("textureIndex")) {
    		this.textureIndex = compound.getInteger("textureIndex");
    	}
	}
	
	public int getTextureIndex() {
		return this.textureIndex;
	}

	public void setTextureIndex(int textureIndex) {
		this.textureIndex = textureIndex;
	}
	
}
