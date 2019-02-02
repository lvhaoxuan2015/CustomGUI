package custom.gui.api;

import custom.gui.Gui;
import java.util.List;

import com.google.gson.JsonObject;

import custom.gui.CustomGUI;
import custom.gui.listener.MainListener;
import custom.gui.listener.MainListener.GuiType;
import custom.gui.object.EGuiObject;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;

public class CustomGUIAPI {

    public static void noticeServerChangeValue(String id, String value) {
        JsonObject jo = new JsonObject();
        jo.addProperty("Method", "CHANGEVALUE");
        jo.addProperty("ID", id);
        jo.addProperty("Value", value);
        CustomGUI.net.sendToServer(new FMLProxyPacket(
                new PacketBuffer(Unpooled.wrappedBuffer((jo.toString()).getBytes())), CustomGUI.MODID));
    }

    public static void openGUI(List<EGuiObject> list, int guiID, boolean useDefaultBackground) {
        Minecraft.getMinecraft().displayGuiScreen(new Gui(Minecraft.getMinecraft().currentScreen, list, guiID, useDefaultBackground));
    }

    public static void implantationGUI(List<EGuiObject> list, String guiType) {
        MainListener.map.put(GuiType.valueOf(guiType), list);
        MainListener.isInitMap.put(GuiType.valueOf(guiType), false);
    }
}
