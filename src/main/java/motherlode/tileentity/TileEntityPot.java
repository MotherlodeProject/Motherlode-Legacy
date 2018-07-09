package motherlode.tileentity;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class TileEntityPot extends TileEntity {
	EnumDyeColor color;
	EnumDyeColor patternColor;
	int pattern;
	Random random = new Random();

	public TileEntityPot() {
		color = EnumDyeColor.WHITE;
		patternColor = EnumDyeColor.WHITE;
		pattern = 0;
	}

	public void randomizePot() {
		if (!world.isRemote) {
			color = EnumDyeColor.byMetadata(MathHelper.getInt(random, 0, 15));
			patternColor = EnumDyeColor.byMetadata(MathHelper.getInt(random, 0, 15));
			pattern = MathHelper.getInt(random, 0, 3);
			world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
			markDirty();
		}
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

	@Override
	public NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new SPacketUpdateTileEntity(getPos(), 1, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		this.readFromNBT(packet.getNbtCompound());
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
