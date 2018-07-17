package motherlode.recipe;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public enum RecipeTable implements IRecipeTable {
	NONE(null),
	CRAFTING_TABLE(Blocks.CRAFTING_TABLE),
	FURNACE(Blocks.FURNACE);

	Block block;

	RecipeTable(Block block) {
		this.block = block;
	}

	@Override
	public Block getBlock() {
		return block;
	}
}
