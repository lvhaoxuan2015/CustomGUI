package custom.gui.gui.object;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import custom.gui.gui.Gui;
import custom.gui.gui.GuiUtil;
import custom.gui.networkgui.NetWorkGuiField;
import custom.gui.networkgui.NetWorkGuiString;
import net.minecraft.client.gui.GuiTextField;

public class EGuiField implements EGuiObject {

	public int id;
	public int x, y, width, height;
	public GuiTextField instance;

	public EGuiField(int id, int x, int y, int width, int height) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = x;
		this.height = height;
	}

	public EGuiField(String json) {
		Gson gson = new GsonBuilder().create();
		NetWorkGuiField in = gson.fromJson(json, NetWorkGuiField.class);
		GuiUtil.writeInObject(this, in);
	}

	@Override
	public void draw(Gui gui) {
		instance.drawTextBox();
	}

	@Override
	public void init(Gui gui) {
		instance = new GuiTextField(id, gui.getFontRenderer(), x, y, width, height);
		gui.GuiFieldList.add(instance);
	}

}
