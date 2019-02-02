package custom.gui;

import java.io.FileNotFoundException;
import java.io.IOException;

import custom.gui.listener.MainListener;
import custom.gui.listener.PacketListener;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = CustomGUI.MODID, version = CustomGUI.VERSION)
public class CustomGUI {

    public static final String MODID = "customgui";
    public static final String VERSION = "1.0";
    public static FMLEventChannel net;
    public static PacketListener pl = new PacketListener();
    public static MainListener ml = new MainListener();

    @EventHandler
    public void init(FMLInitializationEvent event) throws FileNotFoundException, IOException {
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(pl);
        MinecraftForge.EVENT_BUS.register(ml);
        (net = NetworkRegistry.INSTANCE.newEventDrivenChannel(MODID)).register(pl);
    }
}
