package motherlode;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import motherlode.block.MotherlodeBlocks;
import motherlode.gui.MotherlodeGuiHandler;
import motherlode.item.MotherlodeItems;
import motherlode.network.MotherlodeNetwork;
import motherlode.registry.MotherlodeRegistry;
import motherlode.tileentity.TileEntityPot;
import motherlode.world.biome.MotherlodeBiomes;
import motherlode.world.gen.MotherlodeWorldGenerators;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Motherlode.MOD_ID, name = Motherlode.NAME, version = Motherlode.VERSION)
public class Motherlode {

	public static final String MOD_ID = "motherlode";
	public static final String NAME = "Motherlode";
	public static final String VERSION = "1.0.0";
	public static final CreativeTabs TAB = new CreativeTabs("motherlode") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(MotherlodeRegistry.ITEMS.get(new Random().nextInt(MotherlodeRegistry.ITEMS.size())));
		}
	};

	@Mod.Instance(Motherlode.MOD_ID)
	public static Motherlode instance;

	@SidedProxy(clientSide = "motherlode.client.ClientProxy", serverSide = "motherlode.server.ServerProxy")
	public static CommonProxy proxy;

	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MotherlodeBlocks.load();
		MotherlodeItems.load();
		MotherlodeBiomes.load();
		MotherlodeNetwork.registerMessages();
		GameRegistry.registerTileEntity(TileEntityPot.class, new ResourceLocation(Motherlode.MOD_ID, "pot"));
		proxy.preInit();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new MotherlodeGuiHandler());
		MotherlodeWorldGenerators.registerWorldGens();
		proxy.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit();
	}
}
