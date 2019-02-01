package custom.gui.networkgui;

/**
 * 图片对象
 */
public class NetWorkGuiImage extends NetWorkGuiObject {

    public String path;
    public int x, y, textureX, textureY, width, height, id;

    /**
     * 构造一个图片
     *
     * @param path 图片地址
     * @param id 按钮ID
     * @param x 按钮横坐标
     * @param y 按钮纵坐标
     * @param width 按钮宽度
     * @param height 按钮高度
     * @param textureX 图片选取起点横坐标
     * @param textureY 图片选取起点纵坐标
     */
    public NetWorkGuiImage(String path, int id, int x, int y, int textureX, int textureY, int width, int height) {
        this.id = id;
        this.path = path;
        this.x = x;
        this.y = y;
        this.textureX = textureX;
        this.textureY = textureY;
        this.width = width;
        this.height = height;
        type = "GuiImage";
    }

}
