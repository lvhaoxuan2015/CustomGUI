package custom.gui.object;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import custom.gui.util.GuiUtil;
import custom.gui.mcobject.GuiCustomField;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class EGuiCustomField implements EGuiObject {

    public int x, y, width, height, id, maxStringLength, textureX, textureY;
    public GuiCustomField instance;
    public float textureWidth, textureHeight;
    public String url;
    public boolean wheel;

    public EGuiCustomField(JsonObject obj) {
        GuiUtil.writeInObject(new Gson().fromJson(obj, this.getClass()), this);
        if (TextureManager.imageUrls.get(url) == null) {
            TextureManager.downloadImage(url);
        }
    }

    @Override
    public void draw() {
        instance.x = x;
        instance.y = y;
        instance.width = width;
        instance.height = height;
        instance.drawTextBox();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public void init() {
        instance = new GuiCustomField(id, Minecraft.getMinecraft().fontRenderer, x, y, width, height, textureX, textureY, textureWidth, textureHeight, url);
        instance.setMaxStringLength(maxStringLength);
    }

}
