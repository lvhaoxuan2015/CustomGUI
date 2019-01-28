package custom.gui.event;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Event;
import org.bukkit.entity.Player;

public class CustomGuiOpenEvent extends Event {

    HandlerList hl = new HandlerList();
    int id;
    Player p;

    public CustomGuiOpenEvent(int id, Player p) {
        this.id = id;
        this.p = p;
    }

    public int getCustomGuiID() {
        return this.id;
    }

    public Player getPlayer() {
        return this.p;
    }

    public HandlerList getHandlerList() {
        return hl;
    }

    public HandlerList getHandlers() {
        return hl;
    }

}
