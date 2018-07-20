package motherlode.client.model.entity;

import java.util.Random;

import motherlode.entity.passive.EntityButterfly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * Butterfly.tcn - Motherlode
 * Created using Tabula 7.0.0
 */
public class ModelButterfly extends ModelBase {
	
    public ModelRenderer body;
    public ModelRenderer leftWing;
    public ModelRenderer leftWingBottom;
    public ModelRenderer rightWing;
    public ModelRenderer rightWingBottom;
    private int animationOffset;
    private Random rng;

    public ModelButterfly() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.leftWingBottom = new ModelRenderer(this, 4, 0);
        this.leftWingBottom.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftWingBottom.addBox(0.5F, 1.0F, 0.0F, 4, 4, 0, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.addBox(-0.5F, -3.0F, -0.5F, 1, 7, 1, 0.0F);
        this.leftWing = new ModelRenderer(this, 0, 8);
        this.leftWing.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftWing.addBox(0.5F, -4.0F, 0.0F, 5, 5, 0, 0.0F);
        this.rightWing = new ModelRenderer(this, 0, 8);
        this.rightWing.mirror = true;
        this.rightWing.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightWing.addBox(-5.5F, -4.0F, 0.0F, 5, 5, 0, 0.0F);
        this.rightWingBottom = new ModelRenderer(this, 4, 0);
        this.rightWingBottom.mirror = true;
        this.rightWingBottom.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightWingBottom.addBox(-4.5F, 1.0F, 0.0F, 4, 4, 0, 0.0F);
        
        this.body.addChild(this.rightWing);
        this.body.addChild(this.leftWing);
        this.leftWing.addChild(this.leftWingBottom);
        this.rightWing.addChild(this.rightWingBottom);
        
        this.rng = new Random();
        this.animationOffset = rng.nextInt(20);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    	
    	this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        this.body.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks,
    		float netHeadYaw, float headPitch, float scale, Entity entityIn) {
    	
    	EntityButterfly butterfly = (EntityButterfly)entityIn;

    	this.body.rotateAngleX = - (float)Math.PI / 2.0F;
    	this.leftWing.rotateAngleY = this.wingAngle(butterfly);
    	this.rightWing.rotateAngleY = - this.leftWing.rotateAngleY;
    }
    
    public float wingAngle(EntityButterfly butterfly) {
    	if (!butterfly.getIsSitting()) {
	    	int index = (butterfly.ticksExisted + this.animationOffset) % 8;
	    	return MathHelper.sin(index * 2.0F * (float)Math.PI / 7.0F);
    	}
    	return - (float)Math.PI / 2.0F + 0.1F;
    }
}
