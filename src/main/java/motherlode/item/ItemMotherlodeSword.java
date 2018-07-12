package motherlode.item;

import motherlode.util.InitUtil;
import motherlode.util.ModelCompound;
import net.minecraft.item.ItemSword;

public class ItemMotherlodeSword extends ItemSword {

	public ItemMotherlodeSword(String name, ToolMaterial material) {
		super(material);
		InitUtil.setup(this, name);
		InitUtil.setModel(new ModelCompound(this).setFileName("tool").setInvVariant("type=" + name));
	}
}
