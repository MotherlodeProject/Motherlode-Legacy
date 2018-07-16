package motherlode.block;

import motherlode.client.model.BlockModelDefinition;
import motherlode.client.model.ItemBlockModelDefinition;
import motherlode.client.model.ItemModelDefinition;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMotherlodeSimple extends BlockMotherlode implements IModeledBlock {

	public BlockMotherlodeSimple(String name) {
		super(name);
	}

	public BlockMotherlodeSimple(String name, Material material) {
		super(name, material);
	}

	public BlockMotherlodeSimple(String name, String blockstate) {
		super(name, blockstate);
	}

	public BlockMotherlodeSimple(String name, String blockstate, Material material) {
		super(name, blockstate, material);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockModelDefinition getBlockModelDefinition() {
		return new BlockModelDefinition(this, blockstate).prepend("horiz_facing=ignore,slab_half=ignore,stairs_half=ignore,stairs_shape=ignore").setVariant("type=" + name);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ItemModelDefinition getItemModelDefinition() {
		return new ItemBlockModelDefinition(this, blockstate).prepend("horiz_facing=ignore,slab_half=ignore,stairs_half=ignore,stairs_shape=ignore").setVariant("type=" + name);
	}
}
