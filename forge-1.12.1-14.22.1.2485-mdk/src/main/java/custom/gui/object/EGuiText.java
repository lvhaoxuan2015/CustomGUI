package custom.gui.object;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import custom.gui.util.GuiUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class EGuiText implements EGuiObject {

    public String str;
    public int x, y, color, id, width, height;
    public boolean wheel;

    public EGuiText(JsonObject obj) {
        GuiUtil.writeInObject(new Gson().fromJson(obj, this.getClass()), this);
        this.height = 16;
        this.width = str.length() * 16;
    }

    @Override
    public void draw() {
        Minecraft.getMinecraft().fontRenderer.drawString(str, x, y, color);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public void init() {
    }

}
