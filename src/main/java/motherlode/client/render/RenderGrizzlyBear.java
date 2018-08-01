package motherlode.client.render;

import javax.annotation.Nullable;

import motherlode.Motherlode;
import motherlode.entity.monster.EntityGrizzlyBear;
import net.minecraft.client.model.ModelPolarBear;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderGrizzlyBear extends RenderLiving<EntityGrizzlyBear> {
	
	private static final ResourceLocation GRIZZLY_BEAR_TEXTURE = new ResourceLocation(Motherlode.MOD_ID, "textures/entity/grizzly_bear.png");

	public ModelPolarBear model;

	public RenderGrizzlyBear(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelPolarBear(), 1.0F);
		this.model = new ModelPolarBear();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityGrizzlyBear entity) {
		return GRIZZLY_BEAR_TEXTURE;
	}

	@Override
	public void doRender(EntityGrizzlyBear entity, double x, double y, double z, float yaw, float elapsedTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(0, -1.2F, 0);
		super.doRender(entity, x, y, z, yaw, elapsedTicks);
		GlStateManager.popMatrix();
	}

	public static class Factory implements IRenderFactory<EntityGrizzlyBear> {

		public static final Factory INSTANCE = new Factory();

		@Override
		public Render<? super EntityGrizzlyBear> createRenderFor(RenderManager manager) {
			return new RenderGrizzlyBear(manager);
		}
	}
}