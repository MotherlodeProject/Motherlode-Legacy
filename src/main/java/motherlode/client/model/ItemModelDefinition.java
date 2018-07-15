package motherlode.client.model;

import motherlode.Motherlode;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ItemModelDefinition {
	protected Item item;
	protected int maxMeta;
	protected ResourceLocation fileLocation;
	protected ItemMeshDefinition meshDefinition = null;
	protected ItemVariantGetter itemVariantGetter = null;
	protected IItemColor iItemColor = null;
	protected VariantMetaGetter variant = meta -> "inventory";

	public ItemModelDefinition(Item item) {
		this(item, 0);
	}

	public ItemModelDefinition(Item item, int maxMeta) {
		this(item, maxMeta, item.getRegistryName());
	}

	public ItemModelDefinition(Item item, String fileLocation) {
		this(item, 0, fileLocation);
	}

	public ItemModelDefinition(Item item, int maxMeta, String fileLocation) {
		this(item, maxMeta, new ResourceLocation(Motherlode.MOD_ID, fileLocation));
	}

	public ItemModelDefinition(Item item, ResourceLocation fileLocation) {
		this(item, 0, fileLocation);
	}

	public ItemModelDefinition(Item item, int maxMeta, ResourceLocation fileLocation) {
		this.item = item;
		this.maxMeta = maxMeta;
		this.fileLocation = fileLocation;
	}

	public ItemModelDefinition setMeshDefinition(ItemMeshDefinition meshDefinition, ItemVariantGetter itemVariantGetter) {
		this.meshDefinition = meshDefinition;
		this.itemVariantGetter = itemVariantGetter;
		return this;
	}

	public ItemModelDefinition setIItemColor(IItemColor iItemColor) {
		this.iItemColor = iItemColor;
		return this;
	}

	public ItemModelDefinition setVariant(String variant) {
		this.variant = meta -> variant;
		return this;
	}

	public ItemModelDefinition setVariant(VariantMetaGetter variant) {
		this.variant = variant;
		return this;
	}

	public Item getItem() {
		return item;
	}

	public ResourceLocation getFileLocation() {
		return fileLocation;
	}

	public ItemMeshDefinition getMeshDefinition() {
		return meshDefinition;
	}

	public IItemColor getIItemColor() {
		return iItemColor;
	}

	public VariantMetaGetter getVariant() {
		return variant;
	}
}
