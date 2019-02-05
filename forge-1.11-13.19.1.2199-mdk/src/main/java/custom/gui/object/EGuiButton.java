package custom.gui.object;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import custom.gui.util.GuiUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

public class EGuiButton implements EGuiObject {

    public String str;
    public int x, y, width, height, id;
    public GuiButton instance;
    public boolean wheel;

    public EGuiButton(JsonObject obj) {
        GuiUtil.writeInObject(new Gson().fromJson(obj, this.getClass()), this);
    }

    @Override
    public void draw(int mouseX, int mouseY, float partialTicks) {
        instance.xPosition = x;
        instance.yPosition = y;
        instance.displayString = str;
        instance.width = width;
        instance.height = height;
        instance.drawButton(Minecraft.getMinecraft(), mouseX, mouseY);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public void init() {
        instance = new GuiButton(id, x, y, width, height, str);
    }
}
