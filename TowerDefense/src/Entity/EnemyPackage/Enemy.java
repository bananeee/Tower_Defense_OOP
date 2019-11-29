package Entity.EnemyPackage;

import Database.Config;
import Database.Data;
import Entity.GameEntity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.File;

public abstract class Enemy extends GameEntity {
    private int posX; // enemy position in the middle of the line
    private int posY; //
    private int oriHealth;
    private int oriArmor;
    private boolean left;
    private int id;
    protected int range;
    public boolean isDestroyed;
    private int healthShowCount = 1000;

    private Circle arena;
    AudioClip clip = new AudioClip(new File("meow_short.mp3").toURI().toString());

    protected int health;
    protected int armor;
    protected int velocity;
    protected int damage;
    protected int reward;
    protected int score;
    protected double imgSizeX;
    protected double imgSizeY;
    protected Image[] image;

    public Enemy(int posX, int posY, int id) {
        this.posX = posX;
        this.posY = posY;
        this.id = id;
    }

    protected void setup() {
        arena = new Circle(posX, posY, range / 2);
        oriHealth = health;
        oriArmor = armor;
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

    public int getId() {
        return id;
    }

    public void isDamaged(boolean yes, int damage) {
        healthShowCount = 0;
        if (armor <= 0) health = health - damage;
        armor = armor - damage;
    }

    public void Destroyed() {
        clip.play(0.5);
        Data.playerMoney += reward;
        Data.playerScore += score;
        isDestroyed = true;
        Data.enemiesList.remove(this);
    }

    public boolean isPass() {
        if (posX >= Config.GATEX && posY <= Config.GATEY) {
            Data.playerHealth -= damage;
            return true;
        } else
            return false;
    }

    public void Walk() {
        posX += velocity * Direction(posX - Config.TILE_SIZE / 2, posY - Config.TILE_SIZE / 2)[0];
        posY += velocity * Direction(posX - Config.TILE_SIZE / 2, posY - Config.TILE_SIZE / 2)[1];
    }

    private int[] Direction(double x, double y) {
        boolean checkX;
        boolean checkY;
        if (x - (int) (x / Config.TILE_SIZE) * Config.TILE_SIZE > velocity)
            checkX = false;
        else
            checkX = true;
        if (y - (int) (y / Config.TILE_SIZE) * Config.TILE_SIZE > velocity)
            checkY = false;
        else
            checkY = true;
        int cox = (int) x / Config.TILE_SIZE;
        int coy = (int) y / Config.TILE_SIZE;
        int[] dir = new int[2];
        switch (Config.mapTower[coy][cox]) {
            case 4:
                if (checkY == false) {
                    dir[0] = 0;
                    dir[1] = -1;
                } else {
                    dir[0] = -1;
                    dir[1] = 0;
                    left = true;
                }
                break;
            case 6:
                if (checkY == false) {
                    dir[0] = 0;
                    dir[1] = -1;
                } else {
                    dir[0] = 1;
                    dir[1] = 0;
                    left = false;
                }
                break;
            case 2:
                if (checkX == false) {
                    dir[0] = -1;
                    dir[1] = 0;
                } else {
                    dir[0] = 0;
                    dir[1] = 1;
                }
                break;
            case 8:
                if (checkX == false) {
                    dir[0] = -1;
                    dir[1] = 0;
                } else {
                    dir[0] = 0;
                    dir[1] = -1;
                }
                break;
        }
        return dir;
    }

    public void onUpdate() {
        if (healthShowCount != 1000)
            healthShowCount ++;
        else if (healthShowCount == Config.HEALTH_SHOW_TIME)
            healthShowCount = 1000;
        Walk();
        if (health <= 0 || isPass())
            Destroyed();
        arena.setCenterX(posX);
        arena.setCenterY(posY);
    }

    private Image getFrame(int tickCount) {
        int index = (int) ((tickCount % (image.length * 3)) / 3);
        return image[index];
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(getFrame(tickCount), posX - imgSizeX / 2, posY - imgSizeY  * 3/4, imgSizeX, imgSizeY);

        if (healthShowCount < Config.HEALTH_SHOW_TIME) {
            gc.setFill(Color.RED);
            gc.fillRect(posX- Config.TILE_SIZE / 2 - 13, posY - Config.TILE_SIZE * 1.25, 75 ,3);

            gc.setFill(Color.GREEN);
            gc.fillRect(posX - Config.TILE_SIZE / 2 - 13, posY - Config.TILE_SIZE * 1.25, health * 75 / oriHealth, 3 );

            if (oriArmor != 0) {
                gc.setFill(Color.SILVER);
                gc.fillRect(posX - Config.TILE_SIZE / 2 - 13, posY - Config.TILE_SIZE * 1.25, armor * 75 / oriArmor, 3 );
            }
        }
    }

    public int getHealth() {
        return health;
    }

    public Circle getArena() {
        return arena;
    }


}
