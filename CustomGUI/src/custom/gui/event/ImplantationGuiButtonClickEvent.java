package custom.gui.event;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Event;
import org.bukkit.entity.Player;

/**
 * 当玩家按下注入的按钮时触发这个事件
 */
public class ImplantationGuiButtonClickEvent extends Event {

    private static final HandlerList hl = new HandlerList();
    private String guiType;
    private int buttonID;
    private Player p;

    public ImplantationGuiButtonClickEvent(int buttonID, String guiType, Player p) {
        this.buttonID = buttonID;
        this.guiType = guiType;
        this.p = p;
    }

    public int getCustomGuiButtonID() {
        return this.buttonID;
    }

    public String getGuiType() {
        return guiType;
    }

    public Player getPlayer() {
        return this.p;
    }

    public static HandlerList getHandlerList() {
        return hl;
    }

    public HandlerList getHandlers() {
        return hl;
    }

}
