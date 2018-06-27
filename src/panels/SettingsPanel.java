package panels;

import classes.MemePanel;
import classes.R;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends MemePanel {

    public SettingsPanel(){
        setupUI();
    }

    @Override
    public void setupUI() {
        setBackground(R.color_blue_light);
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.gridy = 1;
        c.weighty = 0.1;
        c.anchor = GridBagConstraints.PAGE_END;
        JButton saveButton = new JButton(R.string_button_save);
        add(saveButton, c);
    }
}
