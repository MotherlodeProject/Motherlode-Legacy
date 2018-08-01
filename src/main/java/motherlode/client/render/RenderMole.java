package motherlode.client.render;

import javax.annotation.Nullable;

import motherlode.Motherlode;
import motherlode.client.model.entity.ModelMole;
import motherlode.entity.passive.EntityMole;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMole extends RenderLiving<EntityMole> {
	
	private static final ResourceLocation MOLE_TEXTURE = new ResourceLocation(Motherlode.MOD_ID, "textures/entity/mole.png");

	public ModelMole model;

	public RenderMole(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelMole(), 1.0F);
		this.model = new ModelMole();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityMole entity) {
		return MOLE_TEXTURE;
	}

	@Override
	public void doRender(EntityMole entity, double x, double y, double z, float yaw, float elapsedTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(0, -1.2F, 0);
		super.doRender(entity, x, y, z, yaw, elapsedTicks);
		GlStateManager.popMatrix();
	}

	public static class Factory implements IRenderFactory<EntityMole> {

		public static final Factory INSTANCE = new Factory();

		@Override
		public Render<? super EntityMole> createRenderFor(RenderManager manager) {
			return new RenderMole(manager);
		}
	}
}