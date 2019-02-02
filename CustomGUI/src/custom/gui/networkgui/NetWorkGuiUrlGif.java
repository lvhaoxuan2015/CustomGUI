package custom.gui.networkgui;

/**
 * GIF图片对象
 */
public class NetWorkGuiUrlGif extends NetWorkGuiObject {

    public String path;
    public int x, y, textureX, textureY, width, height, id, speed;

    /**
     * 构造一个GIF图片
     *
     * @param path 地址
     * @param id 按钮ID
     * @param x 按钮横坐标
     * @param speed 间隔，越小速度越快
     * @param y 按钮纵坐标
     * @param width 按钮宽度
     * @param height 按钮高度
     * @param textureX 图片选取起点横坐标
     * @param textureY 图片选取起点纵坐标
     */
    public NetWorkGuiUrlGif(String path, int id, int speed, int x, int y, int textureX, int textureY, int width, int height) {
        this.path = path;
        this.x = x;
        this.y = y;
        this.textureX = textureX;
        this.textureY = textureY;
        this.width = width;
        this.height = height;
        this.id = id;
        this.speed = speed;
        type = "GuiUrlGif";
    }

}
