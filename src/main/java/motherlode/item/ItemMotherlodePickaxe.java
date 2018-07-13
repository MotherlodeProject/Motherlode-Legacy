package motherlode.item;

import motherlode.util.InitUtil;
import motherlode.util.ModelCompound;
import net.minecraft.item.ItemPickaxe;

public class ItemMotherlodePickaxe extends ItemPickaxe {

	public ItemMotherlodePickaxe(String name, ToolMaterial material) {
		super(material);
		InitUtil.setup(this, name, new ModelCompound(this).setFileName("tool").setInvVariant("type=" + name));
	}
}
