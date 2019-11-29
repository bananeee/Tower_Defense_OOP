import Database.Config;
import Database.Data;
import Entity.GameEntity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class GameRun {
    private GraphicsContext gc;
    private ArrayList<GameEntity> entitiesList = new ArrayList<>();

    public GameRun(GraphicsContext gc) {
        this.gc = gc;
        entitiesList.addAll(Data.towersList);
        entitiesList.addAll(Data.enemiesList);
        entitiesList.addAll(Data.bulletsList);
        entitiesList.addAll(Data.bombsList);
        if (Data.lightning != null)
            entitiesList.add(Data.lightning);
        entitiesList.add(Config.SPAWN_ENEMY);
    }


    private void render() {
        for (GameEntity entity : entitiesList)
            entity.draw(gc);

        if (Data.lightningIsReady) {
            gc.setFill(new Color(0.9,0.9,0.9,0.2));
            gc.fillOval(Config.TILE_SIZE * 15, Config.TILE_SIZE * 13, Config.TILE_SIZE, Config.TILE_SIZE);
        }
        gc.setFill(Color.YELLOW);
        gc.setFont(new Font("MV Boli", 30));
        gc.fillText("" + Data.playerHealth, 18 * Config.TILE_SIZE, 13 * Config.TILE_SIZE);
        gc.fillText("" + Data.playerMoney, 18 * Config.TILE_SIZE, 14 * Config.TILE_SIZE);
        gc.setFill(Color.BLACK);
        gc.fillText("Score: " + Data.playerScore, 1 * Config.TILE_SIZE, 1 * Config.TILE_SIZE);
    }

    private void doRun() {
        for (int i = 0; i < Data.gameSpeed; i++) {
            for (GameEntity entity : entitiesList) {
                entity.tick();
                entity.onUpdate();
            }
        }
    }

    public void clearData() {
        Data.playerHealth = Config.playerHealth;
        Data.playerMoney = Config.playerMoney;
        Data.playerScore = Config.playerScore;
        Data.enemiesList = new ArrayList<>();
        Data.bulletsList = new ArrayList<>();
        Data.towersList = new ArrayList<>();
        Data.bombsList = new ArrayList<>();
        Data.lightning = null;
        Data.gameOver = false;
        Data.mapTower = Config.mapTower;
        Data.level = 1;
        Data.gameSpeed = 1;
    }

    public void run() {
        if (Data.gamePause == false) {
            doRun();
        }
        render();

    }

}
