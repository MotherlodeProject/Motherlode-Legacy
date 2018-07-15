package motherlode.block;

import motherlode.client.model.BlockModelDefinition;
import motherlode.client.model.ItemModelDefinition;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public interface IModeledBlock {
	@SideOnly(Side.CLIENT)
	public BlockModelDefinition getBlockModelDefinition();

	@Nullable
	@SideOnly(Side.CLIENT)
	public ItemModelDefinition getItemModelDefinition();
}
