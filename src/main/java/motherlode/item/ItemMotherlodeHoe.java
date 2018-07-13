package motherlode.item;

import motherlode.util.InitUtil;
import motherlode.util.ModelCompound;
import net.minecraft.item.ItemHoe;

public class ItemMotherlodeHoe extends ItemHoe {

	public ItemMotherlodeHoe(String name, ToolMaterial material) {
		super(material);
		InitUtil.setup(this, name, new ModelCompound(this).setFileName("tool").setInvVariant("type=" + name));
	}
}
