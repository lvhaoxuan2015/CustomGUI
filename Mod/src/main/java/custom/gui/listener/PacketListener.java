package custom.gui.listener;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import custom.gui.CustomGUI;
import custom.gui.gui.CustomGUIAPI;
import custom.gui.gui.Gui;
import custom.gui.gui.GuiUtil;
import custom.gui.gui.object.EGuiObject;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;

public class PacketListener {
	@SubscribeEvent
	public void ClientCustomPacketEvent(ClientCustomPacketEvent packet) throws UnsupportedEncodingException {
		FMLNetworkEvent.ClientCustomPacketEvent clientCustomPacketEvent = packet;
		byte[] datas = new byte[clientCustomPacketEvent.getPacket().payload().readableBytes()];
		clientCustomPacketEvent.getPacket().payload().readBytes(datas);
		String str = new String(datas);
		if (str.contains("OPENGUI")) {
			str = str.replace("OPENGUI", "").replace("$MIDDLEBRACKETS1$", "").replace("$MIDDLEBRACKETS2$", "");
			int guiID = Integer.parseInt(str.split("\\$SEPARATOR\\$")[0]);
			List<EGuiObject> list = GuiUtil.backToObject(str.split("\\$SEPARATOR\\$")[1]);
			CustomGUIAPI.openGUI(list, guiID);
		} else if (str.contains("CHECKMOD")) {
			new Timer().schedule(new TimerTask() {
				@Override
				public void run() {
					if (Minecraft.getMinecraft().inGameHasFocus || Minecraft.getMinecraft().isGamePaused()) {
						CustomGUI.net.sendToServer(new FMLProxyPacket(
								new PacketBuffer(Unpooled.wrappedBuffer(("CHECKMOD").getBytes())), CustomGUI.MODID));
						cancel();
					}
				}
			}, 0, 1000);
		} else if (str.contains("GETFIELD")) {
			str = str.replace("GETFIELD", "");
			int id = Integer.parseInt(str.split("\\$SEPARATOR\\$")[1]);
			if (Minecraft.getMinecraft().currentScreen instanceof Gui) {
				Gui gui = (Gui) Minecraft.getMinecraft().currentScreen;
				for (GuiTextField gtf : gui.GuiFieldList) {
					if (gtf.getId() == id) {
						CustomGUI.net.sendToServer(new FMLProxyPacket(
								new PacketBuffer(Unpooled
										.wrappedBuffer(("GUIFIELD:" + gtf.getText() + ";" + gtf.getId()).getBytes())),
								CustomGUI.MODID));
					}
				}
			}
		}
	}
}
