package custom.gui.gui.object;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import custom.gui.gui.Gui;
import custom.gui.gui.GuiUtil;
import net.minecraft.client.gui.GuiButton;

public class EGuiButton implements EGuiObject {

    String str;
    int x, y, width, height, id;

    public EGuiButton(JsonObject obj) {
        GuiUtil.writeInObject(new Gson().fromJson(obj, this.getClass()), this);
    }

    @Override
    public void draw(Gui gui) {
    }

    @Override
    public void init(Gui gui) {
        gui.EbuttonList.add(new GuiButton(id, x, y, width, height, str));
    }
}
