package motherlode.client.render;

import motherlode.client.model.ModelDynamite;
import motherlode.entity.EntityDynamite;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDynamite extends Render<EntityDynamite> {
	
	public ModelDynamite model;

	public RenderDynamite(RenderManager renderManagerIn) {
		super(renderManagerIn);
		this.model = new ModelDynamite();
	}
	
	@Override
	public void doRender(EntityDynamite entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.rotate(entity.getRotationYaw(), 0F, 1F, 0F); // does not work?
//        GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F); // angle, vector (x,y,z)
//        GlStateManager.rotate((float)(this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
//        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
//        this.bindTexture(new ResourceLocation("motherlode", "textures/items/item_Dynamite.png"));
        GlStateManager.disableRescaleNormal();
        this.model.render(entity, 0F, 0F, 0F, 0F, 0F, 0.1F);
        GlStateManager.popMatrix();
//        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
	

	@Override
	protected ResourceLocation getEntityTexture(EntityDynamite entity) {
		return new ResourceLocation("motherlode", "textures/item/item_dynamite_0.png");
	}
	
	public static class Factory implements IRenderFactory<EntityDynamite> {
		
		public static final Factory INSTANCE = new Factory();

		@Override
		public Render<? super EntityDynamite> createRenderFor(RenderManager manager) {
			return new RenderDynamite(manager);
		}
	}
}
