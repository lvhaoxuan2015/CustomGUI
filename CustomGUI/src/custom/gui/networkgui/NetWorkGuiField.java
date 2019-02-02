package custom.gui.networkgui;

/**
 * 输入框对象
 */
public class NetWorkGuiField extends NetWorkGuiObject {

    public int x, y, width, height, id, maxStringLength;

    /**
     * 构造一个输入框
     *
     * @param id 按钮ID
     * @param x 按钮横坐标
     * @param y 按钮纵坐标
     * @param width 按钮宽度
     * @param height 按钮高度
     * @param maxStringLength 最大文本长度
     */
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
