package custom.gui.gui.object;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import custom.gui.gui.Gui;
import custom.gui.gui.GuiUtil;
import custom.gui.networkgui.NetWorkGuiButton;
import net.minecraft.client.gui.GuiButton;

public class EGuiButton extends EGuiObject {

	String str;
	int x, y, width, height, id;

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
		gui.EbuttonList.add(new GuiButton(id, x, y, width, height, str));
	}
}
