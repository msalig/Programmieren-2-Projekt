/*
 * Copyright (c) 2023 Marko Salig.
 *
 * Licensed under the MIT license: https://opensource.org/licenses/MIT
 * Permission is granted to use, copy, modify, and redistribute the work.
 * Full license information available in the project LICENSE file.
 */
package net.salig.lagerspiel.view.info;

import net.salig.lagerspiel.Utils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TutorialWindow extends JFrame {

    private static final int WIDTH = 965;
    private static final int HEIGHT = 700;

    private final CardLayout cardLayout;
    private final JPanel slidePanel;
    private JPanel navPanel;

    private int currentSlide = 0;
    int previousSlide = 0;
    private final int totalSlides = 7;

    public TutorialWindow() {
        super(Utils.getString("info.window.title"));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        slidePanel = new JPanel();
        cardLayout = new CardLayout();
        slidePanel.setLayout(cardLayout);

        // Add individual slide panels to the slidePanel using cardLayout
        for (int slideIndex = 0; slideIndex < totalSlides; slideIndex++) {
            JPanel slide = createSlide(
                    slideIndex,
                    Utils.getString("info.window.slide" + slideIndex + ".heading"),
                    Utils.getString("info.window.slide" + slideIndex + ".message"));
            slidePanel.add(slide, "slide" + slideIndex);
        }

        mainPanel.add(createHeadingPanel(), BorderLayout.NORTH);
        mainPanel.add(slidePanel, BorderLayout.CENTER);
        mainPanel.add(createNavPanel(), BorderLayout.SOUTH);

        updateNavPanel();

        add(mainPanel);
    }

    private JPanel createHeadingPanel() {
        JPanel headingPanel = new JPanel();
        headingPanel.setSize(new Dimension(getWidth(), 200));
        headingPanel.setBackground(new Color(0, 0, 0, 200));

        JLabel heading = new JLabel(Utils.getString("info.window.title"), SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 50));
        heading.setForeground(Color.WHITE);

        headingPanel.add(heading);
        return headingPanel;
    }

    private JPanel createNavPanel() {
        navPanel = new JPanel();
        navPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        navPanel.setBackground(new Color(0, 0, 0, 200));

        JLabel previous = new JLabel(Utils.createImageIcon("assets/previous.png"));
        previous.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                showPreviousSlide();
            }
        });
        navPanel.add(previous);

        // Add dots as separate JLabels to the navPanel
        for (int i = 0; i < totalSlides; i++) {
            JLabel dotLabel = new JLabel("•");
            dotLabel.setFont(new Font("Arial", Font.BOLD, 30));
            dotLabel.setForeground(i == currentSlide ? Color.BLACK : Color.GRAY);
            navPanel.add(dotLabel);
        }

        JLabel next = new JLabel(Utils.createImageIcon("assets/next.png"));
        next.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (currentSlide == totalSlides - 1) {
                    dispose();
                } else {
                    showNextSlide();
                }
            }
        });
        navPanel.add(next);

        return navPanel;
    }

    private JPanel createSlide(int slideIndex, String heading, String message) {
        JPanel slide = new Slide(slideIndex);
        slide.setLayout(null);

        JLabel slideHeading = new JLabel(heading, SwingConstants.CENTER);
        slideHeading.setFont(new Font("Arial", Font.BOLD, 30));
        slideHeading.setForeground(Color.WHITE);

        JLabel slideMessage = new JLabel(message, SwingConstants.CENTER);
        slideMessage.setFont(new Font("Arial", Font.PLAIN, 18));
        slideMessage.setForeground(Color.WHITE);
        slideMessage.setVerticalAlignment(SwingConstants.TOP);

        //Set positions depending on slideIndex
        Insets insets = slide.getInsets();
        Dimension size = slideHeading.getPreferredSize();
        switch (slideIndex) {
            case 2, 3 -> {
                slideHeading.setBounds(10 + insets.left, 5 + insets.top, size.width, size.height);
                slideMessage.setBounds(10 + insets.left, 75 + insets.top, size.width, HEIGHT);
            }
            case 5 -> {
                slideHeading.setBounds(5 + insets.left, 5 + insets.top, size.width, size.height);
                slideMessage.setBounds(5 + insets.left, 100 + insets.top, 130, HEIGHT);
            }
            default -> {
                slideHeading.setBounds(WIDTH / 2 - size.width / 2 + insets.left, 200 + insets.top, size.width, size.height);
                slideMessage.setBounds(WIDTH / 2 - 200 + insets.left, 300 + insets.top, 400, HEIGHT);
            }
        }

        slide.add(slideHeading);
        slide.add(slideMessage);
        slide.setBorder(new LineBorder(Color.YELLOW, 3));
        return slide;
    }

    private static class Slide extends JPanel {

        private final ImageIcon background;

        public Slide(int slide) {
            background = Utils.createImageIcon("assets/slide" + slide + ".png");
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (background != null) {
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    private void showNextSlide() {
        if (currentSlide < totalSlides - 1) {
            currentSlide++;
            cardLayout.next(slidePanel);
            updateNavPanel();
        }
    }

    private void showPreviousSlide() {
        if (currentSlide > 0) {
            currentSlide--;
            cardLayout.previous(slidePanel);
            updateNavPanel();
        }
    }

    private void updateNavPanel() {
        Component[] navComps = navPanel.getComponents();

        //Repaint dots
        for (int i = 1; i < navComps.length - 1; i++) {
            navComps[i].setForeground((i - 1 == currentSlide) ? Color.BLACK : Color.GRAY);
            repaint();
        }

        //Change next-arrow to end-tutorial-text
        JLabel nextSlideLabel = (JLabel) navComps[navComps.length - 1];
        if (currentSlide == totalSlides - 1) {
            nextSlideLabel.setText(Utils.getString("info.window.finish"));
            nextSlideLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            nextSlideLabel.setForeground(Color.WHITE);
            nextSlideLabel.setIcon(null);
        } else if (previousSlide == totalSlides - 1) {
            nextSlideLabel.setIcon(Utils.createImageIcon("assets/next.png"));
            nextSlideLabel.setText(null);
        }

        previousSlide = currentSlide;
    }
}