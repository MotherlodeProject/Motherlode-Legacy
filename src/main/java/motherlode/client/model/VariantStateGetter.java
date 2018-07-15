package motherlode.client.model;

import net.minecraft.block.state.IBlockState;

public interface VariantStateGetter {
	public String getVariantForState(IBlockState state);
}
