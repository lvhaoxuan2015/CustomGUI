package custom.gui.networkgui;

/**
 * 自定义按钮对象
 */
public class NetWorkGuiCustomButton extends NetWorkGuiObject {

    String str, firstUrl, lastUrl;
    int x, y, width, height, id, firstColor, lastColor;

    /**
     * 构造一个自定义按钮
     *
     * @param str 按钮文本
     * @param firstUrl 鼠标未悬停时的图片地址
     * @param lastUrl 鼠标悬停时的图片地址
     * @param id 按钮ID
     * @param x 按钮横坐标
     * @param y 按钮纵坐标
     * @param width 按钮宽度
     * @param height 按钮高度
     * @param firstColor 鼠标未悬停时的文本颜色
     * @param lastColor 鼠标悬停时的文本颜色
     */
    public NetWorkGuiCustomButton(String str, String firstUrl, String lastUrl, int id, int x, int y, int width, int height, int firstColor, int lastColor) {
        this.id = id;
        this.str = str;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.firstUrl = firstUrl;
        this.lastUrl = lastUrl;
        this.firstColor = firstColor;
        this.lastColor = lastColor;
        type = "GuiCustomButton";
    }
}
