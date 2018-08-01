package motherlode.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Mole.tcn - TechneToTabulaImporter
 * Created using Tabula 7.0.0
 */
public class ModelMole extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer footFrontRight;
    public ModelRenderer footBackRight;
    public ModelRenderer footFrontLeft;
    public ModelRenderer footBackLeft;
    public ModelRenderer tail;

    public ModelMole() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new ModelRenderer(this, 27, 2);
        this.head.setRotationPoint(0.0F, 1.0F, -5.5F);
        this.head.addBox(-3.0F, -2.5F, -4.0F, 6, 5, 4, 0.0F);
        this.footBackLeft = new ModelRenderer(this, 24, 19);
        this.footBackLeft.mirror = true;
        this.footBackLeft.setRotationPoint(3.0F, 3.0F, 4.0F);
        this.footBackLeft.addBox(0.0F, 0.0F, -5.5333333015441895F, 4, 1, 6, 0.0F);
        this.setRotateAngle(footBackLeft, 0.0F, -0.17453292012214658F, 0.0F);
        this.footFrontRight = new ModelRenderer(this, 0, 18);
        this.footFrontRight.setRotationPoint(-3.0F, 3.0F, -4.0F);
        this.footFrontRight.addBox(-5.0F, 0.0F, -6.5333333015441895F, 5, 1, 7, 0.0F);
        this.tail = new ModelRenderer(this, 5, 8);
        this.tail.setRotationPoint(0.0F, 3.0F, 5.0F);
        this.tail.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
        this.footFrontLeft = new ModelRenderer(this, 0, 18);
        this.footFrontLeft.mirror = true;
        this.footFrontLeft.setRotationPoint(3.0F, 3.0F, -4.0F);
        this.footFrontLeft.addBox(0.0F, 0.0F, -6.5333333015441895F, 5, 1, 7, 0.0F);
        this.footBackRight = new ModelRenderer(this, 24, 19);
        this.footBackRight.setRotationPoint(-3.0F, 3.0F, 4.0F);
        this.footBackRight.addBox(-4.0F, 0.0F, -5.5333333015441895F, 4, 1, 6, 0.0F);
        this.setRotateAngle(footBackRight, 0.0F, 0.17453292012214658F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.addBox(-4.0F, -3.5F, -5.5F, 8, 7, 11, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.head.render(f5);
        this.footBackLeft.render(f5);
        this.footFrontRight.render(f5);
        this.tail.render(f5);
        this.footFrontLeft.render(f5);
        this.footBackRight.render(f5);
        this.body.render(f5);
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
