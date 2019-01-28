package custom.gui.gui.object;

import java.lang.reflect.Field;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import custom.gui.gui.Gui;
import custom.gui.gui.GuiUtil;
import custom.gui.networkgui.NetWorkGuiButton;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class EGuiButton implements EGuiObject {

	String str;
	int buttonid;
	int x, y, width, height;

	public EGuiButton(String str, int buttonid, int x, int y) {
		this.str = str;
		this.buttonid = buttonid;
		this.x = x;
		this.y = y;
	}

	public EGuiButton(String json) {
		Gson gson = new GsonBuilder().create();
		NetWorkGuiButton in = gson.fromJson(json, NetWorkGuiButton.class);
		GuiUtil.writeInObject(this, in);
	}

	@Override
	public void draw(Gui gui) {
	}

	@Override
	public void init(Gui gui) {
		gui.EbuttonList.add(new GuiButton(buttonid, x, y, width, height, str));
	}
}
