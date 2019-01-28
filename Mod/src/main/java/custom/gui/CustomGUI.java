package custom.gui;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import custom.gui.gui.CustomGUIAPI;
import custom.gui.gui.Gui;
import custom.gui.gui.GuiUtil;
import custom.gui.gui.object.EGuiObject;
import custom.gui.listener.MainListener;
import custom.gui.listener.PacketListener;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = CustomGUI.MODID, version = CustomGUI.VERSION)
public class CustomGUI {
	public static final String MODID = "customgui";
	public static final String VERSION = "1.0";
	public static FMLEventChannel net;
	public static PacketListener pl = new PacketListener();
	public static MainListener ml = new MainListener();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(pl);
		MinecraftForge.EVENT_BUS.register(ml);
		(net = NetworkRegistry.INSTANCE.newEventDrivenChannel(MODID)).register(pl);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
	}
}
