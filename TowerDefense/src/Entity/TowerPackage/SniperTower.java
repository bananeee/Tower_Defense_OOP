package Entity.TowerPackage;

import Database.Config;
import Database.Data;
import Entity.BulletPackage.SniperBullet;
import javafx.scene.image.Image;

public class SniperTower extends Tower {
    public SniperTower(int posX, int posY) {
        super(posX, posY);
        price = Config.SNIPER_TOWER_PRICE;
        frequence = Config.SNIPER_TOWER_FREQUENCE;
        range = Config.SNIPER_TOWER_RANGE;
        Data.playerMoney -= price;
        image = new Image("tower.png");
        imageSizeX = Config.TILE_SIZE * 1.75;
        imageSizeY = Config.TILE_SIZE * 1.75;
        Image[] test = new Image[10];
        for (int i = 1; i < 11; i++)
            test[i - 1] = new Image("SniperTower/Layer_" + i + ".png");
        Image[] testL = new Image[10];
        for (int i = 1; i < 11; i++)
            testL[i - 1] = new Image("SniperTower/Layer_" + i + ".png");
        imageList = test;
        imageListL = testL;
    }

    public SniperTower(int posX, int posY, boolean upgrade) {
        this(posX, posY);
        if (upgrade)
            upgrade();
    }

    @Override
    protected void creatBullet() {
        Data.bulletsList.add(new SniperBullet(this));
    }
}
