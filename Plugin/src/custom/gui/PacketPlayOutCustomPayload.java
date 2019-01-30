package custom.gui;

import io.netty.buffer.Unpooled;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PacketPlayOutCustomPayload {

    private static Constructor<?> packetConstructor;
    private static Method getHandle;
    private static Field playerConnection;
    private static Method sendPacket;
    private Object packet;
    private String msg;
    private Player p;

    public PacketPlayOutCustomPayload(Player p, String msg) {
        this.p = p;
        this.msg = msg;
        try {
            Class<?> packetClass = PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutCustomPayload");
            packetConstructor = getPacketPlayOutCustomPayloadConstructor(packetClass);
            getHandle = getMethod(PackageType.CRAFTBUKKIT_ENTITY.getClass("CraftPlayer"), "getHandle");
            playerConnection = getField(PackageType.MINECRAFT_SERVER.getClass("EntityPlayer"), false, "playerConnection");
            sendPacket = getMethod(playerConnection.getType(), "sendPacket");
        } catch (ClassNotFoundException | NoSuchMethodException | NoSuchFieldException | SecurityException ex) {
            Logger.getLogger(PacketPlayOutCustomPayload.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initializePacket(String str, Object pds) {
        if (packet != null) {
            return;
        }
        try {
            packet = packetConstructor.newInstance();
            setValue(packet, packet.getClass(), true, "a", str);
            setValue(packet, packet.getClass(), true, "b", pds);
        } catch (NullPointerException | IllegalAccessException | IllegalArgumentException | InstantiationException | SecurityException | InvocationTargetException | NoSuchFieldException e) {
            Logger.getLogger(PacketPlayOutCustomPayload.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void setValue(Object instance, Class<?> clazz, boolean declared, String fieldName, Object value) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        getField(clazz, declared, fieldName).set(instance, value);
    }

    public void sendTo() {
        Class<?> pds;
        try {
            pds = PackageType.MINECRAFT_SERVER.getClass("PacketDataSerializer");
            Object obj = getPacketDataSerializerConstructor(pds).newInstance(Unpooled.wrappedBuffer(msg.getBytes()));
            initializePacket("customgui", obj);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(PacketPlayOutCustomPayload.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            sendPacket.invoke(playerConnection.get(getHandle.invoke(p)), packet);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(PacketPlayOutCustomPayload.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Constructor<?> getPacketPlayOutCustomPayloadConstructor(Class<?> clazz, Class<?>... parameterTypes) throws NoSuchMethodException {
        for (Constructor<?> constructor : clazz.getConstructors()) {
            if (constructor.getParameters().length == 0) {
                return constructor;
            }
        }
        return null;
    }

    public static Constructor<?> getPacketDataSerializerConstructor(Class<?> clazz, Class<?>... parameterTypes) throws NoSuchMethodException {
        for (Constructor<?> constructor : clazz.getConstructors()) {
            if (constructor.getParameters().length == 1) {
                return constructor;
            }
        }
        return null;
    }

    public static Method getMethod(Class<?> clazz, String methodName) throws NoSuchMethodException {
        for (Method method : clazz.getMethods()) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        return null;
    }

    public static Field getField(Class<?> clazz, boolean declared, String fieldName) throws NoSuchFieldException, SecurityException {
        Field field = declared ? clazz.getDeclaredField(fieldName) : clazz.getField(fieldName);
        field.setAccessible(true);
        return field;
    }

    public enum PackageType {
        MINECRAFT_SERVER("net.minecraft.server." + getServerVersion()),
        CRAFTBUKKIT("org.bukkit.craftbukkit." + getServerVersion()),
        CRAFTBUKKIT_ENTITY(CRAFTBUKKIT, "entity"),;

        private final String path;

        private PackageType(String path) {
            this.path = path;
        }

        private PackageType(PackageType parent, String path) {
            this(parent + "." + path);
        }

        public String getPath() {
            return path;
        }

        public Class<?> getClass(String className) throws ClassNotFoundException {
            return Class.forName(this + "." + className);
        }

        @Override
        public String toString() {
            return path;
        }

        public static String getServerVersion() {
            return Bukkit.getServer().getClass().getPackage().getName().substring(23);
        }
    }
}
