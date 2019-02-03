package custom.gui;

import custom.gui.listener.PacketListener;
import custom.gui.listener.MainListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.*;
import org.bukkit.*;
import custom.gui.networkgui.*;
import custom.gui.api.*;
import org.bukkit.entity.*;

public class CustomGUI extends JavaPlugin {

    public static PacketListener pl;

    @Override
    public void onEnable() {
        pl = new PacketListener();
        getServer().getMessenger().registerIncomingPluginChannel(this, "customgui", pl);
        getServer().getMessenger().registerOutgoingPluginChannel(this, "customgui");
        getServer().getPluginManager().registerEvents(new MainListener(), this);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp() && args.length == 1) {
            Player p = Bukkit.getPlayer(args[0]);
            if (p != null) {
                NetWorkGui nwg = new NetWorkGui(NetWorkGuiManager.distributeID());
                nwg.setUseDefaultBackground(true);
                nwg.objList.add(new NetWorkGuiUrlGif("https://imgsa.baidu.com/forum/w%3D580/sign=1134406ad4c451daf6f60ce386fc52a5/fbabde529822720e06123ace72cb0a46f31fab87.jpg", NetWorkGuiManager.distributeID(), 4, 240, 0, 0, 0, 352, 240, 352.0f, 240.0f));
                nwg.objList.add(new NetWorkGuiImage("https://gss2.bdstatic.com/9fo3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=80c78b32a4d3fd1f3609a53c08754222/6c224f4a20a446230bbbbd7e9422720e0cf3d7bc.jpg", NetWorkGuiManager.distributeID(), 0, 0, 0, 0, 240, 300, 240.0f, 300.0f).setWheel(true));
                nwg.objList.add(new NetWorkGuiButton("Test", NetWorkGuiManager.distributeID(), 0, 0, 80, 20));
                nwg.objList.add(new NetWorkGuiText("Test", NetWorkGuiManager.distributeID(), 0, 20, 16724016));
                nwg.objList.add(new NetWorkGuiText("¡ìe¡ìlTest22222", NetWorkGuiManager.distributeID(), 0, 300, 16724016).setWheel(true));
                nwg.objList.add(new NetWorkGuiField(NetWorkGuiManager.distributeID(), 0, 60, 200, 20, 128));
                API.openGui(p, nwg);
            }
        }
        return false;
    }
}
