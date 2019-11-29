package Entity.TowerPackage;

import Database.Config;
import Database.Data;
import Entity.BulletPackage.NormalBullet;
import javafx.scene.image.Image;

public class NormalTower extends Tower {


    public NormalTower(int posX, int posY) {
        super(posX, posY);
        price = Config.NORMAL_TOWER_PRICE;
        frequence = Config.NORMAL_TOWER_FREQUENCE;
//        fireTime = 13;
        range = Config.NORMAL_TOWER_RANGE;
        Data.playerMoney -= price;
        image = new Image("tower.png");
        Image[] test = new Image[14];
        for (int i = 1; i < 15; i++)
            test[i - 1] = new Image("NormalTower/cat_a1_super_" + i + ".png");
        Image[] testL = new Image[14];
        for (int i = 1; i < 15; i++)
            testL[i - 1] = new Image("NormalTower/cat_a1_superL_" + i + ".png");
        imageList = test;
        imageListL = testL;
        imageSizeX = Config.TILE_SIZE * 1.25;
        imageSizeY = Config.TILE_SIZE * 1.25;
        imageList = test;
        imageListL = testL;
    }

    public NormalTower(int posX, int posY, boolean upgrade) {
        this(posX, posY);
        if (upgrade)
            upgrade();
    }

    @Override
    protected void creatBullet() {
//        AudioClip clip = new AudioClip(new File("meow_short.mp3").toURI().toString());
//        clip.play(25);
        Data.bulletsList.add(new NormalBullet(this));
    }
}
