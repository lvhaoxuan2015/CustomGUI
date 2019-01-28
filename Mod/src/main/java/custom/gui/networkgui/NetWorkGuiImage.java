package custom.gui.networkgui;

public class NetWorkGuiImage extends NetWorkGuiObject {

	public String path;
	public int x, y, textureX, textureY, width, height;

	public NetWorkGuiImage(String path, int x, int y, int textureX, int textureY, int width, int height) {
		this.path = path;
		this.x = x;
		this.y = y;
		this.textureX = textureX;
		this.textureY = textureY;
		this.width = width;
		this.height = height;
		type = "GuiImage";
	}

}
