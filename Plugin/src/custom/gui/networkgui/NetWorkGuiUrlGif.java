package custom.gui.networkgui;

public class NetWorkGuiUrlGif extends NetWorkGuiObject {

    public String path;
    public int x, y, textureX, textureY, width, height, id, speed;

    public NetWorkGuiUrlGif(String path, int id, int speed, int x, int y, int textureX, int textureY, int width, int height) {
        this.path = path;
        this.x = x;
        this.y = y;
        this.textureX = textureX;
        this.textureY = textureY;
        this.width = width;
        this.height = height;
        this.id = id;
        this.speed = speed;
        type = "GuiUrlGif";
    }

}
