package no.uib.inf101.sem2.view.buttons;

import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.TowerDefenseModel;
import no.uib.inf101.sem2.view.ImageButton;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ButtonManager extends JPanel {
    public final ImageButton startButton;
    public final ImageButton restartButton;
    public final ImageButton pauseButton;
    public final ImageButton resumeButton;
    public final ImageButton mainMenuButton;
    public final ImageButton exitButton;

    /**
     * The button manager is responsible for creating and managing all the buttons in the game.
     *
     * @param model       The game model
     * @param buttonImage The image to use for the buttons
     *
     * @throws IOException If the image file cannot be found
     * @throws FontFormatException If the font file cannot be found
     */
    public ButtonManager(TowerDefenseModel model, Image buttonImage) throws IOException, FontFormatException {
        startButton = new ImageButton(buttonImage, "Start", e -> {
            model.startGame();
            updateButtonVisibility(model.getGameState());
        });
        restartButton = new ImageButton(buttonImage, "Restart", e -> {
            model.restartGame();
            updateButtonVisibility(model.getGameState());
        });
        pauseButton = new ImageButton(buttonImage, "Pause", e -> {
            model.pauseGame();
            updateButtonVisibility(model.getGameState());
        });
        resumeButton = new ImageButton(buttonImage, "Resume", e -> {
            model.resumeGame();
            updateButtonVisibility(model.getGameState());
        });
        mainMenuButton = new ImageButton(buttonImage, "Main Menu", e -> {
            model.mainMenu();
            updateButtonVisibility(model.getGameState());
        });
        exitButton = new ImageButton(buttonImage, "Exit", e -> {
            model.exitGame();
            updateButtonVisibility(model.getGameState());
        });

        this.setLayout(null); // Set layout to null so that you can position the buttons using setBounds
        this.setOpaque(false); // Set this to false so that the ButtonManager background is transparent

        this.add(startButton);
        this.add(restartButton);
        this.add(pauseButton);
        this.add(resumeButton);
        this.add(mainMenuButton);
        this.add(exitButton);
    }

    /**
     * Update the visibility of the buttons based on the current game state.
     * @param gameState The current game state
     */
    public void updateButtonVisibility(GameState gameState) {
        startButton.setVisible(false);
        restartButton.setVisible(false);
        pauseButton.setVisible(false);
        resumeButton.setVisible(false);
        mainMenuButton.setVisible(false);
        exitButton.setVisible(false);

        switch (gameState) {
            case MAIN_MENU -> {
                startButton.setVisible(true);
                exitButton.setVisible(true);
            }
            case IN_GAME -> pauseButton.setVisible(true);
            case PAUSE_SCREEN -> {
                resumeButton.setVisible(true);
                restartButton.setVisible(true);
                mainMenuButton.setVisible(true);
            }
            case GAME_OVER_SCREEN, VICTORY_SCREEN -> {
                restartButton.setVisible(true);
                mainMenuButton.setVisible(true);
            }
        }

        positionButtons();
    }

    /**
     * Position the buttons based on the current size of the ButtonManager.
     */
    public void positionButtons() {
        double titleHeight = getHeight() / 4.0;

        positionButton(startButton, 0, 2, titleHeight);
        positionButton(restartButton, 1, 3, titleHeight);
        positionButton(pauseButton, 0, 0, titleHeight); // Update this line
        positionButton(resumeButton, 0, 3, titleHeight);
        positionButton(mainMenuButton, 2, 3, titleHeight);
        positionButton(exitButton, 1, 2, titleHeight);
    }

    /**
     * Position a button based on the current size of the ButtonManager.
     * @param button The button to position
     * @param index The index of the button
     * @param totalButtons The total number of buttons
     * @param titleHeight The height of the title
     */
    private void positionButton(ImageButton button, int index, int totalButtons, double titleHeight) {
        double screenWidth = getWidth();
        double screenHeight = getHeight();
        double spacing = (screenHeight - titleHeight) / (totalButtons + 1);
        int buttonWidth = (int) (screenWidth / 4);
        int buttonHeight = (int) (screenHeight / 6);
        int buttonX;
        int buttonY;

        if (button == pauseButton) {
            int gameRectangleHeight = 8 * getHeight() / 10;
            int remainingHeight = getHeight() - gameRectangleHeight;
            buttonX = 0;
            buttonY = gameRectangleHeight + (int) (remainingHeight / 2.0) - (buttonHeight / 2);
        } else {
            buttonX = (int) ((screenWidth - buttonWidth) / 2);
            buttonY = (int) (titleHeight + (index + 1) * (spacing));
        }

        button.setBounds(buttonX, buttonY, buttonWidth, buttonHeight); // Set the bounds for the button
    }


}

