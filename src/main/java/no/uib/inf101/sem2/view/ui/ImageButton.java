package no.uib.inf101.sem2.view.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

/**
 * This class extends JButton to create a custom button with an image background
 * and some special visual effects when the user interacts with it.
 */
public class ImageButton extends JButton {

    private final Image buttonImage;
    private boolean isMouseOver = false;
    private boolean isMousePressed = false;

    /**
     * Creates a new ImageButton with the given image, text, and ActionListener.
     *
     * @param buttonImage the image to use as the background of the button
     * @param text        the text to display on the button
     * @throws IOException         if there is an error reading the font file
     * @throws FontFormatException if there is an error creating the font
     */
    public ImageButton(Image buttonImage, String text) throws IOException, FontFormatException {
        this.buttonImage = buttonImage;
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setForeground(Color.WHITE);
        File fontFile = new File("src/main/resources/misc/BebasNeue-Regular.ttf");
        Font buttonFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        setFont(buttonFont.deriveFont(40f));
        setText(text);
        /**addActionListener(actionListener);

        // Add mouse listeners to track when the user interacts with the button
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isMouseOver = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isMouseOver = false;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                isMousePressed = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isMousePressed = false;
                repaint();
            }
        }); */
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(buttonImage, 0, 0, getWidth(), getHeight(), this);

        // If the mouse is over the button, draw a translucent white rectangle over it
        if (isMouseOver) {
            g2d.setColor(new Color(255, 255, 255, 16));
            g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 10, 10));
        }

        // If the mouse is pressed on the button, draw a translucent black rectangle over it
        if (isMousePressed) {
            g2d.setColor(new Color(0, 0, 0, 16));
            g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 10, 10));
        }

        // Call the superclass's paintComponent method to draw the button's text and borders
        super.paintComponent(g);
    }

    protected void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(buttonImage, 0, 0, getWidth(), getHeight(), this);
    }
}
