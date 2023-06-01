package net.salig.lagerspiel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Objects;

public final class Utils {

    public final static int IMAGE_SIZE = 150;

    public static ImageIcon createImageIcon(String path) {
        URL imgUrl = Utils.class.getClassLoader().getResource(path);
        return new ImageIcon(Objects.requireNonNull(imgUrl));
    }

    public static ImageIcon createImageIcon(String path, int width, int height) {
        URL imgUrl = Utils.class.getClassLoader().getResource(path);
        return new ImageIcon(new ImageIcon(Objects.requireNonNull(imgUrl)).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }
}
