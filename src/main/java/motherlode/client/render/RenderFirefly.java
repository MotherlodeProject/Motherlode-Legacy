package motherlode.client.render;

import motherlode.Motherlode;
import motherlode.client.model.entity.ModelFirefly;
import motherlode.client.render.layer.LayerFireflyGlow;
import motherlode.entity.passive.EntityFirefly;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFirefly extends RenderLiving<EntityFirefly>
{
	private static final ResourceLocation FIREFLY_TEXTURE = new ResourceLocation(Motherlode.MOD_ID, "textures/entity/firefly.png");
	
	public ModelFirefly model;

    public RenderFirefly(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelFirefly(), 0F);
        this.model = new ModelFirefly();
        this.addLayer(new LayerFireflyGlow(this));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityFirefly entity) {
    	return FIREFLY_TEXTURE;
    }
    
    @Override
	public void doRender(EntityFirefly firefly, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.rotate(firefly.renderYawOffset, 0F, 1F, 0F);
        GlStateManager.scale(firefly.getScale(), firefly.getScale(), firefly.getScale());
        bindTexture(FIREFLY_TEXTURE);
        GlStateManager.color(firefly.getRed(), firefly.getGreen(), firefly.getBlue(), 1.0F);
        
        GlStateManager.disableLighting();
        int combinedBrightness = firefly.getBrightnessForRender(); // Brightness, sort of. Code borrowed from spiders/endermen	
        int skyLightTimes16 = combinedBrightness % 65536;
        int blockLightTimes16 = combinedBrightness / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)skyLightTimes16, (float)blockLightTimes16);
        GlStateManager.enableLighting();
//        Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
        model.render(firefly, 0F, 0F, 0F, 0F, 0F, 0.1F);
//        Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
        this.setLightmap(firefly);
        GlStateManager.enableAlpha();
        
        GlStateManager.popMatrix();
        
        this.layerRenderers.get(0).doRenderLayer(firefly, 0F, 0F, partialTicks, firefly.ticksExisted, 0F, 0F, 1F);
    }

    public static class Factory implements IRenderFactory<EntityFirefly> {
		
		public static final Factory INSTANCE = new Factory();

		@Override
		public Render<? super EntityFirefly> createRenderFor(RenderManager manager) {
			return new RenderFirefly(manager);
		}
	}
}