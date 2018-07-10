package motherlode.tileentity;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import java.util.Random;

public class TileEntityPot extends TileEntity {
	EnumDyeColor color;
	EnumDyeColor patternColor;
	int pattern;
	Random random = new Random();

	public TileEntityPot() {

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
		world.markBlockRangeForRenderUpdate(pos, pos);
	}

	public void setColor(EnumDyeColor color) {
		this.color = color;
		notifyUpdate();
	}

	public void setPatternColor(EnumDyeColor patternColor) {
		this.patternColor = patternColor;
		notifyUpdate();
	}

	public void setPattern(int pattern) {
		this.pattern = pattern;
		notifyUpdate();
	}

	public void notifyUpdate() {
		world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
		markDirty();
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
