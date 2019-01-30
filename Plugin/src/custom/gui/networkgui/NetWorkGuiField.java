package custom.gui.networkgui;

public class NetWorkGuiField extends NetWorkGuiObject {

    public int x, y, width, height, id, maxStringLength;

    public NetWorkGuiField(int id, int x, int y, int width, int height, int maxStringLength) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.maxStringLength = maxStringLength;
        type = "GuiField";
    }
}
