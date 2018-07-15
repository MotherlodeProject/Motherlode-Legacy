package motherlode.block;

import motherlode.client.model.BlockModelDefinition;
import motherlode.client.model.ItemBlockModelDefinition;
import motherlode.client.model.ItemModelDefinition;
import motherlode.util.InitUtil;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockMotherlodeOre extends Block implements IModeledBlock {
	public final String name;
	public int dropMin = 1;
	public int dropMax = 1;
	public int expMin, expMax;
	public boolean noFortune = false;
	public Item drop;

	public BlockMotherlodeOre(String name, Item drop, float hardness, float resistence, int harvestLevel, int expMin, int expMax, MapColor mapColor) {
		super(Material.ROCK, mapColor);
		this.name = name;
		this.drop = drop;
		this.expMin = expMin;
		this.expMax = expMax;
		InitUtil.setup(this, name + "_ore");
		setHardness(hardness);
		setResistance(resistence);
		setHarvestLevel("pickaxe", harvestLevel);
		setSoundType(SoundType.STONE);
	}

	public BlockMotherlodeOre(String name, Item drop, float hardness, float resistence, int harvestLevel, int expMin, int expMax) {
		this(name, drop, hardness, resistence, harvestLevel, expMin, expMax, MapColor.STONE);
	}

	public BlockMotherlodeOre setDropQuantity(int amount) {
		return setDropQuantity(amount, amount);
	}

	public BlockMotherlodeOre setDropQuantity(int min, int max) {
		this.dropMin = min;
		this.dropMax = max;
		return this;
	}

	public BlockMotherlodeOre setNoFortune() {
		noFortune = true;
		return this;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return drop;
	}

	@Override
	public int quantityDropped(Random random) {
		return MathHelper.getInt(random, dropMin, dropMax);
	}

	@Override
	public int quantityDroppedWithBonus(int fortune, Random random) {
		if (!noFortune && fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped(this.getBlockState().getValidStates().iterator().next(), random, fortune)) {
			int multiplier = random.nextInt(fortune + 2) - 1;

			if (multiplier < 0) {
				multiplier = 0;
			}

			return this.quantityDropped(random) * (multiplier + 1);
		} else {
			return this.quantityDropped(random);
		}
	}

	@Override
	public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
		Random rand = world instanceof World ? ((World) world).rand : new Random();
		if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this)) {
			return MathHelper.getInt(rand, expMin, expMax);
		}
		return 0;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockModelDefinition getBlockModelDefinition() {
		return new BlockModelDefinition(this, "ore").setVariant("type=" + name);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ItemModelDefinition getItemModelDefinition() {
		return new ItemBlockModelDefinition(this, "ore").setVariant("type=" + name);
	}
}
