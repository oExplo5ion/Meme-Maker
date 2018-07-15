package classes;


import java.awt.*;

/**
 * Resource class
 */
public class R {
    private R(){}

    // strings
    public static String string_window_title = "Meme Maker";
    public static String string_text_place_holder = "TEXT";
    public static String string_choose_image = "Choose image";
    public static String string_choose_destination_folder = "Choose destination folder";
    public static String string_image_destination_folder = "Image destination folder";
    public static String string_button_save = "Save image";
    public static String string_image_url = "Image url";
    public static String string_get_image = "Get image";
    public static String string_thread_download_image = "download_image";

    // ints
    public static int int_window_width = 800;
    public static int int_window_height = 600;

    // colors
    public static Color color_clear = new Color(0f,0f,0f,0);
    public static Color color_white = Color.decode("#ffffff");
    public static Color color_white_dark = Color.decode("#DCDCDC");
    public static Color color_blue_dark = Color.decode("#113F53");
    public static Color color_blue_light = Color.decode("#27566D");
}
