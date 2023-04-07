package no.uib.inf101.sem2.view.resources;

import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static no.uib.inf101.sem2.view.Inf101Graphics.loadImageFromResources;

/**
 * A class for loading and storing images.
 */
public class ImageResources {
    private final Map<String, Image> images;

    public ImageResources() {
        images = new HashMap<>();
        try {
            loadImages();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading image resources.");
        }
    }

    /**
     * Loads all non-background images from the resources folder.
     *
     * @throws IOException if an image could not be loaded
     */
    private void loadImages() throws IOException {
        images.put("Button", loadImageFromResources("misc/button.png"));
        images.put("EnemyRed", loadImageFromResources("Enemies/EnemyRed.png"));
        images.put("EnemyBlue", loadImageFromResources("Enemies/EnemyBlue.png"));
        images.put("EnemyYellow", loadImageFromResources("Enemies/EnemyYellow.png"));
        images.put("TowerIce", loadImageFromResources("Towers/TowerIce.png"));
        images.put("TowerFire", loadImageFromResources("Towers/TowerFire.png"));
        images.put("TowerTree", loadImageFromResources("Towers/TowerTree.png"));
        images.put("GoldIcon", loadImageFromResources("misc/gold.png"));
        images.put("HeartIcon", loadImageFromResources("misc/heart.png"));
        images.put("WaveIcon", loadImageFromResources("misc/wave.png"));
        images.put("ProjectileIce", loadImageFromResources("Projectiles/ProjectileIce.png"));
        images.put("ProjectileFire", loadImageFromResources("Projectiles/ProjectileFire.png"));
        images.put("ProjectileTree", loadImageFromResources("Projectiles/ProjectileTree.png"));
    }

    /**
     * Returns the image with the given key.
     *
     * @param key the key of the image
     * @return the image with the given key
     */
    public Image getImage(String key) {
        return images.get(key);
    }
}

