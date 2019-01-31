package custom.gui.networkgui;

import java.util.ArrayList;
import java.util.List;

public class NetWorkGuiManager {

    static int id = 0;
    public static List<String> imageUrls = new ArrayList<>();
    public static List<String> gifUrls = new ArrayList<>();

    public static int distributeID() {
        return id++;
    }

    public static void addImageURL(String url) {
        imageUrls.add(url);
    }

    public static void addGifURL(String url) {
        gifUrls.add(url);
    }
}
