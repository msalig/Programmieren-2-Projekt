package net.salig.lagerspiel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public final class Utils {

    public final static int IMAGE_SIZE = 190;

    private static final ResourceBundle stringResources = ResourceBundle.getBundle("strings", Locale.getDefault());

    public static ImageIcon createImageIcon(String path) {
        URL imgUrl = Utils.class.getClassLoader().getResource(path);
        return new ImageIcon(Objects.requireNonNull(imgUrl));
    }

    public static ImageIcon createImageIcon(String path, int width, int height) {
        URL imgUrl = Utils.class.getClassLoader().getResource(path);
        return new ImageIcon(new ImageIcon(Objects.requireNonNull(imgUrl)).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }

    public static ResourceBundle getStringResources() {
        return stringResources;
    }
}
