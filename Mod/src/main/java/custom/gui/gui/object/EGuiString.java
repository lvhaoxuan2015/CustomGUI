package custom.gui.gui.object;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import custom.gui.gui.Gui;
import custom.gui.gui.GuiUtil;
import custom.gui.networkgui.NetWorkGuiString;

public class EGuiString extends EGuiObject {

	String str;
	int x, y, color, id;

	public EGuiString(String json) {
		Gson gson = new GsonBuilder().create();
		NetWorkGuiString in = gson.fromJson(json, NetWorkGuiString.class);
		GuiUtil.writeInObject(this, in);
	}

	@Override
	public void draw(Gui gui) {
		gui.drawString(gui.getFontRenderer(), str, x, y, color);
	}

	@Override
	public void init(Gui gui) {
	}

}
