/*
 * Copyright (c) 2023 Marko Salig.
 *
 * Licensed under the MIT license: https://opensource.org/licenses/MIT
 * Permission is granted to use, copy, modify, and redistribute the work.
 * Full license information available in the project LICENSE file.
 */

package net.salig.lagerspiel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public final class Utils {

    public final static int IMAGE_SIZE = 190;

    private static final ResourceBundle stringResources = ResourceBundle.getBundle("values.strings", Locale.getDefault());

    /**
     * Returns a ImageIcon for the given path from the resource folder.
     *
     * @param path the path for the desired image.
     * @return the ImageIcon for the given path
     * @throws NullPointerException     if {@code path} is {@code null}
     * @throws MissingResourceException if no resource for the given path can be found
     */
    public static ImageIcon createImageIcon(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        URL imgUrl = Utils.class.getClassLoader().getResource(path);
        if (imgUrl != null) {
            return new ImageIcon(imgUrl);
        } else {
            throw new MissingResourceException("Can't find resource for "
                    + "javax.swing.ImageIcon"
                    + ", path " + path,
                    "javax.swing.ImageIcon",
                    path);
        }
    }

    /**
     * Returns a scaled ImageIcon for the given path from the resource folder.
     * Also take a look here
     * <blockquote>
     * <code>(ImageIcon) {@link #createImageIcon(String) (String) createImageIcon}(path)</code>.
     * </blockquote>
     *
     * @param path   the path for the desired image.
     * @param width  the desired width to which to scale the image.
     * @param height the desired height to which to scale the image.
     * @return the ImageIcon for the given path
     * @throws NullPointerException     if {@code path} is {@code null}
     * @throws MissingResourceException if no image for the given path can be found
     */
    public static ImageIcon createImageIcon(String path, int width, int height) {
        return new ImageIcon(createImageIcon(path).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }

    /**
     * Gets a string for the given key from this resource bundle.
     * Look also at
     * <blockquote>
     * <code>(String) {@link ResourceBundle#getString(String) getString}(key)</code>.
     * </blockquote>
     *
     * @param key the key for the desired string
     * @return the string for the given key
     * @throws NullPointerException     if {@code key} is {@code null}
     * @throws MissingResourceException if no object for the given key can be found
     */
    public static String getString(String key) {
        return stringResources.getString(key);
    }
}
