package Entity.BulletPackage;

import Database.Config;
import Entity.EnemyPackage.Enemy;
import Entity.TowerPackage.Tower;
import javafx.scene.image.Image;

public class MachineBullet extends Bullet {
    public MachineBullet(Tower tower) {
        super(tower);
        damage = Config.MACHINE_BULLET_DAMAGE;
        range = Config.MACHINE_BULLET_RANGE;
        velocity = Config.MACHINE_BULLET_VELOCITY;
        image = new Image("MachineBullet.png");
        setup();
    }

    public MachineBullet(Enemy enemy, int posX, int posY) {
        super(enemy, posX, posY);
        damage = Config.MACHINE_BULLET_DAMAGE;
        range = Config.MACHINE_BULLET_RANGE;
        velocity = Config.MACHINE_BULLET_VELOCITY;
        image = new Image("bullet6.png");
        setup();
    }
}
