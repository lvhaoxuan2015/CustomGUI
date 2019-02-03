package custom.gui.object;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import custom.gui.util.GuiUtil;
import java.awt.image.BufferedImage;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import org.lwjgl.opengl.GL11;

public class EGuiUrlGif implements EGuiObject {

    public String path;
    public int x, y, textureX, textureY, width, height, id, speed, frameNum = 0, counter = 0, length;
    public float textureWidth, textureHeight;
    public boolean[] isuploadTextureImage;
    public int[] textureIDs;
    public BufferedImage[] imgs;
    public boolean wheel;

    public EGuiUrlGif(JsonObject obj) {
        GuiUtil.writeInObject(new Gson().fromJson(obj, this.getClass()), this);
        if (TextureManager.gifUrls.get(path) == null) {
            TextureManager.downloadGif(path);
        }
        length = TextureManager.gifUrls.get(path).length;
        isuploadTextureImage = new boolean[length];
        textureIDs = new int[length];
        imgs = TextureManager.gifUrls.get(path);
    }

    @Override
    public void draw() {
        counter++;
        if (counter == speed) {
            frameNum++;
            counter = 0;
        }
        if (frameNum == length) {
            frameNum = 0;
        }
        try {
            if (!isuploadTextureImage[frameNum]) {
                textureIDs[frameNum] = GL11.glGenTextures();
                TextureUtil.uploadTextureImage(textureIDs[frameNum], imgs[frameNum]);
                isuploadTextureImage[frameNum] = true;
            }
            GlStateManager.bindTexture(textureIDs[frameNum]);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GuiUtil.drawModalRectWithCustomSizedTexture(x, y, textureX, textureY, width, height, textureWidth, textureHeight);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }

    @Override
    public void init() {
    }

}
