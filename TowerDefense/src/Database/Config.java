package Database;

import Entity.SpawnEnemy;

public class Config {
    public static final int TILE_SIZE = 48;
    public static final int SCREEN_WITDH = 25;
    public static final int SCREEN_HEIGHT = 15;

    public static final int playerHealth = 1;
    public static final int playerMoney = 1000;
    public static final int playerScore = 0;

    public static final SpawnEnemy SPAWN_ENEMY = new SpawnEnemy();
    public static final int[] SPAWN_COORDINATE = {Config.TILE_SIZE * 0 + Config.TILE_SIZE / 2, Config.TILE_SIZE * 3 + Config.TILE_SIZE / 2};

    public static final int[] NORMAL_TOWER_COORDINATE = {TILE_SIZE * 3, TILE_SIZE * 13};
    public static final int[] SNIPER_TOWER_COORDINATE = {TILE_SIZE * 7, TILE_SIZE * 13};
    public static final int[] MACHINE_TOWER_COORDINATE = {TILE_SIZE * 11, TILE_SIZE * 13};

    public static final int[] GAME_SCENE = {TILE_SIZE * 25, TILE_SIZE * 12};

    public static final int NORMAL_TOWER = 10000;
    public static final int SNIPER_TOWER = 20000;
    public static final int MACHINE_TOWER = 30000;
    public static final int BOMB = 40000;
    public static final int LIGHTNING = 50000;
    public static final int SELL = 60000;
    public static final int UPGRADE = 70000;

    public static final int BUTTON_SIZE = TILE_SIZE;

    public static final int NORMAL_BULLET_DAMAGE = 10;
    public static final int NORMAL_BULLET_RANGE = TILE_SIZE / 3;
    public static final int NORMAL_BULLET_VELOCITY = 7;

    public static final int SNIPER_BULLET_DAMAGE = 30;
    public static final int SNIPER_BULLET_RANGE = TILE_SIZE / 8;
    public static final int SNIPER_BULLET_VELOCITY = 15;

    public static final int MACHINE_BULLET_DAMAGE = 15;
    public static final int MACHINE_BULLET_RANGE = TILE_SIZE / 8;
    public static final int MACHINE_BULLET_VELOCITY = 15;

    public static final int NORMAL_TOWER_PRICE = 40;
    public static final int NORMAL_TOWER_RANGE = TILE_SIZE * 3;
    public static final int NORMAL_TOWER_FREQUENCE = 30;

    public static final int SNIPER_TOWER_PRICE = 60;
    public static final int SNIPER_TOWER_RANGE = TILE_SIZE * 5;
    public static final int SNIPER_TOWER_FREQUENCE = 30;

    public static final int MACHINE_TOWER_PRICE = 70;
    public static final int MACHINE_TOWER_RANGE = TILE_SIZE * 4;
    public static final int MACHINE_TOWER_FREQUENCE = 12;

    public static final int NORMAL_ENEMY_HEALTH = 100;
    public static final int NORMAL_ENEMY_ARMOR = 100;
    public static final int NORMAL_ENEMY_VELOCITY = 1;
    public static final int NORMAL_ENEMY_DAMAGE = 2;
    public static final int NORMAL_ENEMY_REWARD = 10;
    public static final int NORMAL_ENEMY_SCORE = 10;
    public static final int NORMAL_ENEMY_RANGE = TILE_SIZE;

    public static final int SMALLER_ENEMY_HEALTH = 50;
    public static final int SMALLER_ENEMY_ARMOR = 0;
    public static final int SMALLER_ENEMY_VELOCITY = 2;
    public static final int SMALLER_ENEMY_DAMAGE = 1;
    public static final int SMALLER_ENEMY_REWARD = 15;
    public static final int SMALLER_ENEMY_SCORE = 15;
    public static final int SMALLER_ENEMY_RANGE = TILE_SIZE;

    public static final int TANKER_ENEMY_HEALTH = 300;
    public static final int TANKER_ENEMY_ARMOR = 500;
    public static final int TANKER_ENEMY_VELOCITY = 1;
    public static final int TANKER_ENEMY_DAMAGE = 3;
    public static final int TANKER_ENEMY_REWARD = 25;
    public static final int TANKER_ENEMY_SCORE = 25;
    public static final int TANKER_ENEMY_RANGE = TILE_SIZE;

    public static final int BOSS_ENEMY_HEALTH = 1000;
    public static final int BOSS_ENEMY_ARMOR = 1000;
    public static final int BOSS_ENEMY_VELOCITY = 1;
    public static final int BOSS_ENEMY_DAMAGE = 10;
    public static final int BOSS_ENEMY_REWARD = 75;
    public static final int BOSS_ENEMY_SCORE = 75;
    public static final int BOSS_ENEMY_RANGE = TILE_SIZE;

    public static final int HEALTH_SHOW_TIME = 60;

    public static final int LIGHTNING_RANGE = TILE_SIZE * 2;
    public static final int LIGHTNING_DAMAGE = 50;

    public static final int BOMB_PRICE = 20;
    public static final int BOMB_RANGE = TILE_SIZE;
    public static final int BOMB_DAMAGE = 75;

    public static int GATEX = 22 * TILE_SIZE + TILE_SIZE / 2;
    public static int GATEY = 3 * TILE_SIZE + TILE_SIZE / 2;

//    public static int GATEX = 24 * TILE_SIZE + TILE_SIZE / 2;
//    public static int GATEY = 9 * TILE_SIZE + TILE_SIZE / 2;

    public static int[][] mapTower = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6},
            {0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 6, 6, 2, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0},
            {0, 0, 0, 0, 9, 9, 9, 0, 8, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0},
            {6, 6, 2, 0, 9, 9, 9, 0, 8, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0},
            {0, 0, 2, 0, 9, 9, 9, 0, 8, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0},
            {0, 0, 2, 0, 9, 9, 9, 0, 8, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 2, 0, 0, 8, 0, 0},
            {1, 0, 2, 0, 9, 9, 9, 0, 8, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 8, 0, 0},
            {1, 0, 2, 0, 9, 9, 9, 0, 8, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 8, 0, 0},
            {1, 0, 2, 0, 9, 9, 9, 0, 8, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 8, 0, 0},
            {1, 0, 2, 0, 9, 9, 9, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 8, 6, 6},
            {1, 0, 6, 6, 6, 6, 6, 6, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 9, 9, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 9, 9, 9, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 9, 9, 9, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 9, 9, 9, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

}
