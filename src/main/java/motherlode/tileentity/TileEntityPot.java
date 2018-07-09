package motherlode.tileentity;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class TileEntityPot extends TileEntity {
	EnumDyeColor color;
	EnumDyeColor patternColor;
	int pattern;
	Random random = new Random();

	public TileEntityPot() {
		randomizePot();
	}

	public void randomizePot() {
		color = EnumDyeColor.byMetadata(MathHelper.getInt(random, 0, 15));
		patternColor = EnumDyeColor.byMetadata(MathHelper.getInt(random, 0, 15));
		pattern = MathHelper.getInt(random, 0, 3);
		markDirty();
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound = super.writeToNBT(compound);
		compound.setInteger("Color", color.getMetadata());
		compound.setInteger("PatternColor", patternColor.getMetadata());
		compound.setInteger("Pattern", pattern);
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		color = EnumDyeColor.byMetadata(compound.getInteger("Color"));
		patternColor = EnumDyeColor.byMetadata(compound.getInteger("PatternColor"));
		pattern = compound.getInteger("Pattern");
	}

	public EnumDyeColor getColor() {
		return color;
	}

	public EnumDyeColor getPatternColor() {
		return patternColor;
	}

	public int getPattern() {
		return pattern;
	}
}
