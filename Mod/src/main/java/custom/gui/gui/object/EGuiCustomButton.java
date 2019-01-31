package custom.gui.gui.object;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import custom.gui.gui.Gui;
import custom.gui.gui.GuiUtil;
import custom.gui.gui.object.mcobject.GuiCustomButton;

public class EGuiCustomButton implements EGuiObject {

    String str, firstUrl, lastUrl;
    int x, y, width, height, id;

    public EGuiCustomButton(JsonObject obj) {
        GuiUtil.writeInObject(new Gson().fromJson(obj, this.getClass()), this);
    }

    @Override
    public void draw(Gui gui) {
    }

    @Override
    public void init(Gui gui) {
        gui.EbuttonList.add(new GuiCustomButton(id, x, y, width, height, str, firstUrl, lastUrl));
    }
}
