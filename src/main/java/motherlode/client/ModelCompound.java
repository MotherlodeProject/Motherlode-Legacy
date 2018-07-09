package motherlode.client;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.Item;

public class ModelCompound {
	private String inventoryVariant = "inventory";
	private String variant = "shootingstar.undefinedvariant";
	private String fileName = "shootingstar.undefinedfilename";
	private String blockstatePath;
	private IProperty[] ignoreProperties = null;
	private Block block = null;
	private Item item = null;
	private int meta;

	public ModelCompound(Block block, int meta, String blockstatePath, IProperty... ignoreProperties) {
		this.block = block;
		this.blockstatePath = blockstatePath;
		this.ignoreProperties = ignoreProperties;
		this.meta = meta;
	}

	public ModelCompound(Block block, int meta, IProperty... ignoreProperties) {
		this(block, meta, "", ignoreProperties);
	}

	public ModelCompound(Block block, IProperty... ignoreProperties) {
		this(block, 0, "", ignoreProperties);
	}

	public ModelCompound(Block block, String blockstatePath, IProperty... ignoreProperties) {
		this(block, 0, blockstatePath, ignoreProperties);
	}

	public ModelCompound(Item item, int meta, String blockstatePath) {
		this.item = item;
		this.blockstatePath = blockstatePath;
		this.meta = meta;
	}

	public ModelCompound(Item item, int meta) {
		this(item, meta, "");
	}

	public ModelCompound(Item item) {
		this(item, 0, "");
	}

	public ModelCompound(Item item, String blockstatePath) {
		this(item, 0, blockstatePath);
	}

	public String getInventoryVariant() {
		return inventoryVariant;
	}

	public String getFileName() {
		return fileName;
	}

	public ModelCompound setFileName(String name) {
		fileName = name;
		return this;
	}

	public ModelCompound setInvVariant(String variant) {
		inventoryVariant = variant;
		return this;
	}

	public Block getBlock() {
		return block;
	}

	public Item getItem() {
		if (isBlock())
			return Item.getItemFromBlock(block);
		return item;
	}

	public boolean isBlock() {
		if (block != null)
			return true;
		return false;
	}

	public boolean isItem() {
		if (item != null)
			return true;
		return false;
	}

	public String getBlockStatePath() {
		return blockstatePath;
	}

	public void setBlockStatePath(String blockstatePath) {
		this.blockstatePath = blockstatePath;
	}

	public IProperty[] getIgnoreProperties() {
		return ignoreProperties;
	}

	public int getMeta() {
		return meta;
	}

	public String getVariant() {
		return variant;
	}

	public ModelCompound setVariant(String variant) {
		this.variant = variant;
		return this;
	}
}