package no.uib.inf101.sem2.entity.tower;

public interface ITower {

        int getRange();

        int getDamage();

        int getCost();

        String getSprite();

        int getLevel();

        int getUpgradeCost();

        void upgrade();

        void setLevel(int level);

        void setDamage(int damage);

        void setRange(int range);

        void setCost(int cost);

        void setUpgradeCost(int upgradeCost);
}
