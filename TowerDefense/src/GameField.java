import Database.Config;
import Database.Data;
import Database.MapDrawer;
import Entity.*;
import Entity.BulletPackage.Bullet;
import Entity.EnemyPackage.Enemy;
import Entity.TowerPackage.MachineTower;
import Entity.TowerPackage.NormalTower;
import Entity.TowerPackage.SniperTower;
import Entity.TowerPackage.Tower;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GameField {
    private Pane pane;
    private Scene field;
    private Canvas canvas_animation;
    private Canvas canvas_background;
    private GraphicsContext gc_animation;
    private GraphicsContext gc_background;

    private int getButton = -1;

    private ImageView normalTower = new ImageView("NormalTower/cat_a1_super_1.png");
    private ImageView sniperTower = new ImageView("SniperTower/Layer_1.png");
    private ImageView machineTower = new ImageView("MachineTower/MachineTower_1.png");
    private ImageView speed = new ImageView("speed.png");
    private ImageView pause = new ImageView("pause.png");
    private ImageView save = new ImageView("save.png");
    private ImageView upgrade = new ImageView("upgrade.png");
    private ImageView sell = new ImageView("sell.png");
    private ImageView bomb = new ImageView("skull.png");
    private ImageView lightning = new ImageView("lightning.png");

    private Circle circle = new Circle();

    public GameField() {
        pane = new Pane();
        field = new Scene(pane);
        canvas_animation = new Canvas(Config.TILE_SIZE * Config.SCREEN_WITDH, Config.TILE_SIZE * Config.SCREEN_HEIGHT);
        canvas_background = new Canvas(Config.TILE_SIZE * Config.SCREEN_WITDH, Config.TILE_SIZE * Config.SCREEN_HEIGHT);

        pane.getChildren().addAll(canvas_background, canvas_animation);
        gc_animation = canvas_animation.getGraphicsContext2D();
        gc_background = canvas_background.getGraphicsContext2D();
        //vẽ map
        new MapDrawer().draw(gc_background);

        normalTower.setX(Config.NORMAL_TOWER_COORDINATE[0]);
        normalTower.setY(Config.NORMAL_TOWER_COORDINATE[1]);
        normalTower.setFitWidth(Config.TILE_SIZE * 1.5);
        normalTower.setFitHeight(Config.TILE_SIZE * 1.5);

        sniperTower.setX(Config.SNIPER_TOWER_COORDINATE[0]);
        sniperTower.setY(Config.SNIPER_TOWER_COORDINATE[1]);
        sniperTower.setFitWidth(Config.TILE_SIZE * 1.5);
        sniperTower.setFitHeight(Config.TILE_SIZE * 1.5);

        machineTower.setX(Config.MACHINE_TOWER_COORDINATE[0]);
        machineTower.setY(Config.MACHINE_TOWER_COORDINATE[1]);
        machineTower.setFitWidth(Config.TILE_SIZE * 1.5);
        machineTower.setFitHeight(Config.TILE_SIZE * 1.5);

        speed.setX(Config.TILE_SIZE * 23);
        speed.setY(Config.TILE_SIZE * 13);
        speed.setFitWidth(Config.TILE_SIZE);
        speed.setFitHeight(Config.TILE_SIZE);

        pause.setX(Config.TILE_SIZE * 24);
        pause.setY(Config.TILE_SIZE * 13);
        pause.setFitWidth(Config.TILE_SIZE);
        pause.setFitHeight(Config.TILE_SIZE);

        save.setX(Config.TILE_SIZE * 22);
        save.setY(Config.TILE_SIZE * 13);
        save.setFitWidth(Config.TILE_SIZE);
        save.setFitHeight(Config.TILE_SIZE);

        sell.setX(Config.TILE_SIZE * 20);
        sell.setY(Config.TILE_SIZE * 13);
        sell.setFitWidth(Config.TILE_SIZE);
        sell.setFitHeight(Config.TILE_SIZE);

        upgrade.setX(Config.TILE_SIZE * 21);
        upgrade.setY(Config.TILE_SIZE * 13);
        upgrade.setFitWidth(Config.TILE_SIZE);
        upgrade.setFitHeight(Config.TILE_SIZE);

        bomb.setX(Config.TILE_SIZE * 14);
        bomb.setY(Config.TILE_SIZE * 13);
        bomb.setFitWidth(Config.TILE_SIZE);
        bomb.setFitHeight(Config.TILE_SIZE);

        lightning.setX(Config.TILE_SIZE * 15);
        lightning.setY(Config.TILE_SIZE * 13);
        lightning.setFitWidth(Config.TILE_SIZE);
        lightning.setFitHeight(Config.TILE_SIZE);

        pane.getChildren().addAll(normalTower, machineTower, sniperTower, speed, pause, save, sell, upgrade, circle, bomb, lightning);

    }

    public void activeButton() {

        normalTower.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (Data.playerMoney >= Config.NORMAL_TOWER_PRICE) {
                getButton = Config.NORMAL_TOWER;
                circle.setRadius(Config.NORMAL_TOWER_RANGE);
            }
            event.consume();
        });
        sniperTower.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (Data.playerMoney >= Config.SNIPER_TOWER_PRICE) {
                getButton = Config.SNIPER_TOWER;
                circle.setRadius(Config.SNIPER_TOWER_RANGE);
            }
            event.consume();
        });
        machineTower.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (Data.playerMoney >= Config.MACHINE_TOWER_PRICE) {
                getButton = Config.MACHINE_TOWER;
                circle.setRadius(Config.MACHINE_TOWER_RANGE);
            }
            event.consume();
        });
        speed.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (Data.gameSpeed == 1)
                Data.gameSpeed = 2;
            else
                Data.gameSpeed = 1;
            event.consume();
        });
        pause.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (Data.gamePause == true) {
                Data.gamePause = false;
                pause.setImage(new Image("pause.png"));
            } else {
                Data.gamePause = true;
                pause.setImage(new Image("resume.png"));
            }
            event.consume();
        });
        save.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            saveGame();
            event.consume();
        });
        sell.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            getButton = Config.SELL;
            event.consume();
        });
        upgrade.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            getButton = Config.UPGRADE;
            event.consume();
        });
        bomb.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (Data.playerMoney >= Config.BOMB_PRICE) {
                getButton = Config.BOMB;
                circle.setRadius(Config.BOMB_RANGE);
            }
            event.consume();
        });
        lightning.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (Data.lightningIsReady == true) {
                getButton = Config.LIGHTNING;
                circle.setRadius(Config.LIGHTNING_RANGE);
            }
            event.consume();
        });

        field.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (getButton > 0)
                    doRelease(event);
                event.consume();
            }
        });

        field.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int x = (int) (event.getX() / Config.TILE_SIZE);
                int y = (int) (event.getY() / Config.TILE_SIZE);
                Tower tower = findTower(x * Config.TILE_SIZE, y * Config.TILE_SIZE);
                if (getButton > 0 && getButton <= Config.MACHINE_TOWER) {

                    circle.setCenterX(x * Config.TILE_SIZE + Config.TILE_SIZE / 2);
                    circle.setCenterY(y * Config.TILE_SIZE + Config.TILE_SIZE / 2);
                    if (y < Config.GAME_SCENE[0] && Data.mapTower[y][x] == 0)
                        circle.setFill(Color.color(0, 1, 0, 0.2));
                    else
                        circle.setFill(Color.color(1, 153 / 255, 204 / 255, 0.2));
                    circle.setVisible(true);

                } else if (getButton >= Config.BOMB && getButton <= Config.LIGHTNING) {

                    circle.setCenterX(x * Config.TILE_SIZE + Config.TILE_SIZE / 2);
                    circle.setCenterY(y * Config.TILE_SIZE + Config.TILE_SIZE / 2);
                    if (y < Config.GAME_SCENE[0] && Data.mapTower[y][x] >= 2 && Data.mapTower[y][x] <= 8)
                        circle.setFill(Color.color(0, 1, 0, 0.2));
                    else
                        circle.setFill(Color.color(1, 153 / 255, 204 / 255, 0.2));
                    circle.setVisible(true);

                } else if (tower != null) {
                    circle.setRadius(findTower(x * Config.TILE_SIZE, y * Config.TILE_SIZE).getRange());
                    circle.setCenterX(x * Config.TILE_SIZE + Config.TILE_SIZE / 2);
                    circle.setCenterY(y * Config.TILE_SIZE + Config.TILE_SIZE / 2);
                    if (getButton == Config.UPGRADE && (Data.playerMoney < tower.getPrice() * 2.5 || tower.getUpgrade() == true))
                        circle.setFill(Color.color(1, 153 / 255, 204 / 255, 0.2));
                    else
                        circle.setFill(Color.color(0, 1, 0, 0.2));
                    circle.setVisible(true);

                } else
                    circle.setVisible(false);
            }
        });
    }

    private void doRelease(MouseEvent event) {
        int x = (int) (event.getX() / Config.TILE_SIZE);
        int y = (int) (event.getY() / Config.TILE_SIZE);
        if (Data.mapTower[y][x] == 0) {
            Data.mapTower[y][x] = 1;
            switch (getButton) {
                case Config.NORMAL_TOWER:
                    Data.towersList.add(new NormalTower(x * Config.TILE_SIZE + Config.TILE_SIZE / 2, y * Config.TILE_SIZE + Config.TILE_SIZE / 2));
                    break;
                case Config.SNIPER_TOWER:
                    Data.towersList.add(new SniperTower(x * Config.TILE_SIZE + Config.TILE_SIZE / 2, y * Config.TILE_SIZE + Config.TILE_SIZE / 2));
                    break;
                case Config.MACHINE_TOWER:
                    Data.towersList.add(new MachineTower(x * Config.TILE_SIZE + Config.TILE_SIZE / 2, y * Config.TILE_SIZE + Config.TILE_SIZE / 2));
                    break;
            }
        } else if (Data.mapTower[y][x] >= 2 && Data.mapTower[y][x] <= 8) {
            switch (getButton) {
                case Config.LIGHTNING:
                    Data.lightning = new Lightning(x * Config.TILE_SIZE + Config.TILE_SIZE / 2, y * Config.TILE_SIZE + Config.TILE_SIZE / 2);
                    Data.lightningIsReady = false;
                    break;
                case Config.BOMB:
                    Data.playerMoney -= Config.BOMB_PRICE;
                    Data.bombsList.add(new Bomb(x * Config.TILE_SIZE + Config.TILE_SIZE / 2, y * Config.TILE_SIZE + Config.TILE_SIZE / 2));
            }
        } else {
            Tower tower = findTower(x * Config.TILE_SIZE, y * Config.TILE_SIZE);
            if (tower != null)
                switch (getButton) {
                    case Config.SELL:
                        Data.mapTower[y][x] = 0;
                        Data.playerMoney += tower.getPrice() / 2;
                        Data.towersList.remove(tower);
                        break;
                    case Config.UPGRADE:
                        if (Data.playerMoney >= tower.getPrice() * 2.5 && tower.getUpgrade() == false) {
                            Data.playerMoney -= tower.getPrice() * 2.5;
                            tower.upgrade();
                            System.out.println(tower.getRange());
                        }
                        break;
                }
        }
        getButton = -1;

    }

    public void saveGame() {

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(new File("save_game.txt"));
            BufferedWriter writer = new BufferedWriter(fileWriter);
            fileWriter.write(Data.gameOver + "\n");
            fileWriter.write(Data.gamePause + "\n");
            fileWriter.write(Data.gameSpeed + "\n");
            fileWriter.write(Data.playerHealth + "\n");
            fileWriter.write(Data.playerMoney + "\n");
            fileWriter.write(Data.playerScore + "\n");
            fileWriter.write(Data.level + "\n");
            fileWriter.write(Data.lightningIsReady + "\n");
            fileWriter.write(Data.enemiesList.size() + "\n");
            fileWriter.write(Data.towersList.size() + "\n");
            fileWriter.write(Data.bulletsList.size() + "\n");
            fileWriter.write(Data.bombsList.size() + "\n");
            for (Enemy enemy : Data.enemiesList) {
                fileWriter.write(enemy.getClass() + "-" + enemy.getPosX() + "-" + enemy.getPosY() + "-" + enemy.getId() + "-" + enemy.getHealth() + "\n");
            }
            for (Tower tower : Data.towersList) {
                fileWriter.write(tower.getClass() + "-" + tower.getPosX() + "-" + tower.getPosY() + "-" + tower.getUpgrade() + "\n");
            }
            for (Bullet bullet : Data.bulletsList) {
                fileWriter.write(bullet.getClass() + "-" + bullet.getEnemy().getId() + "-" + bullet.getPosX() + "-" + bullet.getPosY() + "\n");
            }
            for (Bomb bomb : Data.bombsList) {
                fileWriter.write(bomb.getPosX() + "-" + bomb.getPosY() + "\n");
            }

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Tower findTower(int x, int y) {
        for (Tower tower : Data.towersList)
            if (tower.getPosX() == x + Config.TILE_SIZE / 2 && tower.getPosY() == y + Config.TILE_SIZE / 2) {
                return tower;
            }
        return null;
    }

    public Scene getScene() {
        return field;
    }

    public Pane getPane() {
        return pane;
    }

    public GraphicsContext getGc_animation() {
        return gc_animation;
    }

    public GraphicsContext getGc_background() {
        return gc_background;
    }
}
