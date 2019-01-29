package custom.gui.networkgui;

public class NetWorkGuiImage extends NetWorkGuiObject {

    public String path;
    public int x, y, textureX, textureY, width, height, id;

    public NetWorkGuiImage(String path, int id, int x, int y, int textureX, int textureY, int width, int height) {
        this.id = id;
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
