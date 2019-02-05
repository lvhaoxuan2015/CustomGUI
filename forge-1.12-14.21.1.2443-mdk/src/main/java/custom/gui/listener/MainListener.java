package custom.gui.listener;

import com.google.gson.JsonObject;

import custom.gui.CustomGUI;
import custom.gui.Gui;
import custom.gui.api.CustomGUIAPI;
import static custom.gui.listener.MainListener.guiCustomFieldListMap;
import static custom.gui.listener.MainListener.guiFieldListMap;
import custom.gui.mcobject.GuiCustomField;
import custom.gui.object.EGuiObject;
import io.netty.buffer.Unpooled;
import java.util.HashMap;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class MainListener {

    public static HashMap<GuiType, List<EGuiObject>> map = new HashMap<>();
    public static HashMap<GuiType, List<GuiTextField>> guiFieldListMap = new HashMap<>();
    public static HashMap<GuiType, List<GuiButton>> guiButtonListMap = new HashMap<>();
    public static HashMap<GuiType, List<GuiCustomField>> guiCustomFieldListMap = new HashMap<>();
    public static HashMap<GuiType, HashMap<String, String>> variablesMap = new HashMap<>();
    public static HashMap<GuiType, Boolean> initMap = new HashMap<>();

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
    public void RenderGameOverlayEvent(RenderGameOverlayEvent.Post e) {
        if (e.getType() == ElementType.CROSSHAIRS) {
            try {
                GuiScreen currentScreen = Minecraft.getMinecraft().currentScreen;
                if (currentScreen == null) {
                    GuiType guiType = GuiType.valueOfGuiScreen(currentScreen);
                    List<EGuiObject> list = map.get(guiType);
                    if (list != null) {
                        for (EGuiObject obj : list) {
                            obj.draw(getMouseX(currentScreen), getMouseY(currentScreen), Minecraft.getMinecraft().getRenderPartialTicks());
                        }
                    }
                }
            } catch (IllegalArgumentException ex) {
            }
        }
    }

    @SubscribeEvent
    public void BackgroundDrawnEvent(DrawScreenEvent.BackgroundDrawnEvent e) {
        GuiScreen currentScreen = e.getGui();
        try {
            GuiType guiType = GuiType.valueOfGuiScreen(currentScreen);
            List<EGuiObject> list = map.get(guiType);
            if (list != null) {
                for (EGuiObject obj : list) {
                    obj.draw(getMouseX(currentScreen), getMouseY(currentScreen), Minecraft.getMinecraft().getRenderPartialTicks());
                }
            }
        } catch (IllegalArgumentException ex) {
        }
    }

    @SubscribeEvent
    public void KeyboardInputEvent(GuiScreenEvent.KeyboardInputEvent.Post e) {
        char c = Keyboard.getEventCharacter();
        if (Keyboard.getEventKey() == 0 && c >= 32 || Keyboard.getEventKeyState()) {
            GuiScreen currentScreen = e.getGui();
            try {
                GuiType guiType = GuiType.valueOfGuiScreen(currentScreen);
                List<GuiTextField> guiFieldList = guiFieldListMap.get(guiType);
                List<GuiCustomField> guiCustomFieldList = guiCustomFieldListMap.get(guiType);
                if (guiFieldList != null) {
                    for (GuiTextField egf : guiFieldList) {
                        if (egf.textboxKeyTyped(c, Keyboard.getEventKey())) {
                            if (variablesMap.get(guiType).get(String.valueOf(egf.getId())) == null) {
                                variablesMap.get(guiType).put(String.valueOf(egf.getId()), egf.getText());
                                CustomGUIAPI.noticeServerChangeValue(String.valueOf(egf.getId()), egf.getText());
                            }
                            if (!variablesMap.get(guiType).get(String.valueOf(egf.getId())).equalsIgnoreCase(egf.getText())) {
                                variablesMap.get(guiType).put(String.valueOf(egf.getId()), egf.getText());
                                CustomGUIAPI.noticeServerChangeValue(String.valueOf(egf.getId()), egf.getText());
                            }
                        }
                    }
                }
                if (guiCustomFieldList != null) {
                    for (GuiCustomField egf : guiCustomFieldList) {
                        if (egf.textboxKeyTyped(c, Keyboard.getEventKey())) {
                            if (variablesMap.get(guiType).get(String.valueOf(egf.getId())) == null) {
                                variablesMap.get(guiType).put(String.valueOf(egf.getId()), egf.getText());
                                CustomGUIAPI.noticeServerChangeValue(String.valueOf(egf.getId()), egf.getText());
                            }
                            if (!variablesMap.get(guiType).get(String.valueOf(egf.getId())).equalsIgnoreCase(egf.getText())) {
                                variablesMap.get(guiType).put(String.valueOf(egf.getId()), egf.getText());
                                CustomGUIAPI.noticeServerChangeValue(String.valueOf(egf.getId()), egf.getText());
                            }
                        }
                    }
                }
            } catch (IllegalArgumentException ex) {
            }
        }
    }

    @SubscribeEvent
    public void MouseInputEvent(GuiScreenEvent.MouseInputEvent.Post e) {
        int x = getMouseX(e.getGui());
        int y = getMouseY(e.getGui());
        int k = Mouse.getEventButton();
        GuiScreen currentScreen = e.getGui();
        try {
            GuiType guiType = GuiType.valueOfGuiScreen(currentScreen);
            List<GuiTextField> guiFieldList = guiFieldListMap.get(guiType);
            List<GuiCustomField> guiCustomFieldList = guiCustomFieldListMap.get(guiType);
            List<GuiButton> guiButtonList = guiButtonListMap.get(guiType);
            if (Mouse.isButtonDown(0)) {
                if (guiFieldList != null) {
                    for (GuiTextField egf : guiFieldList) {
                        egf.mouseClicked(x, y, k);
                    }
                }
                if (guiCustomFieldList != null) {
                    for (GuiCustomField egf : guiCustomFieldList) {
                        egf.mouseClicked(x, y, k);
                    }
                }
                if (guiButtonList != null) {
                    for (GuiButton egf : guiButtonList) {
                        if (egf.mousePressed(Minecraft.getMinecraft(), x, y)) {
                            JsonObject jo = new JsonObject();
                            jo.addProperty("Method", "CLICKIMPLANTATIONGUIBUTTON");
                            jo.addProperty("GuiType", guiType.name());
                            jo.addProperty("ButtonID", egf.id);
                            CustomGUI.net.sendToServer(new FMLProxyPacket(
                                    new PacketBuffer(Unpooled.wrappedBuffer((jo.toString()).getBytes())), CustomGUI.MODID));
                        }
                    }
                }
            }
        } catch (IllegalArgumentException ex) {
        }
    }

    @SubscribeEvent
    public void InitGuiEvent(GuiScreenEvent.InitGuiEvent.Post e) {
        GuiScreen currentScreen = e.getGui();
        try {
            GuiType guiType = GuiType.valueOfGuiScreen(currentScreen);
            if (initMap.get(guiType) == null) {
                List<EGuiObject> list = map.get(guiType);
                if (list != null) {
                    for (EGuiObject obj : list) {
                        obj.init();
                    }
                }
                initMap.put(guiType, true);
            }
        } catch (IllegalArgumentException ex) {
        }
    }

    public static int getMouseX(GuiScreen gui) {
        if (gui != null) {
            return Mouse.getEventX() * gui.width / gui.mc.displayWidth;
        }
        return -1;
    }

    public static int getMouseY(GuiScreen gui) {
        if (gui != null) {
            return gui.height - Mouse.getEventY() * gui.height / gui.mc.displayHeight - 1;
        }
        return -1;
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
        InGame;

        public static GuiType valueOfGuiScreen(GuiScreen gui) {
            if (gui == null) {
                return GuiType.InGame;
            }
            return GuiType.valueOf(gui.getClass().getSimpleName());
        }
    }
}
