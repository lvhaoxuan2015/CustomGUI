package custom.gui.networkgui;

/**
 * 自定义输入框对象
 */
public class NetWorkGuiCustomField extends NetWorkGuiObject {

    int x, y, width, height, id, maxStringLength, textureX, textureY;
    String url;

    /**
     * 构造一个自定义输入框
     *
     * @param url 背景图片地址
     * @param id 按钮ID
     * @param x 按钮横坐标
     * @param y 按钮纵坐标
     * @param width 按钮宽度
     * @param height 按钮高度
     * @param textureX 图片选取起点横坐标
     * @param maxStringLength 最大文本长度
     * @param textureY 图片选取起点纵坐标
     */
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
