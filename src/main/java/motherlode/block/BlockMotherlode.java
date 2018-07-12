package motherlode.block;

import motherlode.util.ModelCompound;
import motherlode.util.InitUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockMotherlode extends Block {
	public BlockMotherlode(String name, Material materialIn) {
		super(materialIn);
		InitUtil.setup(this, name);
		InitUtil.setModel(new ModelCompound(this));
	}

	public BlockMotherlode(String name) {
		this(name, Material.ROCK);
	}

	public BlockMotherlode(String name, String blockstate, Material materialIn) {
		super(materialIn);
		InitUtil.setup(this, name);
		InitUtil.setModel(new ModelCompound(this).setFileName(blockstate).setInvVariant("type=" + name));
	}

	public BlockMotherlode(String name, String blockstate) {
		this(name, Material.ROCK);
	}
}
