package motherlode.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Antlion.tcn - TechneToTabulaImporter
 * Created using Tabula 7.0.0
 */
public class ModelAntlion extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer fangRightTeeth;
    public ModelRenderer fangLeftTeeth;
    public ModelRenderer fangRight;
    public ModelRenderer fangLeft;
    public ModelRenderer legFrontLeft;
    public ModelRenderer legBackLeft;
    public ModelRenderer legFrontLeft_1;
    public ModelRenderer legMiddleRight;
    public ModelRenderer legMiddleLeft;
    public ModelRenderer legBackLeft_1;

    public ModelAntlion() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.legBackLeft_1 = new ModelRenderer(this, 32, 37);
        this.legBackLeft_1.setRotationPoint(4.5F, 1.0F, 6.599999904632568F);
        this.legBackLeft_1.addBox(0.0F, -2.0F, -3.0F, 8, 4, 4, 0.0F);
        this.setRotateAngle(legBackLeft_1, -0.18001508730825802F, -0.30070968689465344F, 0.5509429315715032F);
        this.fangRightTeeth = new ModelRenderer(this, 41, 16);
        this.fangRightTeeth.mirror = true;
        this.fangRightTeeth.setRotationPoint(0.0F, 3.0F, -14.0F);
        this.fangRightTeeth.addBox(0.0F, -2.0F, -12.0F, 2, 4, 9, 0.0F);
        this.setRotateAngle(fangRightTeeth, 0.0F, 0.6108652353286743F, 0.0F);
        this.fangLeft = new ModelRenderer(this, 0, 24);
        this.fangLeft.setRotationPoint(0.0F, 3.0F, -14.0F);
        this.fangLeft.addBox(0.0F, -2.0F, -12.0F, 4, 4, 12, 0.0F);
        this.setRotateAngle(fangLeft, 0.0F, -0.6108652353286743F, 0.0F);
        this.legBackLeft = new ModelRenderer(this, 32, 37);
        this.legBackLeft.mirror = true;
        this.legBackLeft.setRotationPoint(-4.5F, 1.0F, 6.599999904632568F);
        this.legBackLeft.addBox(-8.0F, -2.0F, -3.0F, 8, 4, 4, 0.0F);
        this.setRotateAngle(legBackLeft, -0.18001508730825802F, 0.30070968689465344F, -0.5509429315715032F);
        this.fangRight = new ModelRenderer(this, 0, 24);
        this.fangRight.mirror = true;
        this.fangRight.setRotationPoint(0.0F, 3.0F, -14.0F);
        this.fangRight.addBox(-4.0F, -2.0F, -12.0F, 4, 4, 12, 0.0F);
        this.setRotateAngle(fangRight, 0.0F, 0.6108652353286743F, 0.0F);
        this.legMiddleRight = new ModelRenderer(this, 32, 37);
        this.legMiddleRight.mirror = true;
        this.legMiddleRight.setRotationPoint(-4.5F, 2.0F, 0.6000000238418579F);
        this.legMiddleRight.addBox(-8.0F, -2.0F, -2.0F, 8, 4, 4, 0.0F);
        this.setRotateAngle(legMiddleRight, 0.06310603411310854F, -0.16283315812346877F, -0.3716701112757061F);
        this.legMiddleLeft = new ModelRenderer(this, 32, 37);
        this.legMiddleLeft.setRotationPoint(4.5F, 2.0F, 0.6000000238418579F);
        this.legMiddleLeft.addBox(0.0F, -2.0F, -2.0F, 8, 4, 4, 0.0F);
        this.setRotateAngle(legMiddleLeft, 0.06310603411310854F, 0.16283315812346877F, 0.3716701112757061F);
        this.legFrontLeft = new ModelRenderer(this, 32, 29);
        this.legFrontLeft.mirror = true;
        this.legFrontLeft.setRotationPoint(-4.5F, 3.0F, -5.0F);
        this.legFrontLeft.addBox(-12.0F, -2.0F, -2.0F, 12, 4, 4, 0.0F);
        this.setRotateAngle(legFrontLeft, 0.08079719316286722F, -0.429259658379615F, -0.19215482374011755F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.addBox(-4.5F, -4.5F, -8.0F, 9, 8, 16, 0.0F);
        this.setRotateAngle(body, 0.2602502703666687F, -0.0F, 0.0F);
        this.fangLeftTeeth = new ModelRenderer(this, 41, 16);
        this.fangLeftTeeth.setRotationPoint(0.0F, 3.0F, -14.0F);
        this.fangLeftTeeth.addBox(-2.0F, -2.0F, -12.0F, 2, 4, 9, 0.0F);
        this.setRotateAngle(fangLeftTeeth, 0.0F, -0.6108652381980153F, 0.0F);
        this.head = new ModelRenderer(this, 34, 3);
        this.head.setRotationPoint(0.0F, 3.0F, -7.0F);
        this.head.addBox(-3.5F, -4.0F, -7.0F, 7, 6, 7, 0.0F);
        this.legFrontLeft_1 = new ModelRenderer(this, 32, 29);
        this.legFrontLeft_1.setRotationPoint(4.5F, 3.0F, -5.0F);
        this.legFrontLeft_1.addBox(0.0F, -2.0F, -2.0F, 12, 4, 4, 0.0F);
        this.setRotateAngle(legFrontLeft_1, 0.08079719316286722F, 0.429259658379615F, 0.19215482374011755F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.legBackLeft_1.render(f5);
        this.fangRightTeeth.render(f5);
        this.fangLeft.render(f5);
        this.legBackLeft.render(f5);
        this.fangRight.render(f5);
        this.legMiddleRight.render(f5);
        this.legMiddleLeft.render(f5);
        this.legFrontLeft.render(f5);
        this.body.render(f5);
        this.fangLeftTeeth.render(f5);
        this.head.render(f5);
        this.legFrontLeft_1.render(f5);
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
