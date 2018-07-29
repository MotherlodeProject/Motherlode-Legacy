package motherlode.client.model;

import motherlode.Motherlode;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class ItemBlockModelDefinition extends ItemModelDefinition {

	protected Block block;
	protected List<String> prepends = new ArrayList<>();
	protected List<String> appends = new ArrayList<>();

	public ItemBlockModelDefinition(BlockModelDefinition definition) {
		this(definition, 0);
	}

	@SuppressWarnings("deprecation")
	public ItemBlockModelDefinition(BlockModelDefinition definition, int maxMeta) {
		this(definition.getBlock(), maxMeta, definition.getFileLocation());
		this.setVariant(meta -> definition.getVariantForState(definition.getBlock().getStateFromMeta(meta)));
	}

	public ItemBlockModelDefinition(Block block) {
		this(block, 0);
	}

	public ItemBlockModelDefinition(Block block, int maxMeta) {
		this(block, maxMeta, block.getRegistryName());
	}

	public ItemBlockModelDefinition(Block block, String fileLocation) {
		this(block, 0, fileLocation);
	}

	public ItemBlockModelDefinition(Block block, int maxMeta, String fileLocation) {
		this(block, maxMeta, new ResourceLocation(Motherlode.MOD_ID, fileLocation));
	}

	public ItemBlockModelDefinition(Block block, ResourceLocation fileLocation) {
		this(block, 0, fileLocation);
	}

	public ItemBlockModelDefinition(Block block, int maxMeta, ResourceLocation fileLocation) {
		super(null, maxMeta, fileLocation);
		this.block = block;
	}

	public ItemBlockModelDefinition prepend(String prepend) {
		this.prepends.add(prepend);
		return this;
	}

	public ItemBlockModelDefinition append(String append) {
		this.appends.add(append);
		return this;
	}

	public Block getBlock() {
		return block;
	}

	public List<String> getPrepends() {
		return prepends;
	}

	public List<String> getAppends() {
		return appends;
	}
}
