package motherlode.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * Small Spider - Glow
 * Created using Tabula 7.0.0
 */
public class ModelSmallSpider extends ModelBase {
    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer legRight1;
    public ModelRenderer legRight2;
    public ModelRenderer legRight3;
    public ModelRenderer legRight4;
    public ModelRenderer legLeft1;
    public ModelRenderer legLeft2;
    public ModelRenderer legLeft3;
    public ModelRenderer legLeft4;

    public ModelSmallSpider() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new ModelRenderer(this, 0, 8);
        this.head.setRotationPoint(0.0F, 0.0F, -3.0F);
        this.head.addBox(-2.0F, -1.5F, -2.5F, 4, 3, 3, 0.0F);
        this.legLeft4 = new ModelRenderer(this, 15, 0);
        this.legLeft4.setRotationPoint(2.5F, 0.0F, -1.0F);
        this.legLeft4.addBox(0.0F, -0.5F, -0.5F, 6, 1, 1, 0.0F);
        this.setRotateAngle(legLeft4, -0.3687880709464018F, -0.796219204759813F, 0.6869615935849681F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.addBox(-2.5F, -2.1F, -2.5F, 5, 3, 5, 0.0F);
        this.setRotateAngle(body, 0.11868238913561441F, 0.0F, 0.0F);
        this.legRight4 = new ModelRenderer(this, 15, 0);
        this.legRight4.mirror = true;
        this.legRight4.setRotationPoint(-2.5F, 0.0F, -1.0F);
        this.legRight4.addBox(-6.0F, -0.5F, -0.5F, 6, 1, 1, 0.0F);
        this.setRotateAngle(legRight4, -0.3687880709464018F, 0.796219204759813F, -0.6869615935849681F);
        this.legRight3 = new ModelRenderer(this, 15, 2);
        this.legRight3.mirror = true;
        this.legRight3.setRotationPoint(-2.5F, 0.0F, -2.5F);
        this.legRight3.addBox(-5.0F, -0.5F, -0.5F, 5, 1, 1, 0.0F);
        this.setRotateAngle(legRight3, 0.0F, -0.20559978588493202F, -0.6330309196983434F);
        this.legRight2 = new ModelRenderer(this, 15, 2);
        this.legRight2.mirror = true;
        this.legRight2.setRotationPoint(-2.5F, 0.0F, -1.7F);
        this.legRight2.addBox(-5.0F, -0.5F, -0.5F, 5, 1, 1, 0.0F);
        this.setRotateAngle(legRight2, 0.3687880709464018F, 0.44209189953016365F, -0.6869615935849681F);
        this.legRight1 = new ModelRenderer(this, 15, 0);
        this.legRight1.mirror = true;
        this.legRight1.setRotationPoint(-2.5F, 0.0F, -2.5F);
        this.legRight1.addBox(-6.0F, -0.5F, -0.5F, 6, 1, 1, 0.0F);
        this.setRotateAngle(legRight1, 0.0F, -0.7723081940074907F, -0.8939576428714956F);
        this.legLeft1 = new ModelRenderer(this, 15, 0);
        this.legLeft1.setRotationPoint(2.5F, 0.0F, -2.5F);
        this.legLeft1.addBox(0.0F, -0.5F, -0.5F, 6, 1, 1, 0.0F);
        this.setRotateAngle(legLeft1, 0.0F, 0.7723081940074907F, 0.8939576428714956F);
        this.legLeft2 = new ModelRenderer(this, 15, 2);
        this.legLeft2.setRotationPoint(2.5F, 0.0F, -2.5F);
        this.legLeft2.addBox(0.0F, -0.5F, -0.5F, 5, 1, 1, 0.0F);
        this.setRotateAngle(legLeft2, 0.0F, 0.20559978588493202F, 0.6330309196983434F);
        this.legLeft3 = new ModelRenderer(this, 15, 2);
        this.legLeft3.setRotationPoint(2.5F, 0.0F, -1.7F);
        this.legLeft3.addBox(0.0F, -0.5F, -0.5F, 5, 1, 1, 0.0F);
        this.setRotateAngle(legLeft3, -0.3687880709464018F, -0.44209189953016365F, 0.6869615935849681F);
        
        this.body.addChild(head);
        this.body.addChild(legLeft1);
        this.body.addChild(legLeft2);
        this.body.addChild(legLeft3);
        this.body.addChild(legLeft4);
        this.body.addChild(legRight1);
        this.body.addChild(legRight2);
        this.body.addChild(legRight3);
        this.body.addChild(legRight4);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    	this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
//        this.Head.render(f5);
//        this.Leg_left4.render(f5);
        this.body.render(scale);
//        this.Leg_right4.render(f5);
//        this.Leg_right3.render(f5);
//        this.Leg_right3_1.render(f5);
//        this.Leg_right1.render(f5);
//        this.Leg_left1.render(f5);
//        this.Leg_left2.render(f5);
//        this.Leg_left3.render(f5);
    }
    
    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
    	this.body.rotateAngleX += 3.1415F;
    	this.body.rotateAngleY = 3.1415F;
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.head.rotateAngleX = headPitch * 0.017453292F;
//        float f = ((float)Math.PI / 4F);
        this.legLeft1.rotateAngleZ = -((float)Math.PI / 4F);
        this.legRight1.rotateAngleZ = ((float)Math.PI / 4F);
        this.legLeft2.rotateAngleZ = -0.58119464F;
        this.legRight2.rotateAngleZ = 0.58119464F;
        this.legLeft3.rotateAngleZ = -0.58119464F;
        this.legRight3.rotateAngleZ = 0.58119464F;
        this.legLeft4.rotateAngleZ = -((float)Math.PI / 4F);
        this.legRight4.rotateAngleZ = ((float)Math.PI / 4F);
//        float f1 = -0.0F;
//        float f2 = 0.3926991F;
        this.legLeft1.rotateAngleY = ((float)Math.PI / 4F);
        this.legRight1.rotateAngleY = -((float)Math.PI / 4F);
        this.legLeft2.rotateAngleY = 0.3926991F;
        this.legRight2.rotateAngleY = -0.3926991F;
        this.legLeft3.rotateAngleY = -0.3926991F;
        this.legRight3.rotateAngleY = 0.3926991F;
        this.legLeft4.rotateAngleY = -((float)Math.PI / 4F);
        this.legRight4.rotateAngleY = ((float)Math.PI / 4F);
        float f3 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * limbSwingAmount;
        float f4 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + (float)Math.PI) * 0.4F) * limbSwingAmount;
        float f5 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + ((float)Math.PI / 2F)) * 0.4F) * limbSwingAmount;
        float f6 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + ((float)Math.PI * 3F / 2F)) * 0.4F) * limbSwingAmount;
        float f7 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * limbSwingAmount;
        float f8 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float)Math.PI) * 0.4F) * limbSwingAmount;
        float f9 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + ((float)Math.PI / 2F)) * 0.4F) * limbSwingAmount;
        float f10 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + ((float)Math.PI * 3F / 2F)) * 0.4F) * limbSwingAmount;
        this.legLeft1.rotateAngleY += f3;
        this.legRight1.rotateAngleY += -f3;
        this.legLeft2.rotateAngleY += f4;
        this.legRight2.rotateAngleY += -f4;
        this.legLeft3.rotateAngleY += f5;
        this.legRight3.rotateAngleY += -f5;
        this.legLeft4.rotateAngleY += f6;
        this.legRight4.rotateAngleY += -f6;
        this.legLeft1.rotateAngleZ += f7;
        this.legRight1.rotateAngleZ += -f7;
        this.legLeft2.rotateAngleZ += f8;
        this.legRight2.rotateAngleZ += -f8;
        this.legLeft3.rotateAngleZ += f9;
        this.legRight3.rotateAngleZ += -f9;
        this.legLeft4.rotateAngleZ += f10;
        this.legRight4.rotateAngleZ += -f10;
    }
}
