package com.dd.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CaptureScreenshot {
    private JFrame frame;
    private JButton captureButton;

    public CaptureScreenshot() {
        frame = new JFrame("Screen Capture Tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create button
        captureButton = new JButton("Capture Screen");
        captureButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                captureScreen();
            }
        });
        
        // Add button to frame
        frame.getContentPane().add(captureButton, BorderLayout.CENTER);

        // Set frame properties
        frame.pack();
        frame.setVisible(true);
    }

    private void captureScreen() {
        try {
            // Capture screen
            Robot robot = new Robot();
            String format = "jpg";
            String fileName = "Screenshot." + format;
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);

            // Save captured image to file
            File file = new File(fileName);
            ImageIO.write(screenFullImage, format, file);

            JOptionPane.showMessageDialog(frame, "Screenshot saved to: " + file.getAbsolutePath());

            // Open captured image
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(file);
            } else {
                JOptionPane.showMessageDialog(frame, "Desktop not supported, cannot open the screenshot.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (AWTException | IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error capturing screenshot: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CaptureScreenshot();
            }
        });
    }
}
