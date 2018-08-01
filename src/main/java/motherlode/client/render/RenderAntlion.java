package motherlode.client.render;

import javax.annotation.Nullable;

import motherlode.Motherlode;
import motherlode.client.model.entity.ModelAntlion;
import motherlode.entity.passive.EntityAntlion;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderAntlion extends RenderLiving<EntityAntlion> {
	
	private static final ResourceLocation ANTLION_TEXTURE = new ResourceLocation(Motherlode.MOD_ID, "textures/entity/antlion.png");

	public ModelAntlion model;

	public RenderAntlion(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelAntlion(), 1.0F);
		this.model = new ModelAntlion();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityAntlion entity) {
		return ANTLION_TEXTURE;
	}

	@Override
	public void doRender(EntityAntlion entity, double x, double y, double z, float yaw, float elapsedTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(0, -1.2F, 0);
		super.doRender(entity, x, y, z, yaw, elapsedTicks);
		GlStateManager.popMatrix();
	}

	public static class Factory implements IRenderFactory<EntityAntlion> {

		public static final Factory INSTANCE = new Factory();

		@Override
		public Render<? super EntityAntlion> createRenderFor(RenderManager manager) {
			return new RenderAntlion(manager);
		}
	}
}