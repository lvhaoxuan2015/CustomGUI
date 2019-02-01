package custom.gui.networkgui;

/**
 * 文本对象
 */
public class NetWorkGuiString extends NetWorkGuiObject {

    public String str;
    public int x, y, color, id;

    /**
     * 构造一个文本
     *
     * @param str 文本
     * @param id 按钮ID
     * @param x 按钮横坐标
     * @param y 按钮纵坐标
     * @param color 颜色，0x颜色
     */
    public NetWorkGuiString(String str, int id, int x, int y, int color) {
        this.id = id;
        this.str = str;
        this.x = x;
        this.y = y;
        this.color = color;
        type = "GuiString";
    }

}
