package custom.gui.listener;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import custom.gui.PacketPlayOutCustomPayload;
import custom.gui.networkgui.NetWorkGuiManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class MainListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerJoinEvent(PlayerJoinEvent e) {
        Gson gson = new Gson();
        Player p = e.getPlayer();
        JsonObject jo = new JsonObject();
        jo.addProperty("Method", "DOWNLOADTEXTURE");
        jo.addProperty("ImageUrls", gson.toJson(NetWorkGuiManager.imageUrls));
        jo.addProperty("GifUrls", gson.toJson(NetWorkGuiManager.gifUrls));
        new PacketPlayOutCustomPayload(p, jo.toString()).sendTo();
    }
}
