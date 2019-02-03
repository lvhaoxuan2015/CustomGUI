package custom.gui.object;

import org.lwjgl.opengl.GL11;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import custom.gui.util.GuiUtil;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureUtil;

public class EGuiImage implements EGuiObject {

    public String path;
    public int x, y, textureX, textureY, width, height, id, textureID;
    public boolean isuploadTextureImage = false;
    public boolean wheel;

    public EGuiImage(JsonObject obj) {
        GuiUtil.writeInObject(new Gson().fromJson(obj, this.getClass()), this);
        if (TextureManager.imageUrls.get(path) == null) {
            TextureManager.downloadImage(path);
        }
    }

    @Override
    public void draw() {
        if (!isuploadTextureImage) {
            textureID = GL11.glGenTextures();
            TextureUtil.uploadTextureImage(textureID, TextureManager.getBufferedImage(path));
            isuploadTextureImage = true;
        }
        GlStateManager.bindTexture(textureID);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GuiUtil.drawModalRectWithCustomSizedTexture(x, y, textureX, textureY, width, height, width, height);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public void init() {
    }
}
