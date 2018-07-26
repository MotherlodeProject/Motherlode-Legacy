package motherlode.client.render;

import motherlode.Motherlode;
import motherlode.client.model.entity.ModelButterfly;
import motherlode.entity.passive.EntityMoth;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMoth extends RenderLiving<EntityMoth> {
	
	private static ResourceLocation[] TEXTURES = {
			new ResourceLocation(Motherlode.MOD_ID, "textures/entity/butterfly_moth.png"),
	};
	
	public ModelButterfly model;
	
	public RenderMoth(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelButterfly(), 0.0F);
		this.model = new ModelButterfly();
	}
	
	@Override
	public void doRender(EntityMoth moth, double x, double y, double z, float entityYaw, float partialTicks) {
		this.bindTexture(this.getEntityTexture(moth));
		GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.translate(0F, -0.1F + moth.getHeight() / 2.0F, 0F); // Model offset from hitbox
        
        // east = (1, 0, 0)
        // up = (0, 1, 0)
		// south = (0, 0, 1)
        if (moth.getIsSitting()) {
	        switch (moth.getFacing()) {
	        case UP:
	        	GlStateManager.rotate(moth.renderYawOffset, 0.0F, 1.0F, 0.0F);
	        	break;
	        case DOWN:
	        	GlStateManager.rotate(moth.renderYawOffset, 0.0F, 1.0F, 0.0F);
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
        	GlStateManager.rotate(moth.renderYawOffset, 0.0F, 1.0F, 0.0F);
        }
        
        GlStateManager.scale(moth.getScale(), moth.getScale(), moth.getScale());
        GlStateManager.disableRescaleNormal();
        this.model.render(moth, 0F, 0F, 0F, 0F, 0F, 0.1F);
        GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMoth entity) {
		return TEXTURES[entity.getTextureIndex()];
	}
	
	public static class Factory implements IRenderFactory<EntityMoth> {
		
		public static final Factory INSTANCE = new Factory();

		@Override
		public Render<? super EntityMoth> createRenderFor(RenderManager manager) {
			return new RenderMoth(manager);
		}
	}

}
