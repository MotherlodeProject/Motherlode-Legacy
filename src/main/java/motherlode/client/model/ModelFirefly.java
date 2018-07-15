package motherlode.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFirefly extends ModelBase {
    public ModelRenderer base;

    public ModelFirefly() {
    	this.textureHeight = 1;
    	this.textureWidth = 1;
        this.base = new ModelRenderer(this, 0, 0);
        this.base.addBox(-0.5F, 0F, -0.5F, 1, 1, 1, 0F);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) { 
        this.base.render(scale);
    }

}
