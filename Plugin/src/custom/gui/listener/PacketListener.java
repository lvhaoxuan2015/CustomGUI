package custom.gui.listener;

import custom.gui.event.CustomGuiButtonClickEvent;
import custom.gui.event.CustomGuiFieldTextGetEvent;
import custom.gui.event.CustomGuiOpenEvent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.Bukkit;

public class PacketListener implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String string, Player player, byte[] bytes) {
        String str = new String(bytes);
        if (str.contains("OPENCUSTOMGUI:")) {
            Bukkit.getPluginManager().callEvent(new CustomGuiOpenEvent(Integer.parseInt(str.split(":")[1]), player));
        } else if (str.contains("CLICKCUSTOMGUIBUTTON:")) {
            Bukkit.getPluginManager().callEvent(new CustomGuiButtonClickEvent(Integer.parseInt(str.split(":")[1].split(";")[0]), Integer.parseInt(str.split(":")[1].split(";")[1]), player));
        } else if (str.contains("CHECKMOD")) {
            MainListener.map.put(player.getName(), true);
        } else if (str.contains("GETFIELD:")) {
            Bukkit.getPluginManager().callEvent(new CustomGuiFieldTextGetEvent(str.split(":")[1].split(";")[0], Integer.parseInt(str.split(":")[1].split(";")[1]), player));
        }
    }

}
