package custom.gui.networkgui;

/**
 * GIF图片对象
 */
public class NetWorkGuiUrlGif extends NetWorkGuiObject {

    public String path;
    public int x, y, textureX, textureY, width, height, id, speed;
    float textureWidth, textureHeight;

    /**
     * 构造一个GIF图片
     *
     * @param path 地址
     * @param id ID
     * @param x 横坐标
     * @param speed 间隔，越小速度越快
     * @param y 纵坐标
     * @param textureX 图片选取起点横坐标
     * @param textureY 图片选取起点纵坐标
     * @param width 图片截取宽度
     * @param height 图片截取高度
     * @param textureWidth 图片显示宽度
     * @param textureHeight 图片显示高度
     */
    public NetWorkGuiUrlGif(String path, int id, int speed, int x, int y, int textureX, int textureY, int width, int height, float textureWidth, float textureHeight) {
        this.path = path;
        this.x = x;
        this.y = y;
        this.textureX = textureX;
        this.textureY = textureY;
        this.width = width;
        this.height = height;
        this.id = id;
        this.speed = speed;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        type = "GuiUrlGif";
    }

}
