package contrivitive.util;

import contrivitive.gui.IContrivitiveGui;
import contrivitive.gui.element.sprite.Sprites;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;
import java.util.List;

@SideOnly(Side.CLIENT)
public class RenderUtils {

	public static void setTextureSheet(ResourceLocation textureLocation) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(textureLocation);
	}

	public static void drawDefaultGuiBackground(IContrivitiveGui gui) {
		int x = gui.getStartingX();
		int y = gui.getStartingY();
		int width = gui.getWidth();
		int height = gui.getHeight();
		//Corners
		drawRectFromSheetNoAdjust(gui, Sprites.BACKGROUND, x, y, 0, 0, 4, 4); //Top left
		drawRectFromSheetNoAdjust(gui, Sprites.BACKGROUND, x + width - 4, y, 7, 0, 4, 4); //Top right
		drawRectFromSheetNoAdjust(gui, Sprites.BACKGROUND, x, y + height - 4, 0, 7, 4, 4); //Bottom left
		drawRectFromSheetNoAdjust(gui, Sprites.BACKGROUND, x + width - 4, y + height - 4, 7, 7, 4, 4); //Bottom right

		//Sides
		drawScaledBackgroundBit(gui, x + 4, y, width - 8, 1, 5, 0, 1, 4); // Top
		drawScaledBackgroundBit(gui, x, y + 4, 1, height - 8, 0, 5, 4, 1); // Left
		drawScaledBackgroundBit(gui, x + width - 4, y + 4, 1, height - 8, 7, 5, 4, 1); // Right
		drawScaledBackgroundBit(gui, x + 4, y + height - 4, width - 8, 1, 5, 7, 1, 4); // Bottom

		//Center
		drawScaledBackgroundBit(gui, x + 4, y + 4, width - 8, height - 8, 5, 5, 1, 1);
	}

	private static void drawScaledBackgroundBit(IContrivitiveGui gui, int x, int y, int widthScale, int heightScale, int textureX, int textureY, int textureWidth, int textureHeight) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, 0);
		GlStateManager.scale(widthScale, heightScale, 1);
		drawRectFromSheetNoAdjust(gui, Sprites.BACKGROUND, 0, 0, textureX, textureY, textureWidth, textureHeight);
		GlStateManager.popMatrix();
	}

	public static void drawEntityFacingMouse(IContrivitiveGui gui, int x, int y, int scale, int mouseX, int mouseY, EntityLivingBase entity, int rotation) {
		x = adjustX(gui, x);
		y = adjustY(gui, y);
		drawEntityOnScreen(x, y, scale, (float) x - mouseX, (float) y - (float) (50 / 30) * scale - mouseY, entity, rotation);
	}

	public static void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY, EntityLivingBase entity, int rotation) {
		GlStateManager.enableColorMaterial();
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) posX, (float) posY, 50.0F);
		GlStateManager.scale((float) (-scale), (float) scale, (float) scale);
		GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
		float f = entity.renderYawOffset;
		float f1 = entity.rotationYaw;
		float f2 = entity.rotationPitch;
		float f3 = entity.prevRotationYawHead;
		float f4 = entity.rotationYawHead;
		GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(-((float) Math.atan((double) (mouseY / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
		entity.renderYawOffset = (float) Math.atan((double) (mouseX / 40.0F)) * 20.0F;
		entity.rotationYaw = (float) Math.atan((double) (mouseX / 40.0F)) * 40.0F;
		entity.rotationPitch = -((float) Math.atan((double) (mouseY / 40.0F))) * 20.0F;
		entity.rotationYawHead = entity.rotationYaw;
		entity.prevRotationYawHead = entity.rotationYaw;
		GlStateManager.translate(0.0F, 0.0F, 0.0F);
		GlStateManager.rotate(rotation, 0, 1, 0);
		RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
		rendermanager.setPlayerViewY(180.0F);
		rendermanager.setRenderShadow(false);
		rendermanager.renderEntity(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
		rendermanager.setRenderShadow(true);
		entity.renderYawOffset = f;
		entity.rotationYaw = f1;
		entity.rotationPitch = f2;
		entity.prevRotationYawHead = f3;
		entity.rotationYawHead = f4;
		GlStateManager.popMatrix();
		RenderHelper.disableStandardItemLighting();
		GlStateManager.disableRescaleNormal();
		GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GlStateManager.disableTexture2D();
		GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}

	public static void drawRectFromSheet(IContrivitiveGui gui, ResourceLocation sheet, int posX, int posY, int textureX, int textureY, int width, int height) {
		setTextureSheet(sheet);
		gui.getGui().drawTexturedModalRect(posX + gui.getStartingX(), posY + gui.getStartingY(), textureX, textureY, width, height);
	}

	public static void drawRectFromSheetNoAdjust(IContrivitiveGui gui, ResourceLocation sheet, int posX, int posY, int textureX, int textureY, int width, int height) {
		setTextureSheet(sheet);
		gui.getGui().drawTexturedModalRect(posX, posY, textureX, textureY, width, height);
	}

	public static void drawSimpleTooltip(IContrivitiveGui gui, int mouseX, int mouseY, List<String> lines) {
		GuiUtils.drawHoveringText(lines, mouseX, mouseY, gui.getWidth(), gui.getHeight(), -1, Minecraft.getMinecraft().fontRenderer);
		GlStateManager.disableLighting();
		GlStateManager.color(1, 1, 1, 1);
	}

	public static void drawSimpleTooltip(IContrivitiveGui gui, int mouseX, int mouseY, String... lines) {
		drawSimpleTooltip(gui, mouseX, mouseY, Arrays.asList(lines));
	}

	public static boolean isInRect(IContrivitiveGui gui, int x, int y, int xSize, int ySize, int mouseX, int mouseY) {
		x = adjustX(gui, x);
		y = adjustY(gui, y);
		return ((mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize));
	}

	public static void drawGradientRect(IContrivitiveGui gui, int x, int y, int width, int height, int startColor, int endColor) {
		x = adjustX(gui, x);
		y = adjustY(gui, y);

		int left = x;
		int top = y;
		int right = x + width;
		int bottom = y + height;
		float f = (float) (startColor >> 24 & 255) / 255.0F;
		float f1 = (float) (startColor >> 16 & 255) / 255.0F;
		float f2 = (float) (startColor >> 8 & 255) / 255.0F;
		float f3 = (float) (startColor & 255) / 255.0F;
		float f4 = (float) (endColor >> 24 & 255) / 255.0F;
		float f5 = (float) (endColor >> 16 & 255) / 255.0F;
		float f6 = (float) (endColor >> 8 & 255) / 255.0F;
		float f7 = (float) (endColor & 255) / 255.0F;
		GlStateManager.disableTexture2D();
		GlStateManager.enableBlend();
		GlStateManager.disableAlpha();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		GlStateManager.shadeModel(7425);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder vertexbuffer = tessellator.getBuffer();
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
		vertexbuffer.pos((double) right, (double) top, (double) 0).color(f1, f2, f3, f).endVertex();
		vertexbuffer.pos((double) left, (double) top, (double) 0).color(f1, f2, f3, f).endVertex();
		vertexbuffer.pos((double) left, (double) bottom, (double) 0).color(f5, f6, f7, f4).endVertex();
		vertexbuffer.pos((double) right, (double) bottom, (double) 0).color(f5, f6, f7, f4).endVertex();
		tessellator.draw();
		GlStateManager.shadeModel(7424);
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.enableTexture2D();
	}

	public static int adjustX(IContrivitiveGui gui, int x) {
		return gui.getStartingX() + x;
	}

	public static int adjustY(IContrivitiveGui gui, int y) {
		return gui.getStartingY() + y;
	}

	public static void drawString(IContrivitiveGui gui, String string, int x, int y, int color) {
		x = adjustX(gui, x);
		y = adjustY(gui, y);
		gui.getGui().mc.fontRenderer.drawString(string, x, y, color);
	}

	public static void drawString(IContrivitiveGui gui, String string, int x, int y) {
		drawString(gui, string, x, y, 16777215);
	}

	public static void drawCenteredString(IContrivitiveGui gui, String string, int y, int color) {
		drawString(gui, string, (gui.getWidth() / 2 - getStringWidth(string) / 2), y, color);
	}

	public static void drawCenteredString(IContrivitiveGui gui, String string, int x, int y, int color) {
		drawString(gui, string, (x - getStringWidth(string) / 2), y, color);
	}

	public static int getStringWidth(String string) {
		return Minecraft.getMinecraft().fontRenderer.getStringWidth(string);
	}
}
