package Entity.EnemyPackage;

import Database.Config;
import Database.Data;
import javafx.scene.image.Image;

public class TankerEnemy extends Enemy {
    public TankerEnemy(int posX, int posY, int id) {
        super(posX, posY, id);
        range = Config.TANKER_ENEMY_RANGE;
        health = Config.TANKER_ENEMY_HEALTH * Data.level;
        armor = Config.TANKER_ENEMY_ARMOR * Data.level;
        damage = Config.TANKER_ENEMY_DAMAGE * Data.level;
        velocity = Config.TANKER_ENEMY_VELOCITY;
        reward = Config.TANKER_ENEMY_REWARD;
        score = Config.TANKER_ENEMY_SCORE;

        imgSizeX = range * 1.5;
        imgSizeY = range * 1.5;

        Image[] imageList = new Image[11];
        for (int i = 1; i < 12; i++)
            imageList[i - 1] = new Image("TankerEnemy/Tank_" + i + ".png");
        image = imageList;
        setup();
    }
    public TankerEnemy(int posX, int posY, int id, int health) {
        this(posX,posY,id);
        this.health = health;

    }
}
