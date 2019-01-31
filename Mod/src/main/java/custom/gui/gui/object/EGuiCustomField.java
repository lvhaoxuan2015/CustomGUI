package custom.gui.gui.object;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import custom.gui.gui.Gui;
import custom.gui.gui.GuiUtil;
import custom.gui.gui.object.mcobject.GuiCustomField;

public class EGuiCustomField implements EGuiObject {

    int x, y, width, height, id, maxStringLength, textureX, textureY;
    GuiCustomField instance;
    String url;

    public EGuiCustomField(JsonObject obj) {
        GuiUtil.writeInObject(new Gson().fromJson(obj, this.getClass()), this);
    }

    @Override
    public void draw(Gui gui) {
        instance.drawTextBox();
    }

    @Override
    public void init(Gui gui) {
        instance = new GuiCustomField(id, gui.getFontRenderer(), x, y, width, height, textureX, textureY, url);
        instance.setMaxStringLength(maxStringLength);
        gui.GuiCustomFieldList.add(instance);
    }

}
