package custom.gui.util;

import custom.gui.object.TextureManager;

public class DownloadImageThread extends Thread {

    String url;

    public DownloadImageThread(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        TextureManager.downloadImage(url);
    }

}
