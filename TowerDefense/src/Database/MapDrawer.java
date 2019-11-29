package Database;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;


public class MapDrawer {
    public void draw(GraphicsContext gc) {
        int map[][];
        if (Data.isLoadMap == false) {
            gc.drawImage(new Image("background.png"), 0, 0);
            map = Map.map;
        } else
            map = Config.mapTower;
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[0].length; j++)
                if (map[i][j] == 2 || map[i][j] == 4 || map[i][j] == 6 || map[i][j] == 8 || map[i][j] == 5) {
                    Image image = new Image("road9.png");
                    gc.drawImage(image, j * Config.TILE_SIZE, i * Config.TILE_SIZE, Config.TILE_SIZE, Config.TILE_SIZE);
                } else if (map[i][j] == 9)
                    gc.drawImage(new Image("bridge.png"), j * Config.TILE_SIZE, i * Config.TILE_SIZE, Config.TILE_SIZE, Config.TILE_SIZE);
                else if (Data.isLoadMap)
                    gc.drawImage(new Image("grass4.png"), j * Config.TILE_SIZE, i * Config.TILE_SIZE, Config.TILE_SIZE, Config.TILE_SIZE);

        gc.drawImage(new Image("wood.png"), 0, (Config.SCREEN_HEIGHT - 3) * Config.TILE_SIZE, Config.SCREEN_WITDH * Config.TILE_SIZE, Config.TILE_SIZE * 3);
        gc.drawImage(new Image("health.png"), 16.5 * Config.TILE_SIZE, 12.25 * Config.TILE_SIZE, Config.TILE_SIZE, Config.TILE_SIZE);
        gc.drawImage(new Image("coin.png"), 16.5 * Config.TILE_SIZE, 13.5 * Config.TILE_SIZE, Config.TILE_SIZE, Config.TILE_SIZE);

        gc.setFont(new Font("MV Boli", 20));
        gc.setFill(Color.YELLOW);
        gc.fillText("" + Config.NORMAL_TOWER_PRICE, Config.NORMAL_TOWER_COORDINATE[0], Config.NORMAL_TOWER_COORDINATE[1]);
        gc.fillText("" + Config.SNIPER_TOWER_PRICE, Config.SNIPER_TOWER_COORDINATE[0], Config.SNIPER_TOWER_COORDINATE[1]);
        gc.fillText("" + Config.MACHINE_TOWER_PRICE, Config.MACHINE_TOWER_COORDINATE[0], Config.MACHINE_TOWER_COORDINATE[1]);
    }


}