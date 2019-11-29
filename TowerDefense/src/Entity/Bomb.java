package Entity;

import Database.Config;
import Database.Data;
import Entity.EnemyPackage.Enemy;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

import java.io.File;

public class Bomb extends GameEntity {
    private int posX;
    private int posY;
    private int range = Config.BOMB_RANGE;
    ;
    private int price = Config.BOMB_PRICE;
    ;
    private boolean detected = false;

    private Image image[] = new Image[15];
    AudioClip clip = new AudioClip(new File("bomb.mp3").toURI().toString());

    public Bomb(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        for (int i = 1; i < 16; i++)
            image[i - 1] = new Image("Bomb/Bomb_" + i + ".png");
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int distance(int enemyX, int enemyY) {

        return (int) Math.sqrt((enemyX - posX) * (enemyX - posX) + (enemyY - posY) * (enemyY - posY));
    }

    @Override
    public void onUpdate() {

        for (Enemy enemy : Data.enemiesList) {
            if (distance(enemy.getPosX(), enemy.getPosY()) <= range) {
                detected = true;
                break;
            }
        }
        if (detected == false)
            tickCount = 0;
        if (tickCount == 45) {
            Data.bombsList.remove(this);
            for (Enemy enemy : Data.enemiesList) {
                if (distance(enemy.getPosX(), enemy.getPosY()) <= range)
                    enemy.isDamaged(true, Config.BOMB_DAMAGE);
            }
        }
        else if (tickCount == 20)
            clip.play(1);
    }

    private Image getFrame(int tickCount) {
        int index = (int) ((tickCount % (image.length * 4)) / 4);
        return image[index];

    }

    @Override
    public void draw(GraphicsContext gc) {
        if (detected == true)
            gc.drawImage(getFrame(tickCount), posX - Config.TILE_SIZE, posY - 2 * Config.TILE_SIZE, Config.TILE_SIZE * 2, Config.TILE_SIZE * 8/3);
        else
            gc.drawImage(new Image("Bomb/Bomb_0.png"), posX - Config.TILE_SIZE, posY - 2 * Config.TILE_SIZE, Config.TILE_SIZE * 2, Config.TILE_SIZE * 8/3);
    }
}
