package Entity.EnemyPackage;

import Database.Config;
import Database.Data;
import javafx.scene.image.Image;

public class SmallerEnemy extends Enemy {
    public SmallerEnemy(int posX, int posY, int id) {
        super(posX, posY, id);
        range = Config.SMALLER_ENEMY_RANGE;
        health = Config.SMALLER_ENEMY_HEALTH * Data.level;
        armor = Config.SMALLER_ENEMY_ARMOR * Data.level;
        damage = Config.SMALLER_ENEMY_DAMAGE * Data.level;
        velocity = Config.SMALLER_ENEMY_VELOCITY;
        reward = Config.SMALLER_ENEMY_REWARD;
        score = Config.SMALLER_ENEMY_SCORE;
        imgSizeX = range * 1;
        imgSizeY = range * 1;

        Image[] imageList = new Image[7];
        for (int i = 1; i < 8; i++)
            imageList[i - 1] = new Image("SmallerEnemy/Smaller_" + i + ".png");
        image = imageList;
        setup();
    }

    public SmallerEnemy(int posX, int posY, int id, int health) {
        this(posX,posY,id);
        this.health = health;

    }

}
