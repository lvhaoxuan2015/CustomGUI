package custom.gui.gui.object;

import java.lang.reflect.Field;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import custom.gui.CustomGUI;
import custom.gui.gui.Gui;
import custom.gui.gui.GuiUtil;
import custom.gui.networkgui.NetWorkGuiButton;
import custom.gui.networkgui.NetWorkGuiImage;
import custom.gui.networkgui.NetWorkGuiObject;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class EGuiImage implements EGuiObject {
	ResourceLocation r;
	String path;
	int x, y, textureX, textureY, width, height;

	public EGuiImage(ResourceLocation r) {
		this.r = r;
	}

	public EGuiImage(String json) {
		Gson gson = new GsonBuilder().create();
		NetWorkGuiImage in = gson.fromJson(json, NetWorkGuiImage.class);
		GuiUtil.writeInObject(this, in);
		this.r = new ResourceLocation(CustomGUI.MODID, path);
	}

	@Override
	public void draw(Gui gui) {
		gui.mc.getTextureManager().bindTexture(r);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		gui.drawModalRectWithCustomSizedTexture(x, y, 0, 0, width, height, width, height);
	}

	@Override
	public void init(Gui gui) {
	}
}
