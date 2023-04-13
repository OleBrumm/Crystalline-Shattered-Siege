package no.uib.inf101.sem2.entity.enemy;

import no.uib.inf101.sem2.screen.ScreenPosition;

import java.util.List;

public class Enemy implements IEnemy {

    public double x;
    public double y;
    public double speed;
    public int health;
    public int reward;
    public int damage;
    public double size;
    public int currentWaypointIndex;
    public List<ScreenPosition> waypoints;

    public Enemy(double x, double y, double speed, int health, int reward, int damage, double size, List<ScreenPosition> waypoints) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.health = health;
        this.reward = reward;
        this.damage = damage;
        this.size = size;
        this.currentWaypointIndex = 0;
        this.waypoints = waypoints;
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public int getHealth() {
        return health;
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
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setY(int y) {
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

}
