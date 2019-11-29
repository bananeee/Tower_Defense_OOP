package Entity.BulletPackage;

import Database.Config;
import Entity.EnemyPackage.Enemy;
import Entity.TowerPackage.Tower;
import javafx.scene.image.Image;

public class SniperBullet extends Bullet {
    public SniperBullet(Tower tower) {
        super(tower);
        damage = Config.SNIPER_BULLET_DAMAGE;
        range = Config.SNIPER_BULLET_RANGE;
        velocity = Config.SNIPER_BULLET_VELOCITY;
        image = new Image("SniperBullet.png");
        setup();
    }

    public SniperBullet(Enemy enemy, int posX, int posY) {
        super(enemy, posX, posY);
        damage = Config.SNIPER_BULLET_DAMAGE;
        range = Config.SNIPER_BULLET_RANGE;
        velocity = Config.SNIPER_BULLET_VELOCITY;
        image = new Image("bullet6.png");
        setup();
    }
}
