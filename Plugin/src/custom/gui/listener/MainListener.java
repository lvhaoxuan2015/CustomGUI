package custom.gui.listener;

import custom.gui.PacketPlayOutCustomPayload;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.ReentrantLock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class MainListener implements Listener {

    public static HashMap<String, Boolean> map = new HashMap<>();
    public static HashMap<String, Thread> threadMap = new HashMap<>();
    public static ReentrantLock lock = new ReentrantLock();
    Thread t;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerJoinEvent(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        new PacketPlayOutCustomPayload(p, "CHECKMOD").sendTo();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (map.get(p.getName()) != null) {
                    if (!map.get(p.getName())) {
                        p.kickPlayer("未正确安装CustomGUI Mod");
                    }
                } else {
                    p.kickPlayer("未正确安装CustomGUI Mod");
                }
            }

        }, 6000);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerQuitEvent(PlayerQuitEvent e) {
        map.put(e.getPlayer().getName(), false);
    }
}
