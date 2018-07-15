package views;

import classes.R;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 *  Visual presentation of meme.
 *  Holds in: image, top text, bottom text.
 */
public class MemeImage extends JLabel {
    final private JTextPane mTopArea = new JTextPane();
    final private JTextPane mBottomArea = new JTextPane();

    public MemeImage(){
        setupUI();
    }

    public static void writeImage(@NotNull BufferedImage image, @NotNull File path) {
        try {
            ImageIO.write(image, "jpg", path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates resized BufferedImage from file.
     * @param image image file which will be resized.
     * @param width image's new width
     * @param height image's new height
     * @return returns null if couldn't create BufferedImage.
     */
    @Nullable
    public static BufferedImage resizeImage(@NotNull File image, final int width, final int height){
        // check file extension
        String extension;
        int i = image.getAbsolutePath().lastIndexOf('.');
        int p = Math.max(image.getAbsolutePath().lastIndexOf('/'), image.getAbsolutePath().lastIndexOf('\\'));
        if (i > p) {
            extension = image.getAbsolutePath().substring(i+1);
            // check extension
            if (extension.equals("jpg") ||
                    extension.equals("JPG") ||
                    extension.equals("jpeg") ||
                    extension.equals("png")) {
                // file is an image
                // create new image
                BufferedImage scaledBI;
                try {
                    scaledBI = ImageIO.read(image);
                    BufferedImage cropedImg = scaledBI.getSubimage(0,0, width, height);
                    Graphics2D g = cropedImg.createGraphics();
                    g.drawImage(cropedImg, 0, 0, null);
                    g.dispose();
                    return cropedImg;
                } catch (IOException e) {
                    return null;
                }
            }
        }
        return null;
    }

    /**
     * Creates resided Image.
     * @param image image file which will be resized.
     * @param width image's new width
     * @param height image's new height
     */
    @Nullable
    public static Image resizeImage(@NotNull Image image, final int width, final int height){
        BufferedImage imgBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = imgBuff.createGraphics();
        g.drawImage(image, 0, 0, width, height, null);
        g.dispose();
        return imgBuff;
    }

    private void setupUI(){
        setLayout(new BorderLayout());

        InputStream stream = getClass().getResourceAsStream("/res/memeIcon.png");
        try {
            setIcon(new ImageIcon(ImageIO.read(stream)));
            setOpaque(true);
            setPreferredSize(new Dimension(300,300));
            add(Box.createHorizontalGlue());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // setup top pane
        mTopArea.setText(R.string_text_place_holder);
        JScrollPane topPane = new JScrollPane(
                mTopArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        add(topPane, BorderLayout.PAGE_START);

        // setup bottom pane
        mBottomArea.setText(R.string_text_place_holder);
        JScrollPane bottomPane = new JScrollPane(
                mBottomArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        add(bottomPane, BorderLayout.PAGE_END);
    }
}




























