package contrivitive;

import contrivitive.lib.ContrivitiveConstants;
import contrivitive.registry.ContrivitiveItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.ArrayList;
import java.util.Random;

@Mod(modid = ContrivitiveConstants.MOD_ID, name = ContrivitiveConstants.MOD_NAME, version = ContrivitiveConstants.VERSION, dependencies = ContrivitiveConstants.DEPENDENCIES)
public class Contrivitive {

	public static final Random RANDOM = new Random();

	public static final CreativeTabs TAB = new CreativeTabs(ContrivitiveConstants.MOD_ID) {
		private ArrayList<ItemStack> buckets = new ArrayList<>();

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ContrivitiveItems.getItems().get(RANDOM.nextInt(ContrivitiveItems.getItems().size())));
		}

/*		@Override
		@SideOnly(Side.CLIENT)
		public void displayAllRelevantItems(NonNullList<ItemStack> list) {
			super.displayAllRelevantItems(list);
			if (buckets.isEmpty() && !MechFluids.getFluids().keySet().isEmpty()) {
				for (Fluid fluid : MechFluids.getFluids().keySet()) {
					buckets.add(FluidUtil.getFilledBucket(new FluidStack(fluid, 1000)).copy());
				}
			}
			list.addAll(buckets);
		}*/
	};

	@Mod.Instance
	public static Contrivitive instance;

	@SidedProxy(clientSide = ContrivitiveConstants.CLIENT_PROXY_CLASS, serverSide = ContrivitiveConstants.SERVER_PROXY_CLASS)
	public static ContrivitiveCommon proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
}
