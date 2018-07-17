package contrivitive.gui.element;

import net.minecraft.entity.player.EntityPlayer;

import java.util.List;

public interface PlayerSpecificElements {
	public List<Element> getDynamicElements(EntityPlayer player);
}
