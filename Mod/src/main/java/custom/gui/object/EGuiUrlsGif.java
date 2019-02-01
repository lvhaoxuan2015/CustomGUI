package custom.gui.object;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import custom.gui.Gui;
import custom.gui.util.GuiUtil;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import org.lwjgl.opengl.GL11;

public class EGuiUrlsGif implements EGuiObject {

    String[] paths;
    int x, y, textureX, textureY, width, height, id, speed, frameNum = 0, counter = 0;
    boolean[] isuploadTextureImage;
    int[] textureIDs;

    public EGuiUrlsGif(JsonObject obj) {
        GuiUtil.writeInObject(new Gson().fromJson(obj, this.getClass()), this);
        for (String path : paths) {
            if (TextureManager.imageUrls.get(path) == null) {
                TextureManager.downloadImage(path);
            }
        }
    }

    @Override
    public void draw(Gui gui) {
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
        net.minecraft.client.gui.Gui.drawModalRectWithCustomSizedTexture(x, y, textureX, textureY, width, height, width,
                height);
    }

    @Override
    public void init(Gui gui) {
        isuploadTextureImage = new boolean[paths.length];
        textureIDs = new int[paths.length];
    }

}
