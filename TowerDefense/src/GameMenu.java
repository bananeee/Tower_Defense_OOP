import Database.Config;
import Database.Data;
import Database.Map;
import Database.MapDrawer;
import Entity.Bomb;
import Entity.BulletPackage.MachineBullet;
import Entity.BulletPackage.NormalBullet;
import Entity.BulletPackage.SniperBullet;
import Entity.EnemyPackage.*;
import Entity.TowerPackage.MachineTower;
import Entity.TowerPackage.NormalTower;
import Entity.TowerPackage.SniperTower;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

public class GameMenu {
    private Stage stage;
    private AnchorPane anchorPane;
    private Scene menu;
    private Scene field;
    private GraphicsContext gc_animation;
    private GraphicsContext gc_background;

    Media media = new Media(new File("happy.mp3").toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    ImageView play = new ImageView("start_2.png");
    ImageView load = new ImageView("load.png");
    ImageView level = new ImageView("normal.png");
    ImageView creat = new ImageView("creat.png");
    ImageView loadMap = new ImageView("loadMap.png");

    public GameMenu(Stage stage, Scene field, GraphicsContext gc_animation, GraphicsContext gc_background) {
        this.stage = stage;
        this.field = field;
        this.gc_animation = gc_animation;
        this.gc_background = gc_background;

        anchorPane = new AnchorPane();
        menu = new Scene(anchorPane, Config.SCREEN_WITDH * Config.TILE_SIZE, Config.SCREEN_HEIGHT * Config.TILE_SIZE);


        play.setX(7.5 * Config.TILE_SIZE);
        play.setY(7.5 * Config.TILE_SIZE);

        load.setX(7.5 * Config.TILE_SIZE);
        load.setY(9.75 * Config.TILE_SIZE);

        level.setX(7.5 * Config.TILE_SIZE);
        level.setY(12 * Config.TILE_SIZE);

        creat.setX(4 * Config.TILE_SIZE);
        creat.setY(10 * Config.TILE_SIZE);

        loadMap.setX(18.5 * Config.TILE_SIZE);
        loadMap.setY(10 * Config.TILE_SIZE);
        //by setting this property to true, the audio will be played
        mediaPlayer.setAutoPlay(true);

        anchorPane.setStyle("-fx-background-image: url('background_1.png')");

        anchorPane.getChildren().addAll(play, load, level, creat, loadMap);
//        ImagePattern pattern = new ImagePattern(new Image("background_1.png"));
//        menu.setFill(pattern);

    }

    public Scene getScene() {
        return menu;
    }

    public void activeButton() {
        play.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            stage.setScene(field);
            mediaPlayer.stop();
            GameStart start = new GameStart(gc_animation, stage, menu);
            start.start();
        });

        load.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            loadGame();
            stage.setScene(field);
            mediaPlayer.stop();
            for (int i = 0; i < Data.enemiesList.size(); i++)
                System.out.println(Data.enemiesList.get(i).getId());
            GameStart start = new GameStart(gc_animation, stage, menu);
            start.start();
        });

        level.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (Data.level == 1) {
                Data.level = 2;
                level.setImage(new Image("hard.png"));
            } else {
                Data.level = 1;
                level.setImage(new Image("normal.png"));
            }
        });

        creat.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            stage.setScene(field);
            mediaPlayer.stop();
            CreatMap creatMap = new CreatMap(stage, menu);
            creatMap.activeButton();
            creatMap.start();
        });

        loadMap.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            loadMapMethod();
            Data.isLoadMap = true;
            //redraw
            new MapDrawer().draw(gc_background);
