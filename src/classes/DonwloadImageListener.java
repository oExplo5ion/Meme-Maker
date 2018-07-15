package classes;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface DonwloadImageListener {
    void imageDownloaded(BufferedImage image);
    void error(IOException e);
}
