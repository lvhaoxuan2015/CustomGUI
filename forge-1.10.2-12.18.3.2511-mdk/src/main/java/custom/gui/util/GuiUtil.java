package custom.gui.util;

import custom.gui.object.EGuiUrlGif;
import custom.gui.object.EGuiText;
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
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

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
        } else if (jsonObj.get("type").getAsString().equalsIgnoreCase("GuiText")) {
            return new EGuiText(jsonObj);
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

    public static void drawModalRectWithCustomSizedTexture(int x, int y, float u, float v, int width, int height, float textureWidth, float textureHeight) {
        float f = 1.0F / textureWidth;
        float f1 = 1.0F / textureHeight;
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vertexbuffer = tessellator.getBuffer();
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        vertexbuffer.pos((double) x, (double) (y + height), 0.0D).tex((double) (u * f), (double) ((v + (float) height) * f1)).endVertex();
        vertexbuffer.pos((double) (x + width), (double) (y + height), 0.0D).tex((double) ((u + (float) width) * f), (double) ((v + (float) height) * f1)).endVertex();
        vertexbuffer.pos((double) (x + width), (double) y, 0.0D).tex((double) ((u + (float) width) * f), (double) (v * f1)).endVertex();
        vertexbuffer.pos((double) x, (double) y, 0.0D).tex((double) (u * f), (double) (v * f1)).endVertex();
        tessellator.draw();
    }
}
