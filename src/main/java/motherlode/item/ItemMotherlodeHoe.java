package motherlode.item;

import motherlode.client.model.ItemModelDefinition;
import motherlode.util.InitUtil;
import net.minecraft.item.ItemHoe;

public class ItemMotherlodeHoe extends ItemHoe implements IModeledItem{
	public final String name;

	public ItemMotherlodeHoe(String name, ToolMaterial material) {
		super(material);
		this.name = name;
		InitUtil.setup(this, name);
	}

	@Override
	public ItemModelDefinition getItemModelDefinition() {
		return new ItemModelDefinition(this, "tool").setVariant("type=" + name);
	}
}
