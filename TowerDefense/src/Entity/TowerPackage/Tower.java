package Entity.TowerPackage;

import Database.Config;
import Database.Data;
import Entity.GameEntity;
import Entity.EnemyPackage.Enemy;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Tower extends GameEntity {
    private int posX;
    private int posY;

    private int upgrade = 1;
    private boolean left = true;

    protected int range;
    protected int frequence;
    protected int price;
    protected Image image;
    protected double imageSizeX;
    protected double imageSizeY;
    protected Image[] imageList;
    protected Image[] imageListL;

    private Enemy enemy;

    public Tower(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }


    public int getPrice() {
        return price;
    }

    public boolean getUpgrade() {
        if (upgrade == 2)
            return true;
        else
            return false;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getRange() {
        return range;
    }

    private boolean inRange(int x, int y, int enemyRange) {
        return Math.sqrt(Math.pow(x - posX, 2) + Math.pow(y - posY, 2)) <= range + enemyRange;
    }

    public void upgrade() {
        upgrade = 2;
        range = range * upgrade - (int) (range * 0.5);
        price = (int) (price * 1.5);
    }

    public void onUpdate() {

        if (enemy == null || enemy.isDestroyed || inRange(enemy.getPosX(), enemy.getPosY(), enemy.getRange()) == false) {
            enemy = null;
            tickCount = 0;
        }
        for (int i = 0; i < Data.enemiesList.size(); i++)
            if (inRange(Data.enemiesList.get(i).getPosX(), Data.enemiesList.get(i).getPosY(), Data.enemiesList.get(i).getRange())) {
                enemy = Data.enemiesList.get(i);
                break;
            }
        isLeft();
        if (tickCount % frequence == frequence - 3 && enemy != null)
            creatBullet();
    }

    protected abstract void creatBullet();

    public void draw(GraphicsContext gc) {
        gc.drawImage(getFrame(tickCount), posX - imageSizeX / 2, posY - imageSizeY / 2, imageSizeX, imageSizeY);
        if (upgrade == 2)
            gc.drawImage(new Image("star.png"), posX + Config.TILE_SIZE / 4, posY - Config.TILE_SIZE / 4, Config.TILE_SIZE / 4, Config.TILE_SIZE / 4);

    }


    private Image getFrame(int tickCount) {
        int index = (int) ((tickCount % (imageList.length * 3)) / 3);
        if (left == false)
            return imageList[index];
        else
            return imageListL[index];

    }

    private void isLeft() {
        if (enemy != null)
            if (enemy.getPosX() < posX)
                left = true;
            else
                left = false;
    }

}
