package custom.gui.networkgui;

/**
 * 自定义按钮对象
 */
public class NetWorkGuiCustomButton extends NetWorkGuiObject {

    String str, firstUrl, lastUrl;
    int x, y, width, height, id, firstColor, lastColor;
    float textureWidth, textureHeight;

    /**
     * 构造一个自定义按钮
     *
     * @param str 文本
     * @param firstUrl 鼠标未悬停时的图片地址
     * @param lastUrl 鼠标悬停时的图片地址
     * @param id ID
     * @param x 横坐标
     * @param y 纵坐标
     * @param width 图片截取宽度
     * @param height 图片截取高度
     * @param textureWidth 图片显示宽度
     * @param textureHeight 图片显示高度
     * @param firstColor 鼠标未悬停时的文本颜色
     * @param lastColor 鼠标悬停时的文本颜色
     */
    public NetWorkGuiCustomButton(String str, String firstUrl, String lastUrl, int id, int x, int y, int width, int height, float textureWidth, float textureHeight, int firstColor, int lastColor) {
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
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.type = "GuiCustomButton";
    }
}
