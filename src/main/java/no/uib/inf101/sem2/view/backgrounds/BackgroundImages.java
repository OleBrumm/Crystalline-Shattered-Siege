package no.uib.inf101.sem2.view.backgrounds;

import java.awt.*;

import static no.uib.inf101.sem2.view.Inf101Graphics.loadImageFromResources;

public class BackgroundImages {
    public final Image mainMenuBgImage;
    public final Image pausedBgImage;
    public final Image gameplayBgWithPathImage;
    public final Image gameStatusBarBgImage;

    /**
     * Loads all background images.
     */
    public BackgroundImages() {
        mainMenuBgImage = loadImageFromResources("backgrounds/main-menu-bg.png");
        pausedBgImage = loadImageFromResources("backgrounds/paused-bg.png");
        gameplayBgWithPathImage = loadImageFromResources("backgrounds/gameplay-bg-withpath.png");
        gameStatusBarBgImage = loadImageFromResources("backgrounds/tilable-bg.png");
    }
}

