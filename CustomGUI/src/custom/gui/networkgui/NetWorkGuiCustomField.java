package custom.gui.networkgui;

/**
 * 自定义输入框对象
 */
public class NetWorkGuiCustomField extends NetWorkGuiObject {

    int x, y, width, height, id, maxStringLength, textureX, textureY;
    float textureWidth, textureHeight;
    String url;

    /**
     * 构造一个自定义输入框
     *
     * @param url 背景图片地址
     * @param id ID
     * @param x 横坐标
     * @param y 纵坐标
     * @param width 图片截取宽度
     * @param height 图片截取高度
     * @param textureX 图片选取起点横坐标
     * @param maxStringLength 最大文本长度
     * @param textureY 图片选取起点纵坐标
     * @param textureWidth 图片显示宽度
     * @param textureHeight 图片显示高度
     */
    public NetWorkGuiCustomField(String url, int id, int x, int y, int width, int height, int textureX, int textureY, float textureWidth, float textureHeight, int maxStringLength) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.maxStringLength = maxStringLength;
        this.url = url;
        this.textureX = textureX;
        this.textureY = textureY;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        type = "GuiCustomField";
    }
}
