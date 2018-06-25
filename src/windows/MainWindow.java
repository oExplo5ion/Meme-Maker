package windows;

import classes.R;
import panels.ImagePanel;
import panels.SettingsPanel;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(R.int_window_width, R.int_window_height);
        setTitle(R.string_window_title);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setupUI();
    }

    public void showWindow(){
        setVisible(true);
    }

    private void setupUI(){
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;

        ImagePanel imagePanel = new ImagePanel();
        c.gridx = 0;
        c.gridy = 0;
        add(imagePanel, c);

        SettingsPanel settingsPanel = new SettingsPanel();
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.6;
        add(settingsPanel, c);
    }

}
