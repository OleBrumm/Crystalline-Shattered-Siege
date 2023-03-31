package no.uib.inf101.sem2.view;

import java.awt.*;
import java.util.List;
import java.awt.geom.Rectangle2D;

public class Menu {
    private final List<ImageButton> buttons;
    private final Rectangle2D bounds;

    public Menu(List<ImageButton> buttons, Rectangle2D bounds) {
        this.buttons = buttons;
        this.bounds = bounds;
        calculateButtonPositions();
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLACK);
        graphics2D.fill(bounds);
        for (ImageButton button : buttons) {
            button.draw(graphics2D);
            System.out.println("Drawing button");
        }
    }

    private void calculateButtonPositions() {
        int numberOfButtons = buttons.size();
        double buttonWidth = bounds.getWidth() / numberOfButtons;
        double buttonHeight = bounds.getHeight();

        for (int i = 0; i < numberOfButtons; i++) {
            ImageButton button = buttons.get(i);
            int buttonX = (int) (bounds.getX() + i * buttonWidth + (buttonWidth - button.getWidth()) / 2);
            int buttonY = (int) (bounds.getY() + (buttonHeight - button.getHeight()) / 2);
            button.setLocation(buttonX, buttonY);
        }
    }
}

