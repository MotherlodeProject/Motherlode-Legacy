package motherlode.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Chipmunk - Glow
 * Created using Tabula 7.0.0
 */
public class ModelChipmunk extends ModelBase {
    public ModelRenderer tailBase;
    public ModelRenderer bodyBase;
    public ModelRenderer headBase;
    public ModelRenderer legFrontRight;
    public ModelRenderer legBackRight;
    public ModelRenderer legFrontLeft;
    public ModelRenderer legBackLeft;
    public ModelRenderer tailBack;
    public ModelRenderer bodyFront;
    public ModelRenderer earLeft;
    public ModelRenderer earRight;
    public ModelRenderer mouth;

    public ModelChipmunk() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.tailBase = new ModelRenderer(this, 10, 14);
        this.tailBase.setRotationPoint(0.0F, 0.1F, 1.7F);
        this.tailBase.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(tailBase, 0.21439913175616385F, 0.0F, 0.0F);
        this.bodyBase = new ModelRenderer(this, 0, 6);
        this.bodyBase.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bodyBase.addBox(-2.0F, -2.0F, -2.0F, 4, 4, 4, 0.0F);
        this.legBackLeft = new ModelRenderer(this, 22, 0);
        this.legBackLeft.setRotationPoint(-1.6F, 1.2F, 1.7F);
        this.legBackLeft.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
        this.tailBack = new ModelRenderer(this, 12, 0);
        this.tailBack.setRotationPoint(0.0F, 0.5F, 1.5F);
        this.tailBack.addBox(-1.5F, -2.1F, -0.4F, 3, 3, 4, 0.0F);
        this.legBackRight = new ModelRenderer(this, 22, 0);
        this.legBackRight.mirror = true;
        this.legBackRight.setRotationPoint(1.6F, 1.2F, 1.7F);
        this.legBackRight.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
        this.mouth = new ModelRenderer(this, 12, 7);
        this.mouth.setRotationPoint(0.0F, 0.0F, -4.0F);
        this.mouth.addBox(-2.0F, 0.45F, 0.8F, 4, 1, 2, 0.0F);
        this.legFrontLeft = new ModelRenderer(this, 22, 0);
        this.legFrontLeft.setRotationPoint(-1.2F, 1.1F, -3.3F);
        this.legFrontLeft.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
        this.earLeft = new ModelRenderer(this, 10, 0);
        this.earLeft.setRotationPoint(0.0F, 0.0F, -4.0F);
        this.earLeft.addBox(-1.5F, -2.6F, 3.3F, 1, 1, 1, 0.0F);
        this.headBase = new ModelRenderer(this, 0, 0);
        this.headBase.setRotationPoint(0.0F, -0.2F, -4.3F);
        this.headBase.addBox(-1.5F, -1.6F, -2.7F, 3, 3, 3, 0.0F);
        this.legFrontRight = new ModelRenderer(this, 22, 0);
        this.legFrontRight.mirror = true;
        this.legFrontRight.setRotationPoint(1.2F, 1.1F, -3.3F);
        this.legFrontRight.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
        this.bodyFront = new ModelRenderer(this, 0, 14);
        this.bodyFront.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bodyFront.addBox(-1.5F, -1.5F, -4.0F, 3, 3, 2, 0.0F);
        this.earRight = new ModelRenderer(this, 10, 0);
        this.earRight.mirror = true;
        this.earRight.setRotationPoint(0.0F, 0.0F, -4.0F);
        this.earRight.addBox(0.5F, -2.6F, 3.3F, 1, 1, 1, 0.0F);
        this.tailBase.addChild(this.tailBack);
        this.headBase.addChild(this.mouth);
        this.headBase.addChild(this.earLeft);
        this.bodyBase.addChild(this.bodyFront);
        this.headBase.addChild(this.earRight);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.tailBase.render(f5);
        this.bodyBase.render(f5);
        this.legBackLeft.render(f5);
        this.legBackRight.render(f5);
        this.legFrontLeft.render(f5);
        this.headBase.render(f5);
        this.legFrontRight.render(f5);
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
