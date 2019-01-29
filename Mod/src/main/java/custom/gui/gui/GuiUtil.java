package custom.gui.gui;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import custom.gui.gui.object.*;
import custom.gui.networkgui.*;

public class GuiUtil {
	public static List<EGuiObject> backToObject(String str) {
		List<EGuiObject> list = new ArrayList<>();
		String[] strs = str.split("\\$NEXT\\$");
		for (String s : strs) {
			String type = s.split("\\$AND\\$")[0];
			String json = s.split("\\$AND\\$")[1];
			list.add(getEGuiObject(type, json));
		}
		return list;
	}

	public static EGuiObject getEGuiObject(String str, String json) {
		if (str.equalsIgnoreCase("GuiButton")) {
			return new EGuiButton(json);
		}
		if (str.equalsIgnoreCase("GuiImage")) {
			return new EGuiImage(json);
		}
		if (str.equalsIgnoreCase("GuiString")) {
			return new EGuiString(json);
		}
		if (str.equalsIgnoreCase("GuiField")) {
			return new EGuiField(json);
		}
		return null;
	}

	public static void writeInObject(Object object, NetWorkGuiObject obj) {
		for (Field f : obj.getClass().getDeclaredFields()) {
			try {
				Object value = f.get(obj);
				String name = f.getName();
				setValue(object, name, value);
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			} catch (NoSuchFieldException e) {
			} catch (SecurityException e) {
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
