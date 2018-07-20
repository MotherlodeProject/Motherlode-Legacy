package motherlode.client.render;

import motherlode.Motherlode;
import motherlode.client.model.entity.ModelButterfly;
import motherlode.entity.passive.EntityButterfly;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderButterfly extends RenderLiving<EntityButterfly> {
	
	private static final ResourceLocation BUTTERFLY_TEXTURE = new ResourceLocation(Motherlode.MOD_ID, "textures/entity/butterfly.png");
	public ModelButterfly model;
	
	public RenderButterfly(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelButterfly(), 0.0F);
		this.model = new ModelButterfly();
	}
	
	@Override
	public void doRender(EntityButterfly butterfly, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.translate(0F, -0.1F + butterfly.getHeight() / 2.0F, 0F); // Model offset from hitbox
        
        // east = (1, 0, 0)
        // up = (0, 1, 0)
		// south = (0, 0, 1)
        if (butterfly.getIsSitting()) {
	        switch (butterfly.getFacing()) {
	        case UP:
	        	GlStateManager.rotate(butterfly.renderYawOffset, 0.0F, 1.0F, 0.0F);
	        	break;
	        case DOWN:
	        	GlStateManager.rotate(butterfly.renderYawOffset, 0.0F, 1.0F, 0.0F);
	        	GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
	        	break;
	        case NORTH:
	        	GlStateManager.rotate(90.0F, -1.0F, 0.0F, 0.0F);
	        	break;
	        case WEST:
	        	GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
	        	break;
	        case SOUTH:
	        	GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
	        	break;
	        case EAST:
	        	GlStateManager.rotate(90.0F, 1.0F, 0.0F, -1.0F);
	        	break;
	        }
        } else {
        	GlStateManager.rotate(butterfly.renderYawOffset, 0.0F, 1.0F, 0.0F);
        }
        
        GlStateManager.scale(butterfly.getScale(), butterfly.getScale(), butterfly.getScale());
        this.bindTexture(BUTTERFLY_TEXTURE);
        GlStateManager.disableRescaleNormal();
        this.model.render(butterfly, 0F, 0F, 0F, 0F, 0F, 0.1F);
        GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityButterfly entity) {
		return BUTTERFLY_TEXTURE;
	}
	
	public static class Factory implements IRenderFactory<EntityButterfly> {
		
		public static final Factory INSTANCE = new Factory();

		@Override
		public Render<? super EntityButterfly> createRenderFor(RenderManager manager) {
			return new RenderButterfly(manager);
		}
	}

}
