package motherlode.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Clam.tcn - TechneToTabulaImporter
 * Created using Tabula 7.0.0
 */
public class ModelClam extends ModelBase {
    public ModelRenderer topDetail;
    public ModelRenderer bottomDetail;
    public ModelRenderer bottomClam;
    public ModelRenderer topClam;

    public ModelClam() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.topDetail = new ModelRenderer(this, 0, 8);
        this.topDetail.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.topDetail.addBox(-4.0F, -6.0F, 0.0F, 8, 6, 1, 0.0F);
        this.setRotateAngle(topDetail, -0.7528668642044067F, -0.0F, 0.0F);
        this.bottomClam = new ModelRenderer(this, 20, 0);
        this.bottomClam.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bottomClam.addBox(-4.0F, -6.0F, 0.0F, 8, 6, 2, 0.0F);
        this.setRotateAngle(bottomClam, -1.5707963705062866F, -0.0F, 0.0F);
        this.bottomDetail = new ModelRenderer(this, 0, 8);
        this.bottomDetail.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bottomDetail.addBox(-4.0F, -6.0F, -1.0F, 8, 6, 1, 0.0F);
        this.setRotateAngle(bottomDetail, -1.5707963705062866F, -0.0F, 0.0F);
        this.topClam = new ModelRenderer(this, 0, 0);
        this.topClam.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.topClam.addBox(-4.0F, -6.0F, -2.0F, 8, 6, 2, 0.0F);
        this.setRotateAngle(topClam, -0.7528668642044067F, -0.0F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.topDetail.render(f5);
        this.bottomClam.render(f5);
        this.bottomDetail.render(f5);
        this.topClam.render(f5);
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
