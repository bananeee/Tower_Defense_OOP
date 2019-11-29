package Entity.EnemyPackage;

import Database.Config;
import Database.Data;
import javafx.scene.image.Image;

public class BossEnemy extends Enemy {
    public BossEnemy(int posX, int posY, int id) {
        super(posX, posY, id);
        range = Config.BOSS_ENEMY_RANGE;
        health = Config.BOSS_ENEMY_HEALTH * Data.level;
        armor = Config.BOSS_ENEMY_ARMOR * Data.level;
        damage = Config.BOSS_ENEMY_DAMAGE * Data.level;
        velocity = Config.BOSS_ENEMY_VELOCITY;
        reward = Config.BOSS_ENEMY_REWARD;
        score = Config.BOSS_ENEMY_SCORE;

        imgSizeX = range * 2;
        imgSizeY = range * 1.5;

        Image[] imageList = new Image[4];
        for (int i = 1; i < 5; i++)
            imageList[i - 1] = new Image("BossEnemy/boss" + i + ".png");
        image = imageList;

        setup();
    }

    public BossEnemy(int posX, int posY, int id, int health) {
        this(posX,posY,id);
        this.health = health;

    }
}
