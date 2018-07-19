package motherlode.block;

import motherlode.client.model.BlockModelDefinition;
import motherlode.client.model.ItemBlockModelDefinition;
import motherlode.client.model.ItemModelDefinition;
import motherlode.util.InitUtil;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMotherlodeStairs extends BlockStairs implements IModeledBlock {
	public final String name;
	public final String blockstate;

	public BlockMotherlodeStairs(String name, IBlockState modelState) {
		this(name, "", modelState);
	}

	public BlockMotherlodeStairs(String name, String blockstate, IBlockState modelState) {
		super(modelState);
		this.name = name;
		this.blockstate = blockstate;
		InitUtil.setup(this, name + "_stairs");
		useNeighborBrightness = true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockModelDefinition getBlockModelDefinition() {
		return new BlockModelDefinition(this, blockstate).setVariant(state -> "slab_half=ignore,stair=facing_" + state.getValue(FACING).getName() + "/half_" + state.getValue(HALF).getName() + "/shape_" + state.getValue(SHAPE).getName()).append("type=" + name);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ItemModelDefinition getItemModelDefinition() {
		return new ItemBlockModelDefinition(this, blockstate).prepend("slab_half=ignore,stair=facing_west/half_bottom/shape_straight").setVariant("type=" + name);
	}
}
