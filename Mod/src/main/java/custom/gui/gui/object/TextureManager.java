package custom.gui.gui.object;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;

public class TextureManager {

	public static BufferedImage getBufferedImage(String urlStr) {
		try {
			URL url = new URL(urlStr);
			InputStream is = url.openStream();
			BufferedImage img = ImageIO.read(is);
			return img;
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
		return null;
	}
}
