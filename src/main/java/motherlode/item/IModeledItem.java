package motherlode.item;

import motherlode.client.model.ItemModelDefinition;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IModeledItem {

	@SideOnly(Side.CLIENT)
	public ItemModelDefinition getItemModelDefinition();
}
