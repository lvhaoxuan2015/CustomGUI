package custom.gui.debug;

import custom.gui.CustomGUI;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Debuger {

    public static boolean isDebug = false;
    public static File logFile;

    public static void init() {
        logFile = new File(CustomGUI.getPlugin(CustomGUI.class).getDataFolder(), "log_" + ((int) Math.random() * 1000000) + ".log");
        try {
            logFile.createNewFile();
        } catch (IOException ex) {
        }
    }

    public static void log(String str) throws FileNotFoundException {
        if (isDebug) {
            System.out.println(str);
            FileOutputStream fos = new FileOutputStream(logFile);
            PrintStream ps = new PrintStream(fos);
            ps.println(str);
        }
    }
}
