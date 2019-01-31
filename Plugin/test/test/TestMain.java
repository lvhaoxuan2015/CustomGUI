package test;

import custom.gui.networkgui.NetWorkGuiManager;
import java.io.IOException;

public class TestMain {

    public static void main(String[] args) throws IOException {
        EGuiUrlGif gif = new EGuiUrlGif("https://n.sinaimg.cn/tech/transform/523/w299h224/20190130/IMDE-hshmsti2178231.gif", NetWorkGuiManager.distributeID(), 10, 240, 300, 0, 0, 236, 233);
        gif.init();
        while (true) {
            gif.draw();
        }
    }

}
