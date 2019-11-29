package Database;

import Entity.*;
import Entity.BulletPackage.Bullet;
import Entity.EnemyPackage.Enemy;
import Entity.TowerPackage.Tower;

import java.util.ArrayList;

public class Data {
    public static boolean gameOver = false;
    public static boolean gamePause = false;

    public static int playerHealth = Config.playerHealth;
    public static int playerMoney = Config.playerMoney;
    public static int playerScore = Config.playerScore;

    public static int level = 1;

    public static int gameSpeed = 1;

    public static boolean lightningIsReady = true;

    public static boolean isLoadMap = false;

    public static ArrayList<Enemy> enemiesList = new ArrayList<>();
    public static ArrayList<Tower> towersList = new ArrayList<>();
    public static ArrayList<Bullet> bulletsList = new ArrayList<>();
    public static ArrayList<Bomb> bombsList = new ArrayList<>();
    public static Lightning lightning = null;

    public static int[][] mapTower = Config.mapTower;

}
