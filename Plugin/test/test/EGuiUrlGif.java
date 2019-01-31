package test;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class EGuiUrlGif {

    String path;
    int x, y, textureX, textureY, width, height, id, speed, frameNum = 0, counter = 0, length;
    boolean[] isuploadTextureImage;
    int[] textureIDs;
    BufferedImage[] imgs;

    public EGuiUrlGif(String path, int id, int speed, int x, int y, int textureX, int textureY, int width, int height) throws MalformedURLException, IOException {
        this.path = path;
        this.x = x;
        this.y = y;
        this.textureX = textureX;
        this.textureY = textureY;
        this.width = width;
        this.height = height;
        this.id = id;
        this.speed = speed;
        GifDecoder gd = new GifDecoder();
        gd.read(new BufferedInputStream(new URL(path).openStream()));
        length = gd.getFrameCount();
        System.out.print(length);
        isuploadTextureImage = new boolean[length];
        textureIDs = new int[length];
        imgs = new BufferedImage[length];
        for (int i = 0; i < length; i++) {
            imgs[i] = gd.getFrame(i);
        }
    }

    public void draw() {
        counter++;
        if (counter == speed) {
            frameNum++;
            counter = 0;
        }
        if (frameNum == length) {
            frameNum = 0;
        }
        //if (!isuploadTextureImage[frameNum]) {
            
        //}
    }

    public void init() throws MalformedURLException, IOException {
    }

}
