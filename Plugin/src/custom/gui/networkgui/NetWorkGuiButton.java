package custom.gui.networkgui;

public class NetWorkGuiButton extends NetWorkGuiObject {

    public String str;
    public int x, y, width, height, id;

    public NetWorkGuiButton(String str, int id, int x, int y, int width, int height) {
        this.id = id;
        this.str = str;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        type = "GuiButton";
    }

}
