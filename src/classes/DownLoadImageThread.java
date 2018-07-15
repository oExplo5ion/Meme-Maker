package classes;

import com.sun.istack.internal.NotNull;
import views.MemeImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

public class DownLoadImageThread extends Thread {

    private URL mImageUrl;
    private DonwloadImageListener mListener;

    public DownLoadImageThread(@NotNull URL imageUrl) {
        mImageUrl = imageUrl;
        setName(R.string_thread_download_image + mImageUrl);
    }

    public void setDownloadImageListener(DonwloadImageListener listener) {
        mListener = listener;
    }

    @Override
    public void run() {
        super.run();

        try {
            // download image
            System.setProperty("http.agent", "Chrome");

            InputStream inputStream = new BufferedInputStream(mImageUrl.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1!=(n=inputStream.read(buf)))
            {
                out.write(buf, 0, n);
            }
            out.close();
            inputStream.close();
            byte[] response = out.toByteArray();

            // convert to buffered image
            InputStream in = new ByteArrayInputStream(response);
            BufferedImage bufferedImage = ImageIO.read(in);
            Image revisedImage = MemeImage.resizeImage(bufferedImage, 300, 300);
            if (mListener != null) {
                mListener.imageDownloaded((BufferedImage) revisedImage);
            }

        } catch (IOException e) {
            if (mListener != null) {
                mListener.error(e);
            }
        }
    }
}
































