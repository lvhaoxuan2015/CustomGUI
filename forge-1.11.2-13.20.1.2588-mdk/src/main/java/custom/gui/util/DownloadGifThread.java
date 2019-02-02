package custom.gui.util;

import custom.gui.object.TextureManager;

public class DownloadGifThread extends Thread {

    String url;

    public DownloadGifThread(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        TextureManager.downloadGif(url);
    }

}
