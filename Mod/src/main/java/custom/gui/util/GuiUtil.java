package custom.gui.util;

import custom.gui.object.EGuiUrlGif;
import custom.gui.object.EGuiString;
import custom.gui.object.EGuiButton;
import custom.gui.object.EGuiUrlsGif;
import custom.gui.object.EGuiField;
import custom.gui.object.EGuiCustomField;
import custom.gui.object.EGuiImage;
import custom.gui.object.EGuiCustomButton;
import custom.gui.object.EGuiObject;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;


public class GuiUtil {

    public static List<EGuiObject> backToObject(List<JsonObject> list) {
        List<EGuiObject> retList = new ArrayList<>();
        for (JsonObject obj : list) {
            retList.add(getEGuiObject(obj));
        }
        return retList;
    }

    public static EGuiObject getEGuiObject(JsonObject jsonObj) {
        if (jsonObj.get("type").getAsString().equalsIgnoreCase("GuiButton")) {
            return new EGuiButton(jsonObj);
        } else if (jsonObj.get("type").getAsString().equalsIgnoreCase("GuiImage")) {
            return new EGuiImage(jsonObj);
        } else if (jsonObj.get("type").getAsString().equalsIgnoreCase("GuiString")) {
            return new EGuiString(jsonObj);
        } else if (jsonObj.get("type").getAsString().equalsIgnoreCase("GuiField")) {
            return new EGuiField(jsonObj);
        } else if (jsonObj.get("type").getAsString().equalsIgnoreCase("GuiUrlsGif")) {
            return new EGuiUrlsGif(jsonObj);
        } else if (jsonObj.get("type").getAsString().equalsIgnoreCase("GuiUrlGif")) {
            return new EGuiUrlGif(jsonObj);
        } else if (jsonObj.get("type").getAsString().equalsIgnoreCase("GuiCustomButton")) {
            return new EGuiCustomButton(jsonObj);
        } else if (jsonObj.get("type").getAsString().equalsIgnoreCase("GuiCustomField")) {
            return new EGuiCustomField(jsonObj);
        }
        return null;
    }

    public static void setValue(Object instance, String fileName, Object value)
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Field field = instance.getClass().getDeclaredField(fileName);
        field.setAccessible(true);
        field.set(instance, value);
    }

    public static void writeInObject(Object source, Object target) {
        for (Field f : source.getClass().getDeclaredFields()) {
            try {
                f.setAccessible(true);
                String key = f.getName();
                Object value = f.get(source);
                setValue(target, key, value);
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            }
        }
    }
}
