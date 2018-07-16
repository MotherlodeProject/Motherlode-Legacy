package motherlode.client.model;

import com.google.common.collect.Maps;
import motherlode.Motherlode;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class BlockModelDefinition extends StateMapperBase {
	protected Block block;
	protected ResourceLocation fileLocation;
	protected List<String> prepends = new ArrayList<>();
	protected List<String> appends = new ArrayList<>();
	protected IProperty[] ignoredProperties = null;
	protected VariantStateGetter variant = state -> "";
	protected IBlockColor iBlockColor = null;

	public BlockModelDefinition(Block block, IProperty... ignoredProperties) {
		this(block, block.getRegistryName(), ignoredProperties);
	}

	public BlockModelDefinition(Block block, String fileLocation, IProperty... ignoredProperties) {
		this(block, new ResourceLocation(Motherlode.MOD_ID, fileLocation), ignoredProperties);
	}

	public BlockModelDefinition(Block block, ResourceLocation fileLocation, IProperty... ignoredProperties) {
		this.block = block;
		this.fileLocation = fileLocation;
		this.ignoredProperties = ignoredProperties;
	}

	public BlockModelDefinition prepend(String prepend) {
		this.prepends.add(prepend);
		return this;
	}

	public BlockModelDefinition append(String append) {
		this.appends.add(append);
		return this;
	}

	public BlockModelDefinition setVariant(String variant) {
		this.variant = state -> variant;
		return this;
	}

	public BlockModelDefinition setVariant(VariantStateGetter variant) {
		this.variant = variant;
		return this;
	}

	public BlockModelDefinition setIBlockColor(IBlockColor iBlockColor) {
		this.iBlockColor = iBlockColor;
		return this;
	}

	public Block getBlock() {
		return block;
	}

	public ResourceLocation getFileLocation() {
		return fileLocation;
	}

	public List<String> getPrepends() {
		return prepends;
	}

	public List<String> getAppends() {
		return appends;
	}

	public VariantStateGetter getVariant() {
		return variant;
	}

	public IProperty[] getIgnoredProperties() {
		return ignoredProperties;
	}

	public IBlockColor getIBlockColor() {
		return iBlockColor;
	}

	public String getVariantForState(IBlockState state) {
		Map<IProperty<?>, Comparable<?>> map = Maps.newLinkedHashMap(state.getProperties()); // gets a map of the state's properties
		for (IProperty<?> iproperty : getIgnoredProperties()) { // removes ignored properties
			map.remove(iproperty);
		}
		String customVariant = this.variant.getVariantForState(state);
		return customVariant.isEmpty() ? getPropertyString(map) : customVariant;
	}

	@Override
	protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
		return new MRLBuilder(fileLocation).setPrepends(prepends).setAppends(appends).setVariant(getVariantForState(state)).build();
	}
}
