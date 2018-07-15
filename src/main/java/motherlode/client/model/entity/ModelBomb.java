package motherlode.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Throwable_Bomb - Glow
 * Created using Tabula 7.0.0
 */
public class ModelBomb extends ModelBase {
    public ModelRenderer base;
    public ModelRenderer top;
    public ModelRenderer fuse;

    public ModelBomb() {
        this.textureWidth = 32;
        this.textureHeight = 16;
        this.fuse = new ModelRenderer(this, 0, 0);
        this.fuse.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.fuse.addBox(-0.5F, -8.0F, 0.0F, 1, 4, 0, 0.0F);
        this.top = new ModelRenderer(this, 18, 0);
        this.top.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.top.addBox(-1.5F, -4.0F, -1.5F, 3, 1, 3, 0.0F);
        this.base = new ModelRenderer(this, 0, 0);
        this.base.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.base.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.fuse.render(f5);
        this.top.render(f5);
        this.base.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
