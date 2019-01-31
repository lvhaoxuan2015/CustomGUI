package custom.gui.gui.object;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import custom.gui.gui.Gui;
import custom.gui.gui.GuiUtil;

public class EGuiString implements EGuiObject {

    String str;
    int x, y, color, id;

    public EGuiString(JsonObject obj) {
        GuiUtil.writeInObject(new Gson().fromJson(obj, this.getClass()), this);
    }

    @Override
    public void draw(Gui gui) {
        gui.drawString(gui.getFontRenderer(), str, x, y, color);
    }

    @Override
    public void init(Gui gui) {
    }

}
