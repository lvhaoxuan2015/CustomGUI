package custom.gui.networkgui;

public class NetWorkGuiField extends NetWorkGuiObject {
	public int id;
	public int x, y, width, height;

	public NetWorkGuiField(int id, int x, int y, int width, int height) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		type = "GuiField";
	}
}
