package motherlode.client.render;

import motherlode.Motherlode;
import motherlode.client.model.entity.ModelLizard;
import motherlode.entity.passive.EntityLizard;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderLizard extends RenderLiving<EntityLizard> {
	private static final ResourceLocation LIZARD_TEXTURE = new ResourceLocation(Motherlode.MOD_ID, "textures/entity/sand_lizard.png");

	public ModelLizard model;

	public RenderLizard(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelLizard(), 0F);
		this.model = new ModelLizard();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityLizard entity) {
		return LIZARD_TEXTURE;
	}

	@Override
	public void doRender(EntityLizard entity, double x, double y, double z, float yaw, float elapsedTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(0, -1.33F, 0);
		super.doRender(entity, x, y, z, yaw, elapsedTicks);
		GlStateManager.popMatrix();
	}

	public static class Factory implements IRenderFactory<EntityLizard> {

		public static final Factory INSTANCE = new Factory();

		@Override
		public Render<? super EntityLizard> createRenderFor(RenderManager manager) {
			return new RenderLizard(manager);
		}
	}
}