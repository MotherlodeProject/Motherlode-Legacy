package motherlode.block;

import motherlode.client.model.BlockModelDefinition;
import motherlode.client.model.ItemBlockModelDefinition;
import motherlode.client.model.ItemModelDefinition;
import motherlode.util.InitUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMotherlode extends Block implements IModeledBlock {
	public final String name;
	public final String blockstate;

	public BlockMotherlode(String name) {
		this(name, Material.ROCK);
	}

	public BlockMotherlode(String name, Material material) {
		this(name, "", material);
	}

	public BlockMotherlode(String name, String blockstate) {
		this(name, Material.ROCK);
	}

	public BlockMotherlode(String name, String blockstate, Material material) {
		super(material);
		this.name = name;
		this.blockstate = blockstate;
		InitUtil.setup(this, name);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockModelDefinition getBlockModelDefinition() {
		if (blockstate.isEmpty()) {
			return new BlockModelDefinition(this);
		}
		return new BlockModelDefinition(this, blockstate).setVariant("type=" + name);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ItemModelDefinition getItemModelDefinition() {
		if (blockstate.isEmpty()) {
			return new ItemBlockModelDefinition(this);
		}
		return new ItemBlockModelDefinition(this, blockstate).setVariant("type=" + name);
	}
}
