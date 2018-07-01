package motherlode;

import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

@Mod(modid = MotherlodeMod.MODID, name = MotherlodeMod.NAME, version = MotherlodeMod.VERSION)
public class MotherlodeMod {
    public static final String MODID = "motherlode";
    public static final String NAME = "Motherlode";
    public static final String VERSION = "0.0";

    @Mod.Instance(MotherlodeMod.MODID)
    public static MotherlodeMod instance;
    
    @SidedProxy(clientSide="motherlode.ClientOnlyProxy", serverSide="motherlode.DecicatedServerProxy")

    public static CommonProxy proxy;
        
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    	proxy.init();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	proxy.postInit();
    }
}
