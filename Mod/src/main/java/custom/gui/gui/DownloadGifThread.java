package custom.gui.gui;

import custom.gui.gui.object.TextureManager;

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
