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

    private void saveImage() {
        if (mListener != null) {
            mListener.saveImage();
        }
    }

    private void resetText() {
        if (mListener != null) {
            mListener.resetText();
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
        JPanel buttonsContainer = new JPanel();
        buttonsContainer.setLayout(new BoxLayout(buttonsContainer, BoxLayout.Y_AXIS));
        buttonsContainer.setBackground(R.color_clear);
        add(buttonsContainer, c);

        JButton resetButton = new JButton(R.string_reset_text);
        resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        resetButton.addActionListener(e -> { resetText(); });
        buttonsContainer.add(resetButton);

        JButton saveButton = new JButton(R.string_button_save);
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.addActionListener(e -> { saveImage(); });
        buttonsContainer.add(saveButton);
    }
}























