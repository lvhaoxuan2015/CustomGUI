package custom.gui.networkgui;

public class NetWorkGuiString extends NetWorkGuiObject {

	public String str;
	public int x, y, color, id;

	public NetWorkGuiString(String str, int id, int x, int y, int color) {
		this.id = id;
		this.str = str;
		this.x = x;
		this.y = y;
		this.color = color;
		type = "GuiString";
	}

}
