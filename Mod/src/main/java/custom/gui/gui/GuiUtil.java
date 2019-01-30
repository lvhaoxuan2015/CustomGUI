package custom.gui.gui;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;

import custom.gui.gui.object.EGuiButton;
import custom.gui.gui.object.EGuiField;
import custom.gui.gui.object.EGuiImage;
import custom.gui.gui.object.EGuiObject;
import custom.gui.gui.object.EGuiString;

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
		}
		if (jsonObj.get("type").getAsString().equalsIgnoreCase("GuiImage")) {
			return new EGuiImage(jsonObj);
		}
		if (jsonObj.get("type").getAsString().equalsIgnoreCase("GuiString")) {
			return new EGuiString(jsonObj);
		}
		if (jsonObj.get("type").getAsString().equalsIgnoreCase("GuiField")) {
			return new EGuiField(jsonObj);
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
