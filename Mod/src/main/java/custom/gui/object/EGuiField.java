package custom.gui.object;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import custom.gui.util.GuiUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiTextField;

public class EGuiField implements EGuiObject {

    public int x, y, width, height, id, maxStringLength;
    public GuiTextField instance;

    public EGuiField(JsonObject obj) {
        GuiUtil.writeInObject(new Gson().fromJson(obj, this.getClass()), this);
    }

    @Override
    public void draw() {
        instance.drawTextBox();
    }

    @Override
    public void init() {
        instance = new GuiTextField(id, Minecraft.getMinecraft().fontRendererObj, x, y, width, height);
        instance.setMaxStringLength(maxStringLength);
    }

}
