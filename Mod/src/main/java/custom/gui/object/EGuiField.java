package custom.gui.object;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import custom.gui.Gui;
import custom.gui.util.GuiUtil;
import net.minecraft.client.gui.GuiTextField;

public class EGuiField implements EGuiObject {

    public int x, y, width, height, id, maxStringLength;
    public GuiTextField instance;

    public EGuiField(JsonObject obj) {
        GuiUtil.writeInObject(new Gson().fromJson(obj, this.getClass()), this);
    }

    @Override
    public void draw(Gui gui) {
        instance.drawTextBox();
    }

    @Override
    public void init(Gui gui) {
        instance = new GuiTextField(id, gui.getFontRenderer(), x, y, width, height);
        instance.setMaxStringLength(maxStringLength);
        gui.GuiFieldList.add(instance);
    }

}
