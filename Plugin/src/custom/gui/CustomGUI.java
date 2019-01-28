package custom.gui;

import custom.gui.api.API;
import custom.gui.networkgui.NetWorkGui;
import custom.gui.networkgui.NetWorkGuiButton;
import custom.gui.networkgui.NetWorkGuiField;
import custom.gui.networkgui.NetWorkGuiImage;
import custom.gui.networkgui.NetWorkGuiString;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CustomGUI extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getMessenger().registerIncomingPluginChannel(this, "customgui", new PacketListener());
        getServer().getMessenger().registerOutgoingPluginChannel(this, "customgui");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            Player p = (Player) Bukkit.getPlayer(args[0]);
            NetWorkGui nwg = new NetWorkGui(0);
            nwg.objList.add(new NetWorkGuiImage("images/image.png", 0, 0, 0, 0, 720, 540));
            nwg.objList.add(new NetWorkGuiButton("Test", 0, 0, 0, 40, 20));
            nwg.objList.add(new NetWorkGuiString("Test", 0, 20, 0xFF3030));
            nwg.objList.add(new NetWorkGuiField(0, 0, 40, 200, 20));
            API.openGui(p, nwg);
        }
        return false;
    }

}
