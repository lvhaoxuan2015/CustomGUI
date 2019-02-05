package custom.gui.listener;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import custom.gui.api.API;
import custom.gui.event.CustomGuiButtonClickEvent;
import custom.gui.event.CustomGuiCloseEvent;
import custom.gui.event.CustomGuiOpenEvent;
import custom.gui.event.ImplantationGuiButtonClickEvent;
import java.util.HashMap;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.Bukkit;

public class PacketListener implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String string, Player player, byte[] bytes) {
        String str = new String(bytes);
        Gson gson = new Gson();
        JsonObject obj = gson.fromJson(str, JsonObject.class);
        String method = obj.get("Method").getAsString();
        if (method.equalsIgnoreCase("OPENCUSTOMGUI")) {
            Bukkit.getPluginManager().callEvent(new CustomGuiOpenEvent(obj.get("GuiID").getAsInt(), player));
        } else if (method.equalsIgnoreCase("CLICKCUSTOMGUIBUTTON")) {
            Bukkit.getPluginManager().callEvent(new CustomGuiButtonClickEvent(obj.get("ButtonID").getAsInt(), obj.get("GuiID").getAsInt(), player));
        } else if (method.equalsIgnoreCase("CHANGEVALUE")) {
            if (API.variablesMap.get(player.getName()) == null) {
                API.variablesMap.put(player.getName(), new HashMap<>());
            }
            API.variablesMap.get(player.getName()).put(obj.get("ID").getAsString(), obj.get("Value").getAsString());
        } else if (method.equalsIgnoreCase("CLOSEGUI")) {
            Bukkit.getPluginManager().callEvent(new CustomGuiCloseEvent(obj.get("GuiID").getAsInt(), player));
        } else if (method.equalsIgnoreCase("CLICKIMPLANTATIONGUIBUTTON")) {
            Bukkit.getPluginManager().callEvent(new ImplantationGuiButtonClickEvent(obj.get("ButtonID").getAsInt(), obj.get("GuiType").getAsString(), player));
        }
    }

}
