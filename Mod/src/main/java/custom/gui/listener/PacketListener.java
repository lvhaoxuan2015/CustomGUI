package custom.gui.listener;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import custom.gui.gui.CustomGUIAPI;
import custom.gui.gui.GuiUtil;
import custom.gui.gui.object.EGuiObject;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;

public class PacketListener {
	@SubscribeEvent
	public void ClientCustomPacketEvent(ClientCustomPacketEvent packet) throws UnsupportedEncodingException {
		final FMLNetworkEvent.ClientCustomPacketEvent clientCustomPacketEvent = packet;
		byte[] datas = new byte[clientCustomPacketEvent.getPacket().payload().readableBytes()];
		clientCustomPacketEvent.getPacket().payload().readBytes(datas);
		String str = new String(datas);
		if (str.contains("OPENGUI")) {
			str = str.replace("OPENGUI", "").replace("$MIDDLEBRACKETS1$", "").replace("$MIDDLEBRACKETS2$", "");
			int guiID = Integer.parseInt(str.split("\\$SEPARATOR\\$")[0]);
			List<EGuiObject> list = GuiUtil.backToObject(str.split("\\$SEPARATOR\\$")[1]);
			CustomGUIAPI.openGUI(list, guiID);
		}
	}
}
