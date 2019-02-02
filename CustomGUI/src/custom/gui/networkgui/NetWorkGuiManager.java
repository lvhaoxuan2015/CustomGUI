package custom.gui.networkgui;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理器
 */
public class NetWorkGuiManager {

    static int id = 0;
    public static List<String> imageUrls = new ArrayList<>();
    public static List<String> gifUrls = new ArrayList<>();

    /**
     * 构造一个自定义输入框
     *
     * @return 分配一个不重复的ID
     */
    public static int distributeID() {
        return id++;
    }

    /**
     * 添加一个url到玩家进服时自动获取的图片地址缓存列表中，有效减少图片加载时间
     *
     * @param url 图片地址
     */
    public static void addImageURL(String url) {
        imageUrls.add(url);
    }

    /**
     * 添加一个url到玩家进服时自动获取的GIF图片地址缓存列表中，有效减少GIF图片加载时间
     *
     * @param url 图片地址
     */
    public static void addGifURL(String url) {
        gifUrls.add(url);
    }
}
