package custom.gui.implantation;

import java.lang.reflect.Field;
import net.minecraft.client.gui.GuiScreen;

public class InvokeUtil {

    public static void setValue(Object instance, String fieldName, Object value) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        getField(instance.getClass(), fieldName).set(instance, value);
    }

    public static Field getField(Class<?> clazz, String fieldName) throws NoSuchFieldException, SecurityException {
        Field field = clazz.getField(fieldName);
        field.setAccessible(true);
        return field;
    }

    public static void implantationGuiObjectValue(GuiScreen gui, String objName, String objFieldName, Object value) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Object obj = InvokeUtil.getField(gui.getClass(), objName).get(gui);
        InvokeUtil.getField(obj.getClass(), objFieldName).set(obj, value);
    }

    public static void implantationGuiValue(GuiScreen gui, String fieldName, Object value) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        InvokeUtil.getField(gui.getClass(), fieldName).set(gui, value);
    }
}
