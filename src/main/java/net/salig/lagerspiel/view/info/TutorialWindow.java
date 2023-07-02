package net.salig.lagerspiel.view.info;

import net.salig.lagerspiel.Utils;

import javax.swing.*;
import java.awt.*;

public class InfoWindow extends JFrame {

    private final CardLayout cardLayout;
    private final JPanel slidePanel;
    private JPanel navPanel;

    private int currentSlide = 0;
    private final int totalSlides = 7; // Adjust this value based on the total number of slides

    public InfoWindow() {
        super(Utils.getStringResources().getString("info.window.title"));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setBackground(new Color(0, 200, 255));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        slidePanel = new JPanel();
        cardLayout = new CardLayout();
        slidePanel.setLayout(cardLayout);

        // Add your individual slide panels to the slidePanel using cardLayout
        for (int i = 0; i < totalSlides; i++) {
            JPanel slide = createSlidePanel(
                    Utils.getStringResources().getString("info.window.slide" + i + ".heading"),
                    Utils.getStringResources().getString("info.window.slide" + i + ".message"));
            slidePanel.add(slide, "slide" + i);
        }

        mainPanel.add(slidePanel, BorderLayout.CENTER);
        mainPanel.add(createNavPanel(), BorderLayout.SOUTH);

        updateDotColors();

        add(mainPanel);
    }

    private JPanel createNavPanel() {
        navPanel = new JPanel();
        navPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton previousButton = new JButton("Previous");
        previousButton.addActionListener(e -> showPreviousSlide());
        navPanel.add(previousButton);

        // Add dots as separate JLabels to the navPanel
        for (int i = 0; i < totalSlides; i++) {
            JLabel dotLabel = new JLabel("•"); // Use a bullet point or any other dot symbol
            dotLabel.setFont(new Font("Arial", Font.BOLD, 30));
            dotLabel.setForeground(i == currentSlide ? Color.BLACK : Color.GRAY);
            navPanel.add(dotLabel);
        }

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> showNextSlide());
        navPanel.add(nextButton);

        return navPanel;
    }

    private JPanel createSlidePanel(String heading, String message) {
        JPanel slidePanel = new JPanel();
        slidePanel.setLayout(new BorderLayout());
        JLabel slideLabel = new JLabel(heading);
        slideLabel.setFont(new Font("Arial", Font.BOLD, 20));
        slideLabel.setHorizontalAlignment(JLabel.CENTER);
        slidePanel.add(slideLabel, BorderLayout.PAGE_START);
        slidePanel.add(new JLabel(message), BorderLayout.CENTER);
        return slidePanel;
    }

    private void showNextSlide() {
        if (currentSlide < totalSlides - 1) {
            currentSlide++;
            cardLayout.next(slidePanel);
            updateDotColors();
        }
    }

    private void showPreviousSlide() {
        if (currentSlide > 0) {
            currentSlide--;
            cardLayout.previous(slidePanel);
            updateDotColors();
        }
    }

    private void updateDotColors() {
        Component[] dotLabels = navPanel.getComponents();
        for (int i = 0; i < dotLabels.length; i++) {
            if(dotLabels[i] instanceof JLabel) {
                JLabel dotLabel = (JLabel) dotLabels[i];
                dotLabel.setForeground(i - 1 == currentSlide ? Color.BLACK : Color.GRAY);
            }
        }
    }
}

