package motherlode.client.render;

import javax.annotation.Nullable;

import motherlode.Motherlode;
import motherlode.client.model.entity.ModelSmallSpider;
import motherlode.entity.monster.EntitySmallSpider;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSmallSpider extends RenderLiving<EntitySmallSpider> {
	
	private static final ResourceLocation SMALL_SPIDER_TEXTURE = new ResourceLocation(Motherlode.MOD_ID, "textures/entity/small_spider.png");

	public ModelSmallSpider model;

	public RenderSmallSpider(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelSmallSpider(), 1.0F);
		this.model = new ModelSmallSpider();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntitySmallSpider entity) {
		return SMALL_SPIDER_TEXTURE;
	}

	@Override
	public void doRender(EntitySmallSpider entity, double x, double y, double z, float yaw, float elapsedTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(0, -1.2F, 0);
		super.doRender(entity, x, y, z, yaw, elapsedTicks);
		GlStateManager.popMatrix();
	}

	public static class Factory implements IRenderFactory<EntitySmallSpider> {

		public static final Factory INSTANCE = new Factory();

		@Override
		public Render<? super EntitySmallSpider> createRenderFor(RenderManager manager) {
			return new RenderSmallSpider(manager);
		}
	}
}