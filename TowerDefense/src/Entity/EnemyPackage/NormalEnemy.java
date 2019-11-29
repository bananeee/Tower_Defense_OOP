package Entity.EnemyPackage;

import Database.Config;
import Database.Data;
import javafx.scene.image.Image;

public class NormalEnemy extends Enemy {
    public NormalEnemy(int posX, int posY, int id) {
        super(posX, posY, id);
        range = Config.NORMAL_ENEMY_RANGE;
        health = Config.NORMAL_ENEMY_HEALTH * Data.level;
        armor = Config.NORMAL_ENEMY_ARMOR * Data.level;
        damage = Config.NORMAL_ENEMY_DAMAGE * Data.level;
        velocity = Config.NORMAL_ENEMY_VELOCITY;
        reward = Config.NORMAL_ENEMY_REWARD;
        score = Config.NORMAL_ENEMY_SCORE;

        imgSizeX = range * 1.5;
        imgSizeY = range * 1.5 / 2.5;

        Image[] imageList = new Image[12];
        for (int i = 1; i < 13; i++)
            imageList[i - 1] = new Image("NormalEnemy/giphy" + i + ".png");
        image = imageList;
        setup();
    }

    public NormalEnemy(int posX, int posY, int id, int health) {
        this(posX, posY, id);
        this.health = health;
    }
}
