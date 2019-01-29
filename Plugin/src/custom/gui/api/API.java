package custom.gui.api;

import custom.gui.PacketPlayOutCustomPayload;
import custom.gui.networkgui.NetWorkGui;
import org.bukkit.entity.Player;

public class API {

    public static void openGui(Player p, NetWorkGui nwg) {
        new PacketPlayOutCustomPayload(p, "OPENGUI$MIDDLEBRACKETS1$" + nwg.guiID + "$SEPARATOR$" + nwg.objListToString() + "$MIDDLEBRACKETS2$").sendTo();
    }

    public static void getField(Player p, int id) throws InterruptedException {
        new PacketPlayOutCustomPayload(p, "GETFIELD$SEPARATOR$" + id).sendTo();
    }

}
