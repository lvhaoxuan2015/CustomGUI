package custom.gui;

import custom.gui.listener.PacketListener;
import custom.gui.api.API;
import custom.gui.listener.MainListener;
import custom.gui.networkgui.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CustomGUI extends JavaPlugin {

    public static PacketListener pl;

    @Override
    public void onEnable() {
        pl = new PacketListener();
        getServer().getMessenger().registerIncomingPluginChannel(this, "customgui", pl);
        getServer().getMessenger().registerOutgoingPluginChannel(this, "customgui");
        getServer().getPluginManager().registerEvents(new MainListener(), this);
        NetWorkGuiManager.addGifURL("https://n.sinaimg.cn/tech/transform/523/w299h224/20190130/IMDE-hshmsti2178231.gif");
        NetWorkGuiManager.addImageURL("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=468864cdf0039245b5b8e95de6fdcfa7/54fbb2fb43166d22ca0c87944a2309f79052d2b3.jpg");
        NetWorkGuiManager.addImageURL("https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=2422309363,3456238023&fm=58&bpow=710&bpoh=532");
        NetWorkGuiManager.addImageURL("https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=906132994,4223306353&fm=58&bpow=800&bpoh=600");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            Player p = (Player) Bukkit.getPlayer(args[0]);
            NetWorkGui nwg = new NetWorkGui(NetWorkGuiManager.distributeID());
            nwg.setUseDefaultBackground(true);
            nwg.objList.add(new NetWorkGuiImage("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=468864cdf0039245b5b8e95de6fdcfa7/54fbb2fb43166d22ca0c87944a2309f79052d2b3.jpg", NetWorkGuiManager.distributeID(), 0, 0, 0, 0, 240, 300));
            nwg.objList.add(new NetWorkGuiUrlGif("https://n.sinaimg.cn/tech/transform/523/w299h224/20190130/IMDE-hshmsti2178231.gif", NetWorkGuiManager.distributeID(), 10, 240, 0, 0, 0, 121, 121));
            nwg.objList.add(new NetWorkGuiCustomButton("Test",
                    "https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=2422309363,3456238023&fm=58&bpow=710&bpoh=532",
                    "https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=906132994,4223306353&fm=58&bpow=800&bpoh=600",
                    NetWorkGuiManager.distributeID(), 40, 0, 40, 20, 14737632, 16777120));
            nwg.objList.add(new NetWorkGuiButton("Test", NetWorkGuiManager.distributeID(), 0, 0, 40, 20));
            nwg.objList.add(new NetWorkGuiString("Test", NetWorkGuiManager.distributeID(), 0, 20, 0xFF3030));
            nwg.objList.add(new NetWorkGuiCustomField("https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=2422309363,3456238023&fm=58&bpow=710&bpoh=532", NetWorkGuiManager.distributeID(), 0, 40, 200, 20, 0, 0, 128));
            API.openGui(p, nwg);
        }
        if (args.length == 2) {
            Player p = (Player) Bukkit.getPlayer(args[0]);
            API.closeNowGui(p);
        }
        return false;
    }

}
