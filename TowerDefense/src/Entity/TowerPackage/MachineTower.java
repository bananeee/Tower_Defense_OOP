package Entity.TowerPackage;

import Database.Config;
import Database.Data;
import Entity.BulletPackage.MachineBullet;
import javafx.scene.image.Image;

public class MachineTower extends Tower {
    public MachineTower(int posX, int posY) {
        super(posX, posY);
        price = Config.MACHINE_TOWER_PRICE;
        frequence = Config.MACHINE_TOWER_FREQUENCE;
        range = Config.MACHINE_TOWER_RANGE;
        Data.playerMoney -= price;
        image = new Image("tower.png");
        Image[] test = new Image[4];
        for (int i = 1; i < 5; i++)
            test[i - 1] = new Image("MachineTower/MachineTower_" + i + ".png");
        Image[] testL = new Image[4];
        for (int i = 1; i < 5; i++)
            testL[i - 1] = new Image("MachineTower/MachineTower_" + i + ".png");
        imageList = test;
        imageListL = testL;
        imageSizeX = Config.TILE_SIZE * 1.75;
        imageSizeY = Config.TILE_SIZE * 1.75;
    }

    public MachineTower(int posX, int posY, boolean upgrade) {
        this(posX, posY);
        if (upgrade)
            upgrade();
    }
    @Override
    protected void creatBullet() {
        Data.bulletsList.add(new MachineBullet(this));
    }
}
