package panels;

import views.MemeImage;
import classes.MemePanel;
import classes.R;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 *  Controls meme logic such as: meme view, image path,
 *  set image, save image...
 */
public class ImagePanel extends MemePanel {

    private final MemeImage imageView = new MemeImage();

    public ImagePanel(){
        setupUI();
    }

    private void chooseImage(){
        // show file dialog
        FileDialog fileDialog = new FileDialog(new Frame(), R.string_choose_image, FileDialog.LOAD);
        fileDialog.setFilenameFilter((dir, name) -> name.matches(".*?\\.(jpg|JPG|jpeg|png)$"));
        fileDialog.setVisible(true);

        // get result
        String filename = fileDialog.getFile();
        if(filename != null){
            // check is file is image
            File image = new File(fileDialog.getDirectory() + fileDialog.getFile());
            BufferedImage imgBuff = MemeImage.resizeImage(image, imageView.getWidth(), imageView.getHeight());
            if (imgBuff != null) {
                imageView.setIcon(new ImageIcon(imgBuff));
            }
        }
    }

    @Override
    public void setupUI() {
        setBackground(R.color_blue_dark);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // setup container
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBackground(R.color_blue_dark);

        // setup image view
        imageView.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(imageView);

        container.add(Box.createRigidArea(new Dimension(0, 20)));

        // setup choose image button
        JButton chooseImageBtn = new JButton();
        chooseImageBtn.setText(R.string_choose_image);
        chooseImageBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        chooseImageBtn.addActionListener(e -> chooseImage());
        container.add(chooseImageBtn);

        // setup image path
        JPanel destinationPanel = new JPanel();

        // add container
        add(Box.createHorizontalGlue());
        add(container);
        add(Box.createHorizontalGlue());
    }
}
