package custom.gui;

import custom.gui.event.CustomGuiButtonClickEvent;
import custom.gui.event.CustomGuiOpenEvent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.Bukkit;

public class PacketListener implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String string, Player player, byte[] bytes) {
        if (new String(bytes).contains("OPENCUSTOMGUI:")) {
            Bukkit.getPluginManager().callEvent(new CustomGuiOpenEvent(Integer.parseInt(new String(bytes).split(":")[1]), player));
        }
        if (new String(bytes).contains("CLICKCUSTOMGUIBUTTON:")) {
            Bukkit.getPluginManager().callEvent(new CustomGuiButtonClickEvent(Integer.parseInt(new String(bytes).split(":")[1].split(";")[0]), Integer.parseInt(new String(bytes).split(":")[1].split(";")[1]), player));
        }
    }

}
