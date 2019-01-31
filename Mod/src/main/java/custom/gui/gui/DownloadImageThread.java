package custom.gui.gui;

import custom.gui.gui.object.TextureManager;

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
