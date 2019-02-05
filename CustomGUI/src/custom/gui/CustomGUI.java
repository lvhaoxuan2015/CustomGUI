package custom.gui;

import custom.gui.listener.PacketListener;
import custom.gui.listener.MainListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.*;
import org.bukkit.*;
import custom.gui.networkgui.*;
import custom.gui.api.*;
import custom.gui.api.API.GuiType;
import org.bukkit.entity.*;

public class CustomGUI extends JavaPlugin {

    public static PacketListener pl;

    @Override
    public void onEnable() {
        pl = new PacketListener();
        getServer().getMessenger().registerIncomingPluginChannel(this, "customgui", pl);
        getServer().getMessenger().registerOutgoingPluginChannel(this, "customgui");
        getServer().getPluginManager().registerEvents(new MainListener(), this);
        NetWorkGuiManager.addImageURL("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3871474308,1566042740&fm=26&gp=0.jpg");
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp() && args.length == 1) {
            Player p = Bukkit.getPlayer(args[0]);
            if (p != null) {
                NetWorkGui nwg = new NetWorkGui(NetWorkGuiManager.distributeID());
                nwg.setUseDefaultBackground(true);
                nwg.objList.add(new NetWorkGuiCustomField("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3871474308,1566042740&fm=26&gp=0.jpg",
                        NetWorkGuiManager.distributeID(),
                        0,
                        20,
                        200,
                        20,
                        0,
                        0,
                        200,
                        20,
                        128));
                API.implantationGUI(p, nwg, GuiType.GuiInventory);
            }
        }
        return false;
    }
}
