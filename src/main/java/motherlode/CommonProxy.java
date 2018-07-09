package motherlode;

import motherlode.block.MotherlodeBlocks;
import motherlode.item.MotherlodeItems;

public abstract class CommonProxy {

	public void preInit() {
		MotherlodeBlocks.load();
		MotherlodeItems.load();
	}

	public void init() {

	}

	public void postInit() {

	}

	abstract public boolean isDedicatedServer();

}
