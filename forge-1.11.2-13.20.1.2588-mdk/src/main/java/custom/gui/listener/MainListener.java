package custom.gui.listener;

import com.google.gson.JsonObject;

import custom.gui.CustomGUI;
import custom.gui.Gui;
import custom.gui.object.EGuiObject;
import io.netty.buffer.Unpooled;
import java.util.HashMap;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;

public class MainListener {

    public static HashMap<GuiType, List<EGuiObject>> map = new HashMap<>();
    public static HashMap<GuiType, Boolean> isInitMap = new HashMap<>();

    @SubscribeEvent
    public void GuiOpenEvent(GuiOpenEvent e) {
        if (e.getGui() instanceof Gui) {
            Gui gui = (Gui) e.getGui();
            JsonObject jo = new JsonObject();
            jo.addProperty("Method", "OPENCUSTOMGUI");
            jo.addProperty("GuiID", gui.guiID);
            CustomGUI.net.sendToServer(new FMLProxyPacket(
                    new PacketBuffer(Unpooled.wrappedBuffer((jo.toString()).getBytes())), CustomGUI.MODID));
        }
    }

    @SubscribeEvent
    public void RenderGameOverlayEventPre(Pre e) {
        if (e.getType() == ElementType.CROSSHAIRS) {
            try {
                GuiScreen currentScreen = Minecraft.getMinecraft().currentScreen;
                GuiType guiType;
                if (currentScreen == null) {
                    guiType = GuiType.InGame;
                } else {
                    String type = currentScreen.getClass().getSimpleName();
                    guiType = GuiType.valueOf(type);
                }
                List<EGuiObject> list = map.get(guiType);
                if (list != null) {
                    if (!isInitMap.get(guiType)) {
                        for (EGuiObject obj : list) {
                            obj.init();
                        }
                    }
                    for (EGuiObject obj : list) {
                        obj.draw();
                    }
                }
            } catch (IllegalArgumentException ex) {
            }
        }
    }

    public enum GuiType {
        GuiIngameMenu,
        GuiChat,
        GuiInventory,
        GuiContainerCreative,
        GuiChest,
        GuiCrafting,
        GuiFurnace,
        GuiEnchantment,
        GuiDispenser,
        GuiHopper,
        GuiDownloadTerrain,
        InGame
    }
}
