package motherlode.block;

import motherlode.client.model.BlockModelDefinition;
import motherlode.client.model.ItemBlockModelDefinition;
import motherlode.client.model.ItemModelDefinition;
import motherlode.util.InitUtil;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.MapColor;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMotherlodeLog extends BlockLog implements IModeledBlock {

	public final String name;

	public BlockMotherlodeLog(String name, Item drop, float hardness, float resistence, int harvestLevel, int expMin, int expMax, MapColor mapColor) {
		super();
		this.name = name;
		InitUtil.setup(this, name + "_log");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockModelDefinition getBlockModelDefinition() {
		return new BlockModelDefinition(this, "log").setVariant("type=" + name);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ItemModelDefinition getItemModelDefinition() {
		return new ItemBlockModelDefinition(this, "log").setVariant("type=" + name);
	}
}
