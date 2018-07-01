package motherlode.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDynamite extends ModelBase {
	
	public ModelRenderer body;
	public ModelRenderer fuse;
	
	public ModelDynamite() {
		this.body = new ModelRenderer(this, 0, 0); //texture offsets
		this.body.addBox(-3f, 0f, -1f, 6, 2, 2); // box coords/dimensions
	}
	
	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		// Call GlStateManager here for rotation, translation etc
		
		this.body.render(scale);
	}
}
