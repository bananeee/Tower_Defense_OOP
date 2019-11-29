package Entity.BulletPackage;

import Database.Config;
import Entity.EnemyPackage.Enemy;
import Entity.TowerPackage.Tower;
import javafx.scene.image.Image;

public class NormalBullet extends Bullet {

    public NormalBullet(Tower tower) {
        super(tower);
        damage = Config.NORMAL_BULLET_DAMAGE;
        range = Config.NORMAL_BULLET_RANGE;
        velocity = Config.NORMAL_BULLET_VELOCITY;
        image = new Image("NormalBullet.png");
        setup();
    }

    public NormalBullet(Enemy enemy, int posX, int posY) {
        super(enemy, posX, posY);
        damage = Config.NORMAL_BULLET_DAMAGE;
        range = Config.NORMAL_BULLET_RANGE;
        velocity = Config.NORMAL_BULLET_VELOCITY;
        image = new Image("bullet6.png");
        setup();
    }
}
