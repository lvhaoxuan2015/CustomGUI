package custom.gui.api;

import com.google.gson.JsonObject;
import custom.gui.PacketPlayOutCustomPayload;
import custom.gui.networkgui.NetWorkGui;
import java.util.HashMap;
import org.bukkit.entity.Player;

public class API {

    public static HashMap<String, HashMap<Integer, String>> variablesMap = new HashMap<>();

    public static void openGui(Player p, NetWorkGui nwg) {
        JsonObject jo = new JsonObject();
        jo.addProperty("Gui", nwg.objListToJson());
        jo.addProperty("GuiID", nwg.guiID);
        jo.addProperty("Method", "OPENGUI");
        new PacketPlayOutCustomPayload(p, jo.toString()).sendTo();
    }

    public static String getField(Player p, int id) {
        return variablesMap.get(p.getName()).get(id);
    }

}
