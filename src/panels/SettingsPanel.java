package panels;

import classes.MemePanel;
import classes.R;

import javax.swing.*;

public class SettingsPanel extends MemePanel {

    public SettingsPanel(){
        setupUI();
    }

    @Override
    public void setupUI() {
        setBackground(R.color_blue_light);
    }
}
