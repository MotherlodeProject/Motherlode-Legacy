package motherlode;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

public class MotherlodeWorldSavedData extends WorldSavedData {

	private boolean hasNetherAccess = true; // True to allow access by default
	private static final String DATA_NAME = Motherlode.MOD_ID + "_Data";
	
	public MotherlodeWorldSavedData(String name) {
		super(name);
	}
	
	public static MotherlodeWorldSavedData get(World world) {
		MapStorage mapStorage = world.getMapStorage();
		MotherlodeWorldSavedData instance = (MotherlodeWorldSavedData)mapStorage.getOrLoadData(MotherlodeWorldSavedData.class, DATA_NAME);
		if (instance == null) {
			instance = new MotherlodeWorldSavedData(DATA_NAME);
			mapStorage.setData(DATA_NAME, instance);
		}
		
		return instance;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		this.hasNetherAccess = nbt.getBoolean("hasNetherAccess");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setBoolean("hasNetherAccess", this.hasNetherAccess);
		return nbt;
	}

	public boolean getNetherAccess() {
		return this.hasNetherAccess;
	}
	
	public void setNetherAccess(boolean access) {
		this.hasNetherAccess = access;
		this.markDirty();
	}
	
}
