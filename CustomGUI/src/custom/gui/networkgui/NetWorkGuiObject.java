package custom.gui.networkgui;

/**
 * 对象
 */
public abstract class NetWorkGuiObject {

    public String type = "GuiObject";
    public boolean wheel = false;

    /**
     * 设置是否随鼠标滚轮移动
     *
     * @param flag 是/否
     * @return 自身对象
     */
    public NetWorkGuiObject setWheel(boolean flag) {
        wheel = flag;
        return this;
    }
}
