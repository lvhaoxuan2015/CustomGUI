package custom.gui.object;

import custom.gui.util.GifDecoder;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class TextureManager {

    static HashMap<String, BufferedImage> imageUrls = new HashMap<>();
    static HashMap<String, BufferedImage[]> gifUrls = new HashMap<>();

    public static BufferedImage getBufferedImage(String urlStr) {
        if (imageUrls.get(urlStr) == null) {
            try {
                URL url = new URL(urlStr);
                InputStream is = url.openStream();
                BufferedImage img = ImageIO.read(is);
                imageUrls.put(urlStr, img);
                return img;
            } catch (MalformedURLException e) {
            } catch (IOException e) {
            }
            return null;
        } else {
            return imageUrls.get(urlStr);
        }
    }

    public static void downloadImage(String urlStr) {
        if (imageUrls.get(urlStr) == null) {
            try {
                URL url = new URL(urlStr);
                InputStream is = url.openStream();
                BufferedImage img = ImageIO.read(is);
                imageUrls.put(urlStr, img);
            } catch (MalformedURLException e) {
            } catch (IOException e) {
            }
        }
    }

    public static void downloadGif(String urlStr) {
        if (gifUrls.get(urlStr) == null) {
            try {
                GifDecoder gd = new GifDecoder();
                gd.read(new BufferedInputStream(new URL(urlStr).openStream()));
                int length = gd.getFrameCount();
                BufferedImage[] imgs = new BufferedImage[length];
                for (int i = 0; i < length; i++) {
                    imgs[i] = gd.getFrame(i);
                }
                gifUrls.put(urlStr, imgs);
            } catch (MalformedURLException ex) {
            } catch (IOException ex) {
            }
        }
    }
}
