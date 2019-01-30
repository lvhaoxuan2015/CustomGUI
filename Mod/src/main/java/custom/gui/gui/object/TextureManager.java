package custom.gui.gui.object;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class TextureManager {

	static HashMap<String, BufferedImage> map = new HashMap<>();

	public static BufferedImage getBufferedImage(String urlStr) {
		if (map.get(urlStr) == null) {
			try {
				URL url = new URL(urlStr);
				InputStream is = url.openStream();
				BufferedImage img = ImageIO.read(is);
				map.put(urlStr, img);
				return img;
			} catch (MalformedURLException e) {
			} catch (IOException e) {
			}
			return null;
		} else {
			return map.get(urlStr);
		}
	}
}
