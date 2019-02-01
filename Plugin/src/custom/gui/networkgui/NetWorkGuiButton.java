package custom.gui.networkgui;

/**
 * mc自带按钮对象
 */
public class NetWorkGuiButton extends NetWorkGuiObject {

    public String str;
    public int x, y, width, height, id;

    /**
     * 构造一个mc自带的按钮
     *
     * @param str 按钮文本
     * @param id 按钮ID
     * @param x 按钮横坐标
     * @param y 按钮纵坐标
     * @param width 按钮宽度
     * @param height 按钮高度
     */
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
