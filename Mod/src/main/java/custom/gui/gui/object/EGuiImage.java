package custom.gui.gui.object;

import org.lwjgl.opengl.GL11;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import custom.gui.gui.Gui;
import custom.gui.gui.GuiUtil;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureUtil;

public class EGuiImage implements EGuiObject {

    String path;
    int x, y, textureX, textureY, width, height, id, textureID;
    boolean isuploadTextureImage = false;

    public EGuiImage(JsonObject obj) {
        GuiUtil.writeInObject(new Gson().fromJson(obj, this.getClass()), this);
    }

    @Override
    public void draw(Gui gui) {
        if (!isuploadTextureImage) {
            textureID = GL11.glGenTextures();
            TextureUtil.uploadTextureImage(textureID, TextureManager.getBufferedImage(path));
            isuploadTextureImage = true;
        }
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        net.minecraft.client.gui.Gui.drawModalRectWithCustomSizedTexture(x, y, textureX, textureY, width, height, width,
                height);
    }

    @Override
    public void init(Gui gui) {
    }
}
