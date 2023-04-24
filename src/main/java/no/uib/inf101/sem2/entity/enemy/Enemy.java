package no.uib.inf101.sem2.entity.enemy;

import no.uib.inf101.sem2.screen.ScreenPosition;

import java.util.List;

public class Enemy implements IEnemy {

    public double x, y;
    public double speed;
    public int health;
    public int reward;
    public int damage;
    public double size;
    public int currentWaypointIndex;
    public List<ScreenPosition> waypoints;
    private final String type;
    private String effect;
    private boolean isSlowed;

    public Enemy(double x, double y, double speed, int health, int reward, int damage, double size, List<ScreenPosition> waypoints, String type, int currentWaypointIndex) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.health = health;
        this.reward = reward;
        this.damage = damage;
        this.size = size;
        this.currentWaypointIndex = currentWaypointIndex;
        this.waypoints = waypoints;
        this.type = type;
        this.effect = "NONE";
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getReward() {
        return reward;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public double getSize() {
        return size;
    }

    @Override
    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getCurrentWaypointIndex() {
        return currentWaypointIndex;
    }

    @Override
    public String getEffect() {
        return effect;
    }

    @Override
    public void setEffect(String effect) {
        this.effect = effect;
        applyEffect(effect);
    }

    private void applyEffect(String effect) {
        if (effect.equals("SLOW")) {
            if (isSlowed()){
                return;
            }
            isSlowed = true;
            setSpeed(speed / 3);
        }
    }

    private boolean isSlowed() {
        return isSlowed;
    }

    @Override
    public ScreenPosition getPosition() {
        return new ScreenPosition(x, y);
    }

    private void updatePosition() {
        if (currentWaypointIndex < waypoints.size()) {
            ScreenPosition currentWaypoint = waypoints.get(currentWaypointIndex);

            double dx = currentWaypoint.x() - x;
            double dy = currentWaypoint.y() - y;

            if (Math.abs(dx) < speed) {
                // The enemy is close enough horizontally to the waypoint, so update x-coordinate
                x = currentWaypoint.x();
            } else {
                // Move the enemy towards the current waypoint horizontally
                x += Math.signum(dx) * speed;
            }

            if (Math.abs(dy) < speed) {
                // The enemy is close enough vertically to the waypoint, so update y-coordinate
                y = currentWaypoint.y();
            } else {
                // Move the enemy towards the current waypoint vertically
                y += Math.signum(dy) * speed;
            }

            // Check if the enemy has reached the current waypoint
            if (x == currentWaypoint.x() && y == currentWaypoint.y()) {
                currentWaypointIndex++;
            }
        }
    }

    /**
     * Checks if the enemy has reached the end of the path.
     *
     * @return true if the enemy has reached the end of the path, false otherwise
     */
    public boolean hasReachedEnd() {
        return currentWaypointIndex >= waypoints.size();
    }

    @Override
    public void move() {
        updatePosition();
    }

    public boolean isDead() {
        return health <= 0;
    }

    public double getDistance(double x, double y) {
        return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }
}
