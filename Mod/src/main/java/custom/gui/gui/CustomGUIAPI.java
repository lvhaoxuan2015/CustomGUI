package custom.gui.gui;

import java.util.List;

import com.google.gson.JsonObject;

import custom.gui.CustomGUI;
import custom.gui.gui.object.EGuiObject;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;

public class CustomGUIAPI {

    public static void noticeServerChangeValue(int id, String value) {
        JsonObject jo = new JsonObject();
        jo.addProperty("Method", "CHANGEVALUE");
        jo.addProperty("ID", id);
        jo.addProperty("Value", value);
        CustomGUI.net.sendToServer(new FMLProxyPacket(
                new PacketBuffer(Unpooled.wrappedBuffer((jo.toString()).getBytes())), CustomGUI.MODID));
    }

    public static void openGUI(List<EGuiObject> list, int guiID) {
        Minecraft.getMinecraft().displayGuiScreen(new Gui(Minecraft.getMinecraft().currentScreen, list, guiID));
    }
}
