package Entity;

import Database.Config;
import Database.Data;
import Entity.EnemyPackage.Enemy;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

import java.io.File;

public class Lightning extends GameEntity {
    private int posX;
    private int posY;

    private int range = Config.LIGHTNING_RANGE;
    private int damage = Config.LIGHTNING_DAMAGE;
    private Image[] image = new Image[8];
    AudioClip clip = new AudioClip(new File("lightning.mp3").toURI().toString());

    public Lightning(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        clip.play(1);
        for (Enemy enemy : Data.enemiesList) {
            if (inRange(enemy.getPosX(), enemy.getPosY(), enemy.getRange()))
                enemy.isDamaged(true, damage);
        }
        for (int i = 1; i < 9; i++)
            image[i - 1] = new Image("Lightning/Lightning_" + i + ".png");
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    private boolean inRange(int x, int y, int enemyRange) {
        return Math.sqrt(Math.pow(x - posX, 2) + Math.pow(y - posY, 2)) <= range + enemyRange;
    }

    @Override
    public void onUpdate() {
        if (tickCount % ((image.length - 1) * 5) == 0)
            Data.lightning = null;
    }

    private Image getFrame(int tickCount) {
        int index = (int) ((tickCount % (image.length * 5)) / 5);
        return image[index];
    }

    public void draw(GraphicsContext gc) {
//        gc.drawImage(getFrame(tickCount), posX - range / 4, posY - 0.75 * range, range, range);
//        gc.drawImage(getFrame(tickCount), posX - range / 2, posY - 1.5 * range, range, range);
//        gc.drawImage(getFrame(tickCount), posX, posY - range, range, range);
//        gc.drawImage(getFrame(tickCount), posX - range, posY - range / 2, range, range);
//        gc.drawImage(getFrame(tickCount), posX, posY - range / 6, range, range);
        gc.drawImage(getFrame(tickCount), posX - range * 1.5, posY - range * 2.75, range * 3, range * 3);
    }
}
