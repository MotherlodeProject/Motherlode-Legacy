package motherlode.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * Lizard - Glow
 * Created using Tabula 7.0.0
 */
public class ModelLizard extends ModelBase {
	public ModelRenderer rightEye;
	public ModelRenderer tail;
	public ModelRenderer head;
	public ModelRenderer leftEye;
	public ModelRenderer leftFrontLeg;
	public ModelRenderer body;
	public ModelRenderer leftBackLeg;
	public ModelRenderer rightFrontLeg;
	public ModelRenderer rightBackLeg;

	public ModelLizard() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.rightBackLeg = new ModelRenderer(this, 0, 10);
		this.rightBackLeg.mirror = true;
		this.rightBackLeg.setRotationPoint(-2.0F, 0.7F, 1.8F);
		this.rightBackLeg.addBox(-2.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F);
		this.setRotateAngle(rightBackLeg, 0.0F, 0.48624872960562016F, -0.7321656212116213F);
		this.tail = new ModelRenderer(this, 14, 0);
		this.tail.setRotationPoint(0.0F, 0.0F, 3.0F);
		this.tail.addBox(-1.0F, -0.6F, -0.3F, 2, 1, 4, 0.0F);
		this.setRotateAngle(tail, -0.1710422666954443F, 0.0F, 0.0F);
		this.leftFrontLeg = new ModelRenderer(this, 0, 8);
		this.leftFrontLeg.setRotationPoint(2.0F, 0.7F, -2.0F);
		this.leftFrontLeg.addBox(-0.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F);
		this.setRotateAngle(leftFrontLeg, 0.0F, 0.41643555952584704F, 0.8379325738824775F);
		this.leftEye = new ModelRenderer(this, 0, 0);
		this.leftEye.setRotationPoint(0.0F, 0.0F, -3.0F);
		this.leftEye.addBox(0.8F, -1.3F, -2.3F, 1, 1, 1, 0.0F);
		this.setRotateAngle(leftEye, 0.21101030656611444F, 0.0F, 0.0F);
		this.body = new ModelRenderer(this, 0, 0);
		this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body.addBox(-2.0F, -1.0F, -3.0F, 4, 2, 6, 0.0F);
		this.head = new ModelRenderer(this, 4, 8);
		this.head.setRotationPoint(0.0F, 0.0F, -3.0F);
		this.head.addBox(-1.5F, -1.5F, -3.2F, 3, 2, 4, 0.0F);
		this.setRotateAngle(head, 0.21101030656611444F, 0.0F, 0.0F);
		this.rightEye = new ModelRenderer(this, 0, 0);
		this.rightEye.mirror = true;
		this.rightEye.setRotationPoint(0.0F, 0.0F, -3.0F);
		this.rightEye.addBox(-1.7F, -1.3F, -2.3F, 1, 1, 1, 0.0F);
		this.setRotateAngle(rightEye, 0.21101030656611444F, 0.0F, 0.0F);
		this.leftBackLeg = new ModelRenderer(this, 0, 10);
		this.leftBackLeg.setRotationPoint(2.0F, 0.7F, 1.8F);
		this.leftBackLeg.addBox(-0.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F);
		this.setRotateAngle(leftBackLeg, 0.0F, -0.48624872960562016F, 0.7321656212116213F);
		this.rightFrontLeg = new ModelRenderer(this, 0, 8);
		this.rightFrontLeg.mirror = true;
		this.rightFrontLeg.setRotationPoint(-2.0F, 0.7F, -2.0F);
		this.rightFrontLeg.addBox(-2.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F);
		this.setRotateAngle(rightFrontLeg, 0.0F, -0.41643555952584704F, -0.8379325738824775F);
	}

	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		this.rightBackLeg.render(scale);
		this.tail.render(scale);
		this.leftFrontLeg.render(scale);
		this.leftEye.render(scale);
		this.body.render(scale);
		this.head.render(scale);
		this.rightEye.render(scale);
		this.leftBackLeg.render(scale);
		this.rightFrontLeg.render(scale);
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
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		this.head.rotateAngleY = netHeadYaw * 0.017453292F;
		this.head.rotateAngleX = headPitch * 0.017453292F;
		this.rightEye.rotateAngleY = netHeadYaw * 0.017453292F;
		this.rightEye.rotateAngleX = headPitch * 0.017453292F;
		this.leftEye.rotateAngleY = netHeadYaw * 0.017453292F;
		this.leftEye.rotateAngleX = headPitch * 0.017453292F;
		this.rightBackLeg.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightFrontLeg.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.leftBackLeg.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.leftFrontLeg.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}
}
