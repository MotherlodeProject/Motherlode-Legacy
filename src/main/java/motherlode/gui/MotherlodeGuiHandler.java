package motherlode.gui;

import contrivitive.gui.ContrivitiveContainer;
import contrivitive.gui.ContrivitiveGuiContainer;
import contrivitive.gui.GuiContainerBlueprint;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class MotherlodeGuiHandler implements IGuiHandler {

	@Nullable
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		GuiContainerBlueprint blueprint = MLPlayerInv.getCTInventoryBlueprint(player);
		if (id == 0) {
			return new ContrivitiveContainer(blueprint, "main", player);
		}
		return null;
	}

	@Nullable
	@Override
	@SideOnly(Side.CLIENT)
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		GuiContainerBlueprint blueprint = MLPlayerInv.getCTInventoryBlueprint(player);
		if (id == 0) {
			return new ContrivitiveGuiContainer(new ContrivitiveContainer(blueprint, "main", player));
		}
		return null;
	}
}
