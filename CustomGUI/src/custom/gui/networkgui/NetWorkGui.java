package custom.gui.networkgui;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/**
 * GUI对象
 */
public class NetWorkGui {

    public List<NetWorkGuiObject> objList = new ArrayList<>();
    public int guiID;
    public boolean useDefaultBackground = true;

    public NetWorkGui(int guiID) {
        this.guiID = guiID;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(objList);
    }

    /**
     * 设置是否使用mc自带的透光黑色背景
     *
     * @param flag
     */
    public void setUseDefaultBackground(boolean flag) {
        useDefaultBackground = flag;
    }

}
