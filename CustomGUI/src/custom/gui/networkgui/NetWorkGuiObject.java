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
     */
    public void setWheel(boolean flag) {
        wheel = flag;
    }
}
