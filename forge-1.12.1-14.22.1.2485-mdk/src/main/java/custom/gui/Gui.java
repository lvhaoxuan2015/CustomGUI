package custom.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;

import custom.gui.object.*;
import custom.gui.mcobject.*;
import custom.gui.api.CustomGUIAPI;
import custom.gui.object.EGuiCustomButton;
import io.netty.buffer.Unpooled;
import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Gui extends GuiScreen {

    public GuiScreen old;
    public List<EGuiObject> objList = new ArrayList<>();
    public List<GuiButton> GuiButtonList = new ArrayList<>();
    public List<GuiTextField> GuiFieldList = new ArrayList<>();
    public List<GuiCustomField> GuiCustomFieldList = new ArrayList<>();
    public int guiID, oldWidth, oldHeight;
    public boolean useDefaultBackground;
    HashMap<String, String> variablesMap = new HashMap<>();

    public Gui(GuiScreen old, List<EGuiObject> list, int guiID, boolean useDefaultBackground) {
        this.old = old;
        objList = list;
        GuiButtonList = buttonList;
        this.guiID = guiID;
        oldWidth = width;
        oldHeight = height;
        this.useDefaultBackground = useDefaultBackground;
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        JsonObject jo = new JsonObject();
        jo.addProperty("Method", "CLICKCUSTOMGUIBUTTON");
        jo.addProperty("GuiID", guiID);
        jo.addProperty("ButtonID", button.id);
        CustomGUI.net.sendToServer(new FMLProxyPacket(
                new PacketBuffer(Unpooled.wrappedBuffer((jo.toString()).getBytes())), CustomGUI.MODID));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        if (this.width != oldWidth) {
            CustomGUIAPI.noticeServerChangeValue(guiID + ":width", String.valueOf(width));
            this.oldWidth = width;
        }
        if (this.height != oldHeight) {
            CustomGUIAPI.noticeServerChangeValue(guiID + ":height", String.valueOf(height));
            this.oldHeight = height;
        }
        if (useDefaultBackground) {
            drawDefaultBackground();
        }
        for (EGuiObject obj : objList) {
            obj.draw();
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public FontRenderer getFontRenderer() {
        return this.fontRenderer;
    }

    @Override
    public void initGui() {
        for (EGuiObject obj : objList) {
            obj.init();
            if (obj instanceof EGuiCustomButton) {
                GuiButtonList.add(((EGuiCustomButton) obj).instance);
            } else if (obj instanceof EGuiButton) {
                GuiButtonList.add(((EGuiButton) obj).instance);
            } else if (obj instanceof EGuiField) {
                GuiFieldList.add(((EGuiField) obj).instance);
            } else if (obj instanceof EGuiCustomField) {
                GuiCustomFieldList.add(((EGuiCustomField) obj).instance);
            }
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        for (GuiTextField egf : GuiFieldList) {
            if (egf.textboxKeyTyped(typedChar, keyCode)) {
                if (variablesMap.get(String.valueOf(egf.getId())) == null) {
                    variablesMap.put(String.valueOf(egf.getId()), egf.getText());
                    CustomGUIAPI.noticeServerChangeValue(String.valueOf(egf.getId()), egf.getText());
                }
                if (!variablesMap.get(String.valueOf(egf.getId())).equalsIgnoreCase(egf.getText())) {
                    variablesMap.put(String.valueOf(egf.getId()), egf.getText());
                    CustomGUIAPI.noticeServerChangeValue(String.valueOf(egf.getId()), egf.getText());
                }
            }
        }
        for (GuiCustomField egf : GuiCustomFieldList) {
            if (egf.textboxKeyTyped(typedChar, keyCode)) {
                if (variablesMap.get(String.valueOf(egf.getId())) == null) {
                    variablesMap.put(String.valueOf(egf.getId()), egf.getText());
                    CustomGUIAPI.noticeServerChangeValue(String.valueOf(egf.getId()), egf.getText());
                }
                if (!variablesMap.get(String.valueOf(egf.getId())).equalsIgnoreCase(egf.getText())) {
                    variablesMap.put(String.valueOf(egf.getId()), egf.getText());
                    CustomGUIAPI.noticeServerChangeValue(String.valueOf(egf.getId()), egf.getText());
                }
            }
        }
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        for (GuiTextField egf : GuiFieldList) {
            egf.mouseClicked(mouseX, mouseY, mouseButton);
        }
        for (GuiCustomField egf : GuiCustomFieldList) {
            egf.mouseClicked(mouseX, mouseY, mouseButton);
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    public void close() {
        Minecraft.getMinecraft().displayGuiScreen(old);
    }
}
