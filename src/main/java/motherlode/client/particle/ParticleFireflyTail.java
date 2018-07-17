package motherlode.client.particle;

import motherlode.Motherlode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleFireflyTail extends Particle {

	private static ResourceLocation texture = new ResourceLocation(Motherlode.MOD_ID, "entity/firefly");
	
	public ParticleFireflyTail(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn,
			double ySpeedIn, double zSpeedIn) {
		
		super(worldIn, xCoordIn, yCoordIn, zCoordIn);
		this.motionX = xSpeedIn + 0.01 * (this.rand.nextDouble() - 0.5);
		this.motionY = ySpeedIn - 0.01 * this.rand.nextDouble();
		this.motionZ = zSpeedIn + 0.01 * (this.rand.nextDouble() - 0.5);
		this.particleScale = 0.25F;
		this.particleMaxAge = 10;
		
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(texture.toString());
		this.setParticleTexture(sprite);
	}
	
	public static ResourceLocation getTexture() {
		return texture;
	}
	
	@Override
	public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.particleScale *= 0.9;
        
        if (this.particleAge++ >= this.particleMaxAge) {
            this.setExpired();
        }
        
        this.move(this.motionX, this.motionY, this.motionZ);
	}
	
	@Override
    public int getBrightnessForRender(float partialTicks) {
		return 15728800;
	}
	
	// Use the block+item texture sheet onto which we have stitched our sprite in event handler
	@Override
	public int getFXLayer() {
		return 1;
	}
}
