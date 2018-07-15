package panels;

import views.MemeImage;
import classes.MemePanel;
import classes.R;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

/**
 *  Controls meme logic such as: meme view, image path,
 *  set image, save image...
 */
public class ImagePanel extends MemePanel {

    private final MemeImage imageView = new MemeImage();
    private final JLabel dePaLabel = new JLabel();

    public ImagePanel(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException |
                UnsupportedLookAndFeelException ignored) { }
        setupUI();
    }

    public void saveImage() {
        // prepare image
        Icon icon = imageView.getIcon();
        BufferedImage imgBuff = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        imageView.paint(imgBuff.createGraphics());

        // generate name
        Random r = new Random();
        int min = 0;
        int max = 9999;
        float random = min + r.nextFloat() * (max - min);
        String name = "MEME_IMAGE_" + String.valueOf(random);
        File path = new File(dePaLabel.getText() + "/" + name + ".jpg");

        // write image
        MemeImage.writeImage(imgBuff, path);
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

    private void chooseDestinationFolder(){
        // show file dialog
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        chooser.setDialogTitle(R.string_choose_destination_folder);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            dePaLabel.setText(chooser.getSelectedFile().getAbsolutePath());
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

        // setup image url field
        ImageUrlRow row = new ImageUrlRow();
        row.setImageUrlRowListener(image -> imageView.setIcon(new ImageIcon(image)));
        row.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(row);

        container.add(Box.createRigidArea(new Dimension(0, 20)));

        // setup choose image button
        JButton chooseImageBtn = new JButton();
        chooseImageBtn.setText(R.string_choose_image);
        chooseImageBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        chooseImageBtn.addActionListener(e -> chooseImage());
        container.add(chooseImageBtn);

        // setup image path
        container.add(Box.createRigidArea(new Dimension(0, 20)));

        // destination icon
        InputStream stream = getClass().getResourceAsStream("/res/ic_next.png");
        ImageIcon icon = null;
        try {
            icon = new ImageIcon(ImageIO.read(stream));
            icon = new ImageIcon(MemeImage.resizeImage(icon.getImage(), 15, 15));
        } catch (IOException ignored) { }

        JLabel nextIcon = new JLabel();
        if (icon != null) {
            nextIcon.setIcon(icon);
        }
        nextIcon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                chooseDestinationFolder();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        // destination label
        JLabel deLabel = new JLabel(R.string_image_destination_folder + "     ");
        deLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        deLabel.setLayout(new BorderLayout());
        deLabel.setForeground(R.color_white_dark);
        deLabel.add(nextIcon, BorderLayout.LINE_END);
        container.add(deLabel);

        // destination path label
        dePaLabel.setText(System.getProperty("user.home"));
        dePaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dePaLabel.setForeground(R.color_white_dark);
        dePaLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                chooseDestinationFolder();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        container.add(dePaLabel);

        // add container
        add(Box.createHorizontalGlue());
        add(container);
        add(Box.createHorizontalGlue());
    }
}






