//            for (int i = 0; i < Config.mapTower.length; i++) {
//                for (int j = 0; j < Config.mapTower[0].length; j++)
//                    System.out.println(Config.mapTower[i][j] + " ");
//                System.out.println("\n");
//            }
        });
    }

    public void loadGame() {
        FileReader fileReader = null;
        try {
            String s = "";
            fileReader = new FileReader(new File("save_game.txt"));
            BufferedReader reader = new BufferedReader(fileReader);

            //String[] part = s.split("-");
            Data.gameOver = Boolean.parseBoolean(reader.readLine());
            Data.gamePause = Boolean.parseBoolean(reader.readLine());
            Data.gameSpeed = Integer.parseInt(reader.readLine());
            Data.playerHealth = Integer.parseInt(reader.readLine());
            Data.playerMoney = Integer.parseInt(reader.readLine());
            Data.playerScore = Integer.parseInt(reader.readLine());
            Data.level = Integer.parseInt(reader.readLine());
            Data.lightningIsReady = Boolean.parseBoolean(reader.readLine());

            int enemySize = Integer.parseInt(reader.readLine());
            int towerSize = Integer.parseInt(reader.readLine());
            int bulletSize = Integer.parseInt(reader.readLine());
            int bombSize = Integer.parseInt(reader.readLine());
            for (int i = 0; i < enemySize; i++) {
                String[] part = reader.readLine().split("-");

                if (part[0].equals("class Entity.EnemyPackage.NormalEnemy")) {
                    Data.enemiesList.add(new NormalEnemy(Integer.parseInt(part[1]), Integer.parseInt(part[2]), Integer.parseInt(part[3])));
                } else if (part[0].equals("class Entity.EnemyPackage.SmallerEnemy"))
                    Data.enemiesList.add(new SmallerEnemy(Integer.parseInt(part[1]), Integer.parseInt(part[2]), Integer.parseInt(part[3])));
                else if (part[0].equals("class Entity.EnemyPackage.TankerEnemy"))
                    Data.enemiesList.add(new TankerEnemy(Integer.parseInt(part[1]), Integer.parseInt(part[2]), Integer.parseInt(part[3])));
                else if (part[0].equals("class Entity.EnemyPackage.BossEnemy"))
                    Data.enemiesList.add(new BossEnemy(Integer.parseInt(part[1]), Integer.parseInt(part[2]), Integer.parseInt(part[3])));

            }

            for (int i = 0; i < towerSize; i++) {
                String[] part = reader.readLine().split("-");
                if (part[0].equals("class Entity.TowerPackage.NormalTower")) {
                    Data.towersList.add(new NormalTower(Integer.parseInt(part[1]), Integer.parseInt(part[2]), Boolean.parseBoolean(part[3])));
                    Data.playerMoney += Config.NORMAL_TOWER_PRICE;
                }
                if (part[0].equals("class Entity.TowerPackage.SniperTower")) {
                    Data.towersList.add(new SniperTower(Integer.parseInt(part[1]), Integer.parseInt(part[2]), Boolean.parseBoolean(part[3])));
                    Data.playerMoney += Config.SNIPER_TOWER_PRICE;
                }
                if (part[0].equals("class Entity.TowerPackage.MachineTower")) {
                    Data.towersList.add(new MachineTower(Integer.parseInt(part[1]), Integer.parseInt(part[2]), Boolean.parseBoolean(part[3])));
                    Data.playerMoney += Config.MACHINE_TOWER_PRICE;
                }
            }

            for (int i = 0; i < bulletSize; i++) {
                String[] part = reader.readLine().split("-");

                if (part[0].equals("class Entity.BulletPackage.NormalBullet")) {
                    for (Enemy enemy : Data.enemiesList) {
                        if (enemy.getId() == Integer.parseInt(part[1])) {
                            Data.bulletsList.add(new NormalBullet(enemy, Integer.parseInt(part[2]), Integer.parseInt(part[3])));
                            break;
                        }
                    }


                } else if (part[0].equals("class Entity.BulletPackage.SniperBullet")) {
                    for (Enemy enemy : Data.enemiesList) {
                        if (enemy.getId() == Integer.parseInt(part[1])) {
                            Data.bulletsList.add(new SniperBullet(enemy, Integer.parseInt(part[2]), Integer.parseInt(part[3])));
                            break;
                        }
                    }
                } else if (part[0].equals("class Entity.BulletPackage.MachineBullet")) {
                    for (Enemy enemy : Data.enemiesList) {
                        if (enemy.getId() == Integer.parseInt(part[1])) {
                            Data.bulletsList.add(new MachineBullet(enemy, Integer.parseInt(part[2]), Integer.parseInt(part[3])));
                            break;
                        }
                    }
                }

            }

            for (int i = 0; i < bombSize; i++) {
                String[] part = reader.readLine().split("-");
                Data.bombsList.add(new Bomb(Integer.parseInt(part[0]), Integer.parseInt(part[1])));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMapMethod() {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(new File("map.txt"));
            BufferedReader reader = new BufferedReader(fileReader);
            int m = Integer.parseInt(reader.readLine());
            int n = Integer.parseInt(reader.readLine());
            for (int i = 0; i < m; i++) {
                String line = reader.readLine();
                String[] strs = line.trim().split(" ");
                //System.out.println(line);
                for (int j = 0; j < n; j++) {
                    Config.mapTower[i][j] = Integer.parseInt(strs[j]);

                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
