package panels;

import classes.DonwloadImageListener;
import classes.DownLoadImageThread;
import classes.R;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

interface ImageUrlRowListener {
    void imageDownloaded(BufferedImage image);
}

public class ImageUrlRow extends JLabel {

    private  JTextPane mUrlPane;
    private DownLoadImageThread mThread;
    private ImageUrlRowListener mListener;

    public ImageUrlRow() {
        setupUI();
    }

    public void setImageUrlRowListener(ImageUrlRowListener listener) {
        mListener = listener;
    }

    private void downloadImage() {

        if (mThread != null) {
            mThread.interrupt();
        }

        URL url = null;
        try {
            url = new URL(mUrlPane.getText());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        mThread = new DownLoadImageThread(url);
        mThread.setDownloadImageListener(new DonwloadImageListener() {
            @Override
            public void imageDownloaded(BufferedImage image) {
                if (mListener != null) {
                    mListener.imageDownloaded(image);
                }
            }

            @Override
            public void error(IOException e) {

            }
        });
        mThread.run();
    }

    public void setupUI() {
        setLayout(new GridBagLayout());
        setText("                                                                           ");

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;

        mUrlPane = new JTextPane();
        mUrlPane.setText(R.string_image_url);
        c.gridx = 0;
        c.gridy = 0;
        add(mUrlPane, c);

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.0;
        add(Box.createRigidArea(new Dimension(5, 0)), c);

        JButton getImage = new JButton();
        getImage.setText(R.string_get_image);
        getImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                downloadImage();
            }
        });
        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 0.009;
        add(getImage, c);
    }

}






























