package motherlode.client.render;

import javax.annotation.Nullable;

import motherlode.Motherlode;
import motherlode.client.model.entity.ModelClam;
import motherlode.entity.passive.EntityClam;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderClam extends RenderLiving<EntityClam> {
	
	private static final ResourceLocation CLAM_TEXTURE = new ResourceLocation(Motherlode.MOD_ID, "textures/entity/clam.png");

	public ModelClam model;

	public RenderClam(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelClam(), 1.0F);
		this.model = new ModelClam();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityClam entity) {
		return CLAM_TEXTURE;
	}

	@Override
	public void doRender(EntityClam entity, double x, double y, double z, float yaw, float elapsedTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(0, -1.2F, 0);
		super.doRender(entity, x, y, z, yaw, elapsedTicks);
		GlStateManager.popMatrix();
	}

	public static class Factory implements IRenderFactory<EntityClam> {

		public static final Factory INSTANCE = new Factory();

		@Override
		public Render<? super EntityClam> createRenderFor(RenderManager manager) {
			return new RenderClam(manager);
		}
	}
}