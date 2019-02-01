package custom.gui.object;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import custom.gui.util.GuiUtil;
import net.minecraft.client.Minecraft;

public class EGuiText implements EGuiObject {

    public String str;
    public int x, y, color, id;

    public EGuiText(JsonObject obj) {
        GuiUtil.writeInObject(new Gson().fromJson(obj, this.getClass()), this);
    }

    @Override
    public void draw() {
        Minecraft.getMinecraft().fontRendererObj.drawString(str, x, y, color);
    }

    @Override
    public void init() {
    }

}
