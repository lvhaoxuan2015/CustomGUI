package custom.gui.networkgui;

public class NetWorkGuiUrlsGif extends NetWorkGuiObject {

    public String[] paths;
    public int x, y, textureX, textureY, width, height, id, speed;

    public NetWorkGuiUrlsGif(String[] paths, int id, int speed, int x, int y, int textureX, int textureY, int width, int height) {
        this.paths = paths;
        this.x = x;
        this.y = y;
        this.textureX = textureX;
        this.textureY = textureY;
        this.width = width;
        this.height = height;
        this.id = id;
        this.speed = speed;
        type = "GuiUrlsGif";
    }

}
