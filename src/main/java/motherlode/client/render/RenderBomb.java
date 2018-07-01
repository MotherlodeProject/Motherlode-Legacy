package motherlode.client.render;

import motherlode.client.model.ModelBomb;
import motherlode.common.entity.EntityBomb;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBomb extends Render<EntityBomb> {
	
//	protected final Item item;
//    private final RenderItem itemRenderer;
	public ModelBomb model;

//	public RenderBomb(RenderManager renderManagerIn, Item itemIn, RenderItem itemRendererIn) {
	public RenderBomb(RenderManager renderManagerIn) {
		super(renderManagerIn);
//		this.item = itemIn;
//        this.itemRenderer = itemRendererIn;
//		this.item = new ItemBomb();
//		this.itemRenderer = Minecraft.getMinecraft().getRenderItem();
		this.model = new ModelBomb();
		
	}
	
	@Override
	public void doRender(EntityBomb entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.rotate(entity.getRotationYaw(), 0F, 1F, 0F); // does not work?
//        GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F); // angle, vector (x,y,z)
//        GlStateManager.rotate((float)(this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
//        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
//        this.bindTexture(new ResourceLocation("motherlode", "textures/items/item_bomb.png"));
        GlStateManager.disableRescaleNormal();
        this.model.render(entity, 0F, 0F, 0F, 0F, 0F, 0.1F);
        GlStateManager.popMatrix();
//        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
	

	@Override
	protected ResourceLocation getEntityTexture(EntityBomb entity) {
		return new ResourceLocation("motherlode", "textures/items/item_bomb.png");
	}
	
//	public ItemStack getStackToRender(EntityBomb entityIn) {
//        return new ItemStack(this.item);
//    }
	
	public static class Factory implements IRenderFactory<EntityBomb> {
		
		public static final Factory INSTANCE = new Factory();

		@Override
		public Render<? super EntityBomb> createRenderFor(RenderManager manager) {
			return new RenderBomb(manager);
		}
	}
}
