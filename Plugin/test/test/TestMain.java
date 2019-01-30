package test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import custom.gui.networkgui.NetWorkGui;
import custom.gui.networkgui.NetWorkGuiButton;
import custom.gui.networkgui.NetWorkGuiField;
import custom.gui.networkgui.NetWorkGuiImage;
import custom.gui.networkgui.NetWorkGuiManager;
import custom.gui.networkgui.NetWorkGuiObject;
import custom.gui.networkgui.NetWorkGuiString;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestMain {

    public static void main(String[] args) {
        NetWorkGui nwg = new NetWorkGui(NetWorkGuiManager.distributeID());
        nwg.objList.add(new NetWorkGuiImage("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=468864cdf0039245b5b8e95de6fdcfa7/54fbb2fb43166d22ca0c87944a2309f79052d2b3.jpg", NetWorkGuiManager.distributeID(), 0, 0, 0, 0, 480, 600));
        nwg.objList.add(new NetWorkGuiButton("Test", NetWorkGuiManager.distributeID(), 0, 0, 40, 20));
        nwg.objList.add(new NetWorkGuiString("Test", NetWorkGuiManager.distributeID(), 0, 20, 0xFF3030));
        nwg.objList.add(new NetWorkGuiField(NetWorkGuiManager.distributeID(), 0, 40, 200, 20, 128));
        JsonObject jo = new JsonObject();
        jo.addProperty("Gui", nwg.objListToJson());
        jo.addProperty("GuiID", nwg.guiID);
        jo.addProperty("Method", "OPENGUI");

        String str = jo.toString();
        Gson gson = new Gson();
        JsonObject obj = gson.fromJson(str, JsonObject.class);
        String method = obj.get("Method").getAsString();
        if (method.equalsIgnoreCase("OPENGUI")) {
            List<JsonObject> list = gson.fromJson(obj.get("Gui").getAsString(), new TypeToken<List<JsonObject>>() {
            }.getType());
            for (JsonObject obje : list) {
                Object oe = gson.fromJson(obje, Object.class);
                writeInObject(oe);
            }
        }
    }

    public static void writeInObject(Object source) {
        for (Field f : source.getClass().getDeclaredFields()) {
            try {
                f.setAccessible(true);
                String key = f.getName();
                Object value = f.get(source);
                System.out.println(key + ": " + value);
            } catch (SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(TestMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void setValue(Object instance, String fileName, Object value)
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Field field = instance.getClass().getDeclaredField(fileName);
        field.setAccessible(true);
        field.set(instance, value);
    }

}
