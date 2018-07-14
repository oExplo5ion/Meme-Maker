package panels;

import classes.MemePanel;
import classes.R;
import com.sun.istack.internal.NotNull;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends MemePanel {

    private SettingsListerner mListener;

    public SettingsPanel(){
        setupUI();
    }

    private void saveImage(){
        if (mListener != null) {
            mListener.saveImage();
        }
    }

    public void setSettingsListener(@NotNull SettingsListerner settingsListener) {
        mListener = settingsListener;
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
        saveButton.addActionListener(e -> { saveImage(); });
        add(saveButton, c);
    }
}
