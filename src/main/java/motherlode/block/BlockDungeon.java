package motherlode.block;

import motherlode.client.model.BlockModelDefinition;
import motherlode.client.model.ItemBlockModelDefinition;
import motherlode.client.model.ItemModelDefinition;
import motherlode.util.InitUtil;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDungeon extends BlockMotherlode {

	public BlockDungeon(String name) {
		super(name, Material.ROCK);
		InitUtil.setup(this, name);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockModelDefinition getBlockModelDefinition() {
		return new BlockModelDefinition(this, "dunegon").setVariant("type=" + name);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ItemModelDefinition getItemModelDefinition() {
		return new ItemBlockModelDefinition(this, "dunegon").setVariant("type=" + name);
	}
}
