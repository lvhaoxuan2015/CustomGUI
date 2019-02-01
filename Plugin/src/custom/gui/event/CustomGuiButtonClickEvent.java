package custom.gui.event;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Event;
import org.bukkit.entity.Player;

/**
 * 当玩家按下按钮时触发这个事件
 */
public class CustomGuiButtonClickEvent extends Event {

    HandlerList hl = new HandlerList();
    int buttonID, guiID;
    Player p;

    public CustomGuiButtonClickEvent(int buttonID, int guiID, Player p) {
        this.buttonID = buttonID;
        this.guiID = guiID;
        this.p = p;
    }

    public int getCustomGuiButtonID() {
        return this.buttonID;
    }

    public int getCustomGuiID() {
        return this.guiID;
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
