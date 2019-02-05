package custom.gui.api;

import custom.gui.Gui;
import java.util.List;

import com.google.gson.JsonObject;

import custom.gui.CustomGUI;
import custom.gui.listener.MainListener;
import custom.gui.listener.MainListener.GuiType;
import custom.gui.object.EGuiButton;
import custom.gui.object.EGuiCustomButton;
import custom.gui.object.EGuiCustomField;
import custom.gui.object.EGuiField;
import custom.gui.object.EGuiObject;
import io.netty.buffer.Unpooled;
import java.util.ArrayList;
import java.util.HashMap;
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

    public static void openGUI(List<EGuiObject> list, int guiID, int wheelSpeed, boolean useDefaultBackground) {
        Minecraft.getMinecraft().setIngameNotInFocus();
        Minecraft.getMinecraft().displayGuiScreen(new Gui(Minecraft.getMinecraft().currentScreen, list, guiID, wheelSpeed, useDefaultBackground));
    }

    public static void implantationGUI(List<EGuiObject> list, String guiType) {
        MainListener.map.put(GuiType.valueOf(guiType), list);
        MainListener.guiFieldListMap.put(GuiType.valueOf(guiType), new ArrayList<>());
        MainListener.guiCustomFieldListMap.put(GuiType.valueOf(guiType), new ArrayList<>());
        MainListener.guiButtonListMap.put(GuiType.valueOf(guiType), new ArrayList<>());
        MainListener.variablesMap.put(GuiType.valueOf(guiType), new HashMap<>());
        MainListener.initMap.put(GuiType.valueOf(guiType), false);
        for (EGuiObject obj : list) {
            obj.init();
            if (obj instanceof EGuiCustomButton) {
                MainListener.guiButtonListMap.get(GuiType.valueOf(guiType)).add(((EGuiCustomButton) obj).instance);
            } else if (obj instanceof EGuiButton) {
                MainListener.guiButtonListMap.get(GuiType.valueOf(guiType)).add(((EGuiButton) obj).instance);
            } else if (obj instanceof EGuiField) {
                MainListener.guiFieldListMap.get(GuiType.valueOf(guiType)).add(((EGuiField) obj).instance);
            } else if (obj instanceof EGuiCustomField) {
                MainListener.guiCustomFieldListMap.get(GuiType.valueOf(guiType)).add(((EGuiCustomField) obj).instance);
            }
        }
    }
}
