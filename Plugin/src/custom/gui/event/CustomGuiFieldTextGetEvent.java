package custom.gui.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CustomGuiFieldTextGetEvent extends Event {

    HandlerList hl = new HandlerList();
    String str;
    int id;
    Player p;

    public CustomGuiFieldTextGetEvent(String str, int id, Player p) {
        this.str = str;
        this.id = id;
        this.p = p;
    }

    public String getCustomGuiFieldText() {
        return this.str;
    }
    
    public int getCustomGuiFieldID() {
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
