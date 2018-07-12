package motherlode.item;

import motherlode.util.ModelCompound;
import motherlode.util.InitUtil;
import net.minecraft.item.Item;

public class ItemMotherlode extends Item {
	public ItemMotherlode(String name) {
		super();
		InitUtil.setup(this, name);
		InitUtil.setModel(new ModelCompound(this));
	}

	public ItemMotherlode(String name, String blockstate) {
		super();
		InitUtil.setup(this, name);
		InitUtil.setModel(new ModelCompound(this).setFileName(blockstate).setInvVariant("type=" + name));
	}
}
