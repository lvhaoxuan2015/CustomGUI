package custom.gui.networkgui;

public class NetWorkGuiCustomButton extends NetWorkGuiObject {

    String str, firstUrl, lastUrl;
    int x, y, width, height, id;

    public NetWorkGuiCustomButton(String str, String firstUrl, String lastUrl, int id, int x, int y, int width, int height) {
        this.id = id;
        this.str = str;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.firstUrl = firstUrl;
        this.lastUrl = lastUrl;
        type = "GuiCustomButton";
    }
}
