package motherlode.item;

import motherlode.util.InitUtil;
import motherlode.util.ModelCompound;
import net.minecraft.item.ItemSpade;

public class ItemMotherlodeShovel extends ItemSpade {

	public ItemMotherlodeShovel(String name, ToolMaterial material) {
		super(material);
		InitUtil.setup(this, name, new ModelCompound(this).setFileName("tool").setInvVariant("type=" + name));
	}
}
