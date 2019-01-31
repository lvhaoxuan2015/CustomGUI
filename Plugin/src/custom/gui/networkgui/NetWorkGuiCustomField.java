package custom.gui.networkgui;

public class NetWorkGuiCustomField extends NetWorkGuiObject {

    int x, y, width, height, id, maxStringLength, textureX, textureY;
    String url;

    public NetWorkGuiCustomField(String url, int id, int x, int y, int width, int height, int textureX, int textureY, int maxStringLength) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.maxStringLength = maxStringLength;
        this.url = url;
        this.textureX = textureX;
        this.textureY = textureY;
        type = "GuiCustomField";
    }
}
