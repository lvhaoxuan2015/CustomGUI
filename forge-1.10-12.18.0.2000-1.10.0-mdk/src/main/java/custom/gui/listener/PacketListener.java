package custom.gui.listener;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import custom.gui.CustomGUI;

import custom.gui.api.CustomGUIAPI;
import custom.gui.util.DownloadGifThread;
import custom.gui.util.DownloadImageThread;
import custom.gui.Gui;
import custom.gui.util.GuiUtil;
import custom.gui.object.EGuiObject;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
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
        Gson gson = new Gson();
        JsonObject obj = gson.fromJson(str, JsonObject.class);
        String method = obj.get("Method").getAsString();
        if (method.equalsIgnoreCase("OPENGUI")) {
            int guiID = obj.get("GuiID").getAsInt();
            boolean useDefaultBackground = obj.get("UseDefaultBackground").getAsBoolean();
            List<JsonObject> list = gson.fromJson(obj.get("Gui").getAsString(), new TypeToken<List<JsonObject>>() {
            }.getType());
            List<EGuiObject> eList = GuiUtil.backToObject(list);
            CustomGUIAPI.openGUI(eList, guiID, useDefaultBackground);
        } else if (method.equalsIgnoreCase("CLOSENOWGUI")) {
            if (Minecraft.getMinecraft().currentScreen instanceof Gui) {
                Gui gui = (Gui) Minecraft.getMinecraft().currentScreen;
                JsonObject jo = new JsonObject();
                jo.addProperty("Method", "CLOSEGUI");
                jo.addProperty("GuiID", gui.guiID);
                CustomGUI.net.sendToServer(new FMLProxyPacket(
                        new PacketBuffer(Unpooled.wrappedBuffer((jo.toString()).getBytes())), CustomGUI.MODID));
                gui.close();
            }
        } else if (method.equalsIgnoreCase("DOWNLOADTEXTURE")) {
            List<String> imageUrls = gson.fromJson(obj.get("ImageUrls").getAsString(), new TypeToken<List<String>>() {
            }.getType());
            List<String> gifUrls = gson.fromJson(obj.get("GifUrls").getAsString(), new TypeToken<List<String>>() {
            }.getType());
            for (String url : imageUrls) {
                new DownloadImageThread(url).start();
            }
            for (String url : gifUrls) {
                new DownloadGifThread(url).start();
            }
        } else if (method.equalsIgnoreCase("IMPLANTATIONGUI")) {
            List<JsonObject> list = gson.fromJson(obj.get("Gui").getAsString(), new TypeToken<List<JsonObject>>() {
            }.getType());
            List<EGuiObject> eList = GuiUtil.backToObject(list);
            CustomGUIAPI.implantationGUI(eList, obj.get("GuiType").getAsString());
        }
    }
}
