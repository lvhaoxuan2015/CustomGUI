package custom.gui.object;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import custom.gui.Gui;
import custom.gui.util.GuiUtil;
import custom.gui.mcobject.GuiCustomButton;

public class EGuiCustomButton implements EGuiObject {

    String str, firstUrl, lastUrl;
    int x, y, width, height, id, firstColor, lastColor;

    public EGuiCustomButton(JsonObject obj) {
        GuiUtil.writeInObject(new Gson().fromJson(obj, this.getClass()), this);
        if (TextureManager.imageUrls.get(firstUrl) == null) {
            TextureManager.downloadImage(firstUrl);
        }
        if (TextureManager.imageUrls.get(lastUrl) == null) {
            TextureManager.downloadImage(lastUrl);
        }
    }

    @Override
    public void draw(Gui gui) {
    }

    @Override
    public void init(Gui gui) {
        gui.EbuttonList.add(new GuiCustomButton(id, x, y, width, height, firstColor, lastColor, str, firstUrl, lastUrl));
    }
}
