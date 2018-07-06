package motherlode.client.render;

import motherlode.client.model.ModelBomb;
import motherlode.entity.EntityBomb;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBomb extends Render<EntityBomb> {
	
	public ModelBomb model;

	public RenderBomb(RenderManager renderManagerIn) {
		super(renderManagerIn);
		this.model = new ModelBomb();
	}
	
	@Override
	public void doRender(EntityBomb entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.rotate(entity.getRotationYaw(), 0F, 1F, 0F); // does not work?
        GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F); // Model exported upside-down from Tabula
        GlStateManager.translate(0F, -entity.getHeight()/2, 0F); // Model offset from hitbox
        GlStateManager.scale(entity.getScale(), entity.getScale(), entity.getScale());
        this.bindTexture(new ResourceLocation("motherlode", "textures/model/bomb.png"));
        GlStateManager.disableRescaleNormal();
        this.model.render(entity, 0F, 0F, 0F, 0F, 0F, 0.1F);
        GlStateManager.popMatrix();
    }

	@Override
	protected ResourceLocation getEntityTexture(EntityBomb entity) {
		return new ResourceLocation("motherlode", "textures/item/item_bomb_0.png");
	}
	
	public static class Factory implements IRenderFactory<EntityBomb> {
		
		public static final Factory INSTANCE = new Factory();

		@Override
		public Render<? super EntityBomb> createRenderFor(RenderManager manager) {
			return new RenderBomb(manager);
		}
	}
}
