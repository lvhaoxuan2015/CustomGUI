package custom.gui.networkgui;

public class NetWorkGuiButton extends NetWorkGuiObject {

    public String str;
    public int buttonid;
    public int x, y, width, height;

    public NetWorkGuiButton(String str, int buttonid, int x, int y, int width, int height) {
        this.str = str;
        this.buttonid = buttonid;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        type = "GuiButton";
    }

}
