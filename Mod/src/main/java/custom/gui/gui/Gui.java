package custom.gui.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;

import custom.gui.CustomGUI;
import custom.gui.gui.object.EGuiObject;
import io.netty.buffer.Unpooled;
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
    public List<GuiButton> EbuttonList = new ArrayList<>();
    public List<GuiTextField> GuiFieldList = new ArrayList<>();
    public int guiID;
    
    public Gui(GuiScreen old, List<EGuiObject> list, int guiID) {
        this.old = old;
        objList = list;
        EbuttonList = buttonList;
        this.guiID = guiID;
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
        drawDefaultBackground();
        for (EGuiObject obj : objList) {
            obj.draw(this);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    public FontRenderer getFontRenderer() {
        return fontRendererObj;
    }
    
    @Override
    public void initGui() {
        for (EGuiObject obj : objList) {
            obj.init(this);
        }
    }
    
    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        for (GuiTextField egf : GuiFieldList) {
            if (egf.textboxKeyTyped(typedChar, keyCode)) {
                CustomGUIAPI.noticeServerChangeValue(egf.getId(), egf.getText());
            }
        }
        super.keyTyped(typedChar, keyCode);
    }
    
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        for (GuiTextField egf : GuiFieldList) {
            egf.mouseClicked(mouseX, mouseY, mouseButton);
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
    
    public void close() {
        Minecraft.getMinecraft().displayGuiScreen(old);
    }
}
