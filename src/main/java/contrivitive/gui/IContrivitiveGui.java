package contrivitive.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IContrivitiveGui<G extends GuiScreen, B extends GuiBlueprint> {
	@SideOnly(Side.CLIENT)
	public G getGui();

	@SideOnly(Side.CLIENT)
	public int getWidth();

	@SideOnly(Side.CLIENT)
	public int getHeight();

	@SideOnly(Side.CLIENT)
	public int getStartingX();

	@SideOnly(Side.CLIENT)
	public int getStartingY();

	public B getGuiBlueprint();

	@SideOnly(Side.CLIENT)
	public default boolean disableAnimations() {
		return false;
	}
}
