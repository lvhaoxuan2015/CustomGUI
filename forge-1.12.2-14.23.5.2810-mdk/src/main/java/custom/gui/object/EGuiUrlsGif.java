package custom.gui.object;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import custom.gui.util.GuiUtil;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import org.lwjgl.opengl.GL11;

public class EGuiUrlsGif implements EGuiObject {

    public String[] paths;
    public int x, y, textureX, textureY, width, height, id, speed, frameNum = 0, counter = 0;
    public boolean[] isuploadTextureImage;
    public int[] textureIDs;
    public boolean wheel;

    public EGuiUrlsGif(JsonObject obj) {
        GuiUtil.writeInObject(new Gson().fromJson(obj, this.getClass()), this);
        for (String path : paths) {
            if (TextureManager.imageUrls.get(path) == null) {
                TextureManager.downloadImage(path);
            }
        }
    }

    @Override
    public void draw() {
        counter++;
        if (counter == speed) {
            frameNum++;
            counter = 0;
        }
        if (frameNum == paths.length) {
            frameNum = 0;
        }
        if (!isuploadTextureImage[frameNum]) {
            textureIDs[frameNum] = GL11.glGenTextures();
            TextureUtil.uploadTextureImage(textureIDs[frameNum], TextureManager.getBufferedImage(paths[frameNum]));
            isuploadTextureImage[frameNum] = true;
        }
        GlStateManager.bindTexture(textureIDs[frameNum]);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GuiUtil.drawModalRectWithCustomSizedTexture(x, y, textureX, textureY, width, height, width,
                height);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public void init() {
        isuploadTextureImage = new boolean[paths.length];
        textureIDs = new int[paths.length];
    }

}
