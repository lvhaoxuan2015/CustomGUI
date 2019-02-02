package custom.gui.api;

import com.google.gson.JsonObject;
import custom.gui.PacketPlayOutCustomPayload;
import custom.gui.listener.MainListener;
import custom.gui.networkgui.NetWorkGui;
import java.util.HashMap;
import java.util.List;
import org.bukkit.entity.Player;

/**
 * 供调用的API类
 */
public class API {

    /**
     * 储存客户端上传变量的HashMap
     */
    public static HashMap<String, HashMap<String, String>> variablesMap = new HashMap<>();

    /**
     * 为玩家p打开nwg的GUI
     *
     * @param p 玩家
     * @param nwg GUI对象
     */
    public static void openGui(Player p, NetWorkGui nwg) {
        JsonObject jo = new JsonObject();
        jo.addProperty("Gui", nwg.toJson());
        jo.addProperty("UseDefaultBackground", nwg.useDefaultBackground);
        jo.addProperty("GuiID", nwg.guiID);
        jo.addProperty("Method", "OPENGUI");
        new PacketPlayOutCustomPayload(p, jo.toString()).sendTo();
    }

    /**
     * 为玩家p类型为guiType的GUI植入nwg的GUI中的组件
     *
     * @param p 玩家
     * @param nwg GUI对象
     * @param guiType GUI类型
     */
    public static void implantationGUI(Player p, NetWorkGui nwg, GuiType guiType) {
        JsonObject jo = new JsonObject();
        jo.addProperty("Gui", nwg.toJson());
        jo.addProperty("GuiType", guiType.name());
        jo.addProperty("Method", "IMPLANTATIONGUI");
        new PacketPlayOutCustomPayload(p, jo.toString()).sendTo();
    }

    public enum GuiType {
        GuiIngameMenu,
        GuiChat,
        GuiInventory,
        GuiContainerCreative,
        GuiChest,
        GuiCrafting,
        GuiFurnace,
        GuiEnchantment,
        GuiDispenser,
        GuiHopper,
        GuiDownloadTerrain,
        InGame
    }

    /**
     * 为玩家p关闭现在的GUI
     *
     * @param p 玩家
     */
    public static void closeNowGui(Player p) {
        JsonObject jo = new JsonObject();
        jo.addProperty("Method", "CLOSENOWGUI");
        new PacketPlayOutCustomPayload(p, jo.toString()).sendTo();
    }

    /**
     * 获取玩家p的ID为id的输入框中的文本
     *
     * @param p 玩家
     * @param id 输入框ID
     * @return 返回输入框中的文本
     */
    public static String getField(Player p, int id) {
        return variablesMap.get(p.getName()).get(String.valueOf(id));
    }

    /**
     * 获取玩家p的ID为id的GUI的宽度
     *
     * @param p 玩家
     * @param id GUI的ID
     * @return 返回GUI的宽度
     */
    public static int getWidth(Player p, int id) {
        return Integer.valueOf(variablesMap.get(p.getName()).get(id + ":width"));
    }

    /**
     * 获取玩家p的ID为id的GUI的高度
     *
     * @param p 玩家
     * @param id GUI的ID
     * @return 返回GUI的高度
     */
    public static int getHeight(Player p, int id) {
        return Integer.valueOf(variablesMap.get(p.getName()).get(id + ":height"));
    }

}
