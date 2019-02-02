package custom.gui.networkgui;

/**
 * GIF图片对象
 */
public class NetWorkGuiUrlsGif extends NetWorkGuiObject {

    public String[] paths;
    public int x, y, textureX, textureY, width, height, id, speed;

    /**
     * 构造一个GIF
     *
     * @param paths 地址数组
     * @param id 按钮ID
     * @param speed 间隔，越小速度越快
     * @param x 按钮横坐标
     * @param y 按钮纵坐标
     * @param width 按钮宽度
     * @param height 按钮高度
     * @param textureX 图片选取起点横坐标
     * @param textureY 图片选取起点纵坐标
     */
    public NetWorkGuiUrlsGif(String[] paths, int id, int speed, int x, int y, int textureX, int textureY, int width, int height) {
        this.paths = paths;
        this.x = x;
        this.y = y;
        this.textureX = textureX;
        this.textureY = textureY;
        this.width = width;
        this.height = height;
        this.id = id;
        this.speed = speed;
        type = "GuiUrlsGif";
    }

}
