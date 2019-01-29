package custom.gui.gui.object;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import custom.gui.gui.Gui;
import custom.gui.gui.GuiUtil;
import custom.gui.networkgui.NetWorkGuiField;
import net.minecraft.client.gui.GuiTextField;

public class EGuiField extends EGuiObject {

	public int x, y, width, height, id;
	public GuiTextField instance;

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
