package motherlode.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Armadillo - Glow
 * Created using Tabula 7.0.0
 */
public class ModelArmadillo extends ModelBase {
    public ModelRenderer tail;
    public ModelRenderer legBackLeft;
    public ModelRenderer earRight;
    public ModelRenderer bodyBack;
    public ModelRenderer head;
    public ModelRenderer earLeft;
    public ModelRenderer mainBody;
    public ModelRenderer legFrontLeft;
    public ModelRenderer legBackLeft_1;
    public ModelRenderer legFrontLeft_1;

    public ModelArmadillo() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new ModelRenderer(this, 0, 16);
        this.head.setRotationPoint(0.0F, 2.1F, -4.0F);
        this.head.addBox(-2.0F, -3.0F, -5.0F, 4, 4, 5, 0.0F);
        this.legFrontLeft = new ModelRenderer(this, 0, 0);
        this.legFrontLeft.mirror = true;
        this.legFrontLeft.setRotationPoint(2.8F, 2.5F, -2.0F);
        this.legFrontLeft.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.legFrontLeft_1 = new ModelRenderer(this, 0, 0);
        this.legFrontLeft_1.setRotationPoint(-2.8F, 2.5F, -2.0F);
        this.legFrontLeft_1.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.earRight = new ModelRenderer(this, 0, 16);
        this.earRight.mirror = true;
        this.earRight.setRotationPoint(0.0F, 2.1F, -4.0F);
        this.earRight.addBox(0.4F, -4.8F, -1.0F, 2, 2, 0, 0.0F);
        this.setRotateAngle(earRight, 0.0F, 0.0F, 0.1706932008450454F);
        this.tail = new ModelRenderer(this, 1, 25);
        this.tail.setRotationPoint(0.0F, 2.5F, 10.5F);
        this.tail.addBox(-0.5F, -0.5F, 0.3F, 1, 1, 4, 0.0F);
        this.mainBody = new ModelRenderer(this, 0, 0);
        this.mainBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.mainBody.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);
        this.bodyBack = new ModelRenderer(this, 11, 18);
        this.bodyBack.setRotationPoint(0.0F, 0.5F, 3.5F);
        this.bodyBack.addBox(-3.5F, -3.5F, 0.3F, 7, 7, 7, 0.0F);
        this.legBackLeft = new ModelRenderer(this, 0, 0);
        this.legBackLeft.mirror = true;
        this.legBackLeft.setRotationPoint(2.3F, 2.5F, 6.5F);
        this.legBackLeft.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.legBackLeft_1 = new ModelRenderer(this, 0, 0);
        this.legBackLeft_1.setRotationPoint(-2.7F, 2.5F, 6.5F);
        this.legBackLeft_1.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.earLeft = new ModelRenderer(this, 0, 16);
        this.earLeft.setRotationPoint(0.0F, 2.1F, -4.0F);
        this.earLeft.addBox(-2.4F, -4.8F, -1.0F, 2, 2, 0, 0.0F);
        this.setRotateAngle(earLeft, 0.0F, 0.0F, -0.1706932008450454F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.head.render(f5);
        this.legFrontLeft.render(f5);
        this.legFrontLeft_1.render(f5);
        this.earRight.render(f5);
        this.tail.render(f5);
        this.mainBody.render(f5);
        this.bodyBack.render(f5);
        this.legBackLeft.render(f5);
        this.legBackLeft_1.render(f5);
        this.earLeft.render(f5);
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
