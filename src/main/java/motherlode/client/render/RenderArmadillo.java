package motherlode.client.render;

import javax.annotation.Nullable;

import motherlode.Motherlode;
import motherlode.client.model.entity.ModelArmadillo;
import motherlode.entity.passive.EntityArmadillo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderArmadillo extends RenderLiving<EntityArmadillo> {
	
	private static final ResourceLocation ARMADILLO_TEXTURE = new ResourceLocation(Motherlode.MOD_ID, "textures/entity/armadillo.png");

	public ModelArmadillo model;

	public RenderArmadillo(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelArmadillo(), 1.0F);
		this.model = new ModelArmadillo();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityArmadillo entity) {
		return ARMADILLO_TEXTURE;
	}

	@Override
	public void doRender(EntityArmadillo entity, double x, double y, double z, float yaw, float elapsedTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(0, -1.2F, 0);
		super.doRender(entity, x, y, z, yaw, elapsedTicks);
		GlStateManager.popMatrix();
	}

	public static class Factory implements IRenderFactory<EntityArmadillo> {

		public static final Factory INSTANCE = new Factory();

		@Override
		public Render<? super EntityArmadillo> createRenderFor(RenderManager manager) {
			return new RenderArmadillo(manager);
		}
	}
}