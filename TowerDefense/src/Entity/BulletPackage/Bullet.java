package Entity.BulletPackage;

import Database.Data;
import Entity.GameEntity;
import Entity.EnemyPackage.Enemy;
import Entity.TowerPackage.Tower;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public abstract class Bullet extends GameEntity {
    protected int damage;

    private Tower tower;

    private Enemy enemy;
    private int enemyX;
    private int enemyY;

    private int posX; // the center point
    private int posY;

    protected int range;
    private Circle arena;

    protected double velocity;

    //    protected Image[] imageList;
    protected Image image;

    private double degree;
    private double velocityX;
    private double velocityY;

    public Bullet(Tower tower) {
        this.tower = tower;
        enemy = tower.getEnemy();
        enemyX = enemy.getPosX();
        enemyY = enemy.getPosY();
        posX = tower.getPosX();
        posY = tower.getPosY();
        degree = Math.atan2(enemyY - posY, enemyX - posX);

    }

    public Bullet(Enemy enemy, int posX, int posY) {
        this.enemy = enemy;
        this.posX = posX;
        this.posY = posY;
    }

    protected void setup() {
        velocityX = velocity * Math.cos(degree);
        velocityY = velocity * Math.sin(degree);
        arena = new Circle(posX, posY, range);
    }

    public void onUpdate() {
        run();
        arena.setCenterX(posX);
        arena.setCenterY(posY);
        doDamage();
        isDestroy();
    }

    private void run() {
        enemyX = enemy.getPosX();
        enemyY = enemy.getPosY();
        degree = Math.atan2(enemyY - posY, enemyX - posX);
        velocityX = velocity * Math.cos(degree);
        velocityY = velocity * Math.sin(degree);
        posX += ((int) velocityX);
        posY += ((int) velocityY);
    }

    private boolean doDamage() {
        if (Math.sqrt(Math.pow(posX - enemy.getPosX(), 2) + Math.pow(posY - enemy.getPosY(), 2)) <= range) {
            Data.bulletsList.remove(this);
            enemy.isDamaged(true, damage);
            return true;
        } else
            return false;
    }

    private boolean isDestroy() {
        if (enemy.isDestroyed) {
            Data.bulletsList.remove(this);
            return true;
        } else
            return false;
    }

//    public void draw(GraphicsContext gc) {
//        gc.drawImage(image, posX - range, posY - range, range * 2, range * 2);
//    }

    public void draw(GraphicsContext gc) {

        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        ImageView iv = new ImageView(image);
        iv.setRotate( degree * 180 / Math.PI + 180);
        Image base = iv.snapshot(params, null);

        gc.drawImage(base, posX - 5 * range, posY - 5 * range, range * 10, range * 10);

    }

//    private Image getFrame(int tickCount) {
//        int index = (int) ((tickCount % (imageList.length * 4)) / 4);
//        return imageList[index];
//
//    }


    public int getDamage() {
        return damage;
    }

    public Tower getTower() {
        return tower;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public int getEnemyX() {
        return enemyX;
    }

    public int getEnemyY() {
        return enemyY;
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

    public Circle getArena() {
        return arena;
    }

    public double getVelocity() {
        return velocity;
    }

    public double getDegree() {
        return degree;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }
}
