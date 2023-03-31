package no.uib.inf101.sem2.entity.enemy;

public class Enemy2 implements IEnemy {

        @Override
        public int getSpeed() {
            return 2;
        }

        @Override
        public int getHealth() {
            return 3;
        }

        @Override
        public int getReward() {
            return 10;
        }

        @Override
        public int getLevel() {
            return 2;
        }

        @Override
        public String getSprite() {
            return "Enemies/enemy2.png";
        }
}
