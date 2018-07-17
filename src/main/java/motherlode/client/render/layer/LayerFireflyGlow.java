package motherlode.client.render.layer;

import org.lwjgl.opengl.GL11;

import motherlode.Motherlode;
import motherlode.client.render.RenderFirefly;
import motherlode.entity.passive.EntityFirefly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class LayerFireflyGlow implements LayerRenderer<EntityFirefly> {
	
	private static final ResourceLocation GLOW_TEXTURE = new ResourceLocation(Motherlode.MOD_ID, "textures/particle/particle_glow.png");
	private final RenderFirefly fireflyRenderer;
	private float red;
	private float green;
	private float blue;
	private float alpha;
	private float scale;
	
	public LayerFireflyGlow(RenderFirefly fireflyRenderer) {
		this.fireflyRenderer = fireflyRenderer;
		this.red = 1.0F;
		this.green = 1.0F;
		this.blue = 0.0F;
		this.alpha = 1.0F;
		this.scale = 0.25F;
	}

	@Override
	public void doRenderLayer(EntityFirefly entityIn, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

		float rotationX = ActiveRenderInfo.getRotationX();
    	float rotationXZ = ActiveRenderInfo.getRotationXZ();
    	float rotationZ = ActiveRenderInfo.getRotationZ();
    	float rotationYZ = ActiveRenderInfo.getRotationYZ();
    	float rotationXY = ActiveRenderInfo.getRotationXY();
    	
    	EntityPlayer player = Minecraft.getMinecraft().player;
    	
    	double interpPosX = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double)partialTicks;
        double interpPosY = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double)partialTicks;
        double interpPosZ = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double)partialTicks;
        
        double x = entityIn.prevPosX + (entityIn.posX - entityIn.prevPosX) * (double)partialTicks - interpPosX;
        double y = entityIn.prevPosY + (entityIn.posY - entityIn.prevPosY) * (double)partialTicks - interpPosY;
        double z = entityIn.prevPosZ + (entityIn.posZ - entityIn.prevPosZ) * (double)partialTicks - interpPosZ;
    	
    	int combinedBrightness = entityIn.getBrightnessForRender() / 3;
        int skyLightTimes16 = combinedBrightness >> 16 & 65535;
        int blockLightTimes16 = combinedBrightness & 65535;
    	
    	GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.alphaFunc(516, 0.003921569F);
    	
    	GlStateManager.depthMask(false);
    	
    	Tessellator tessellator = Tessellator.getInstance();
    	BufferBuilder buffer = tessellator.getBuffer();

    	buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
    	this.fireflyRenderer.bindTexture(GLOW_TEXTURE);

    	double minU = 0D;
    	double maxU = 1D;
    	double minV = 0D;
    	double maxV = 1D;
    	
    	buffer.pos(x - rotationX * this.scale - rotationYZ * this.scale,
    			y - rotationXZ * this.scale,
    			z - rotationZ * this.scale - rotationXY * this.scale)
    		.tex(maxU, maxV).color(this.red, this.green, this.blue, this.alpha)
    		.lightmap(skyLightTimes16, blockLightTimes16).endVertex();
    
    	buffer.pos(x - rotationX * this.scale + rotationYZ * this.scale,
    			y + rotationXZ * this.scale,
    			z - rotationZ * this.scale + rotationXY * this.scale)
    		.tex(maxU, minV).color(this.red, this.green, this.blue, this.alpha)
    		.lightmap(skyLightTimes16, blockLightTimes16).endVertex();
    
	    buffer.pos(x + rotationX * this.scale + rotationYZ * this.scale,
	    		y + rotationXZ * this.scale,
	    		z + rotationZ * this.scale + rotationXY * this.scale)
	    	.tex(minU, minV).color(this.red, this.green, this.blue, this.alpha)
	    	.lightmap(skyLightTimes16, blockLightTimes16).endVertex();
    
	    buffer.pos(x + rotationX * this.scale - rotationYZ * this.scale,
	    		y - rotationXZ * this.scale,
	    		z + rotationZ * this.scale - rotationXY * this.scale)
	    	.tex(minU, maxV).color(this.red, this.green, this.blue, this.alpha)
	    	.lightmap(skyLightTimes16, blockLightTimes16).endVertex();
        	
    	tessellator.draw();
    	
    	GlStateManager.disableBlend();
    	GlStateManager.depthMask(true);

	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}

}
