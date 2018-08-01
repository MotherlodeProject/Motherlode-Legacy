package motherlode.client.render;

import javax.annotation.Nullable;

import motherlode.Motherlode;
import motherlode.client.model.entity.ModelChipmunk;
import motherlode.entity.passive.EntityChipmunk;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderChipmunk extends RenderLiving<EntityChipmunk> {
	
	private static final ResourceLocation CHIPMUNK_TEXTURE = new ResourceLocation(Motherlode.MOD_ID, "textures/entity/chipmunk.png");

	public ModelChipmunk model;

	public RenderChipmunk(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelChipmunk(), 1.0F);
		this.model = new ModelChipmunk();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityChipmunk entity) {
		return CHIPMUNK_TEXTURE;
	}

	@Override
	public void doRender(EntityChipmunk entity, double x, double y, double z, float yaw, float elapsedTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(0, -1.2F, 0);
		super.doRender(entity, x, y, z, yaw, elapsedTicks);
		GlStateManager.popMatrix();
	}

	public static class Factory implements IRenderFactory<EntityChipmunk> {

		public static final Factory INSTANCE = new Factory();

		@Override
		public Render<? super EntityChipmunk> createRenderFor(RenderManager manager) {
			return new RenderChipmunk(manager);
		}
	}
}