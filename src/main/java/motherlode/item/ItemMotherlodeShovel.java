package motherlode.item;

import motherlode.client.model.ItemModelDefinition;
import motherlode.util.InitUtil;
import net.minecraft.item.ItemSpade;

public class ItemMotherlodeShovel extends ItemSpade implements IModeledItem {
	public final String name;

	public ItemMotherlodeShovel(String name, ToolMaterial material) {
		super(material);
		this.name = name;
		InitUtil.setup(this, name);
	}

	@Override
	public ItemModelDefinition getItemModelDefinition() {
		return new ItemModelDefinition(this, "tool").setVariant("type=" + name);
	}
}
