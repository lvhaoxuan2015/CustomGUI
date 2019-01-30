package custom.gui.listener;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import custom.gui.gui.CustomGUIAPI;
import custom.gui.gui.GuiUtil;
import custom.gui.gui.object.EGuiObject;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;

public class PacketListener {
	@SubscribeEvent
	public void ClientCustomPacketEvent(ClientCustomPacketEvent packet) throws UnsupportedEncodingException {
		FMLNetworkEvent.ClientCustomPacketEvent clientCustomPacketEvent = packet;
		byte[] datas = new byte[clientCustomPacketEvent.getPacket().payload().readableBytes()];
		clientCustomPacketEvent.getPacket().payload().readBytes(datas);
		String str = new String(datas);
		Gson gson = new Gson();
		JsonObject obj = gson.fromJson(str, JsonObject.class);
		String method = obj.get("Method").getAsString();
		if (method.equalsIgnoreCase("OPENGUI")) {
			int guiID = obj.get("GuiID").getAsInt();
			List<JsonObject> list = gson.fromJson(obj.get("Gui").getAsString(), new TypeToken<List<JsonObject>>() {
			}.getType());
			List<EGuiObject> eList = GuiUtil.backToObject(list);
			CustomGUIAPI.openGUI(eList, guiID);
		}
	}
}
