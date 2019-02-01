package custom.gui.object;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import custom.gui.util.GuiUtil;
import custom.gui.mcobject.GuiCustomButton;
import net.minecraft.client.Minecraft;

public class EGuiCustomButton implements EGuiObject {

    public String str, firstUrl, lastUrl;
    public int x, y, width, height, id, firstColor, lastColor;
    public GuiCustomButton instance;

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
    public void draw() {
        instance.drawButton(Minecraft.getMinecraft(), Minecraft.getMinecraft().mouseHelper.deltaX, Minecraft.getMinecraft().mouseHelper.deltaY);
    }

    @Override
    public void init() {
        instance = new GuiCustomButton(id, x, y, width, height, firstColor, lastColor, str, firstUrl, lastUrl);
    }
}
