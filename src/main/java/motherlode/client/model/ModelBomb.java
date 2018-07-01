package motherlode.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBomb extends ModelBase {
	
	public ModelRenderer body;
	public ModelRenderer top;
	public ModelRenderer fuse;
	
	public ModelBomb() {
		this.body = new ModelRenderer(this, 0, 0); //texture offsets
		this.body.addBox(-3f, 0f, -3f, 6, 6, 6); // box coords/dimensions
		this.top = new ModelRenderer(this, 0, 0);
		this.top.addBox(-1f, 6f, -1f, 2, 2, 2);
		this.fuse = new ModelRenderer(this, 0, 0);
		this.fuse.addBox(-0.5f, 8, -0.5f, 2, 3, 2);
	}
	
	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		// Call GlStateManager here for rotation, translation etc
		
		this.body.render(scale);
		this.top.render(scale);
		this.fuse.render(scale);
	}

}
