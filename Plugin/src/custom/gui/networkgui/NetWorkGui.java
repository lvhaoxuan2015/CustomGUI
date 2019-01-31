package custom.gui.networkgui;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class NetWorkGui {

    public List<NetWorkGuiObject> objList = new ArrayList<>();
    public int guiID;

    public NetWorkGui(int guiID) {
        this.guiID = guiID;
    }

    public String objListToJson() {
        Gson gson = new Gson();
        return gson.toJson(objList);
    }

}
