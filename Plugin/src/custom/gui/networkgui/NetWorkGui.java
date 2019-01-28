package custom.gui.networkgui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;

public class NetWorkGui {

    public List<NetWorkGuiObject> objList = new ArrayList<>();
    public int guiID;

    public NetWorkGui(int guiID) {
        this.guiID = guiID;
    }

    public String objListToString() {
        String ret = "";
        Gson gson = new GsonBuilder().create();
        for (NetWorkGuiObject obj : objList) {
            ret += obj.type + "$AND$" + gson.toJson(obj) + "$NEXT$";
        }
        return ret;
    }

}
