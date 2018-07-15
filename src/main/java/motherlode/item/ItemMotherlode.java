package motherlode.item;

import motherlode.client.model.ItemModelDefinition;
import motherlode.util.InitUtil;
import net.minecraft.item.Item;

public class ItemMotherlode extends Item implements IModeledItem {
	public String name;
	public String blockstate;

	public ItemMotherlode(String name) {
		this(name, "");
	}

	public ItemMotherlode(String name, String blockstate) {
		super();
		this.name = name;
		this.blockstate = blockstate;
		InitUtil.setup(this, name);
	}

	@Override
	public ItemModelDefinition getItemModelDefinition() {
		if (blockstate.isEmpty()) {
			return new ItemModelDefinition(this);
		}
		return new ItemModelDefinition(this, blockstate).setVariant("type=" + name);
	}
}
