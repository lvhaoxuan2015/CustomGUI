package custom.gui.gui.object;

import org.lwjgl.opengl.GL11;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import custom.gui.gui.Gui;
import custom.gui.gui.GuiUtil;
import custom.gui.networkgui.NetWorkGuiImage;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureUtil;

public class EGuiImage extends EGuiObject {
	String path;
	int x, y, textureX, textureY, width, height, id;

	public EGuiImage(String json) {
		Gson gson = new GsonBuilder().create();
		NetWorkGuiImage in = gson.fromJson(json, NetWorkGuiImage.class);
		GuiUtil.writeInObject(this, in);
	}

	@Override
	public void draw(Gui gui) {
		int textureID = TextureUtil.glGenTextures();
		TextureUtil.uploadTextureImage(textureID, TextureManager.getBufferedImage(path));
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		net.minecraft.client.gui.Gui.drawModalRectWithCustomSizedTexture(x, y, 0, 0, width / 2, height / 2, width / 2,
				height / 2);
	}

	@Override
	public void init(Gui gui) {
	}
}
