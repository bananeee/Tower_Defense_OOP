import Database.Config;
import Database.Map;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class CreatMap {
    private Stage stage;
    private AnchorPane anchorPane;
    private Scene menu;
    private Scene creatMap;
    private Canvas canvas;
    private GraphicsContext gc;

    private Node start = new Node(0, 3, null);
    private Button button = new Button();
    AnimationTimer animationTimer;

    Timeline gameLoop = new Timeline();
    Task<Void> sleeper = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            return null;
        }
    };

    public CreatMap(Stage stage, Scene menu) {
        this.stage = stage;
        this.menu = menu;
        anchorPane = new AnchorPane();
        creatMap = new Scene(anchorPane);
        canvas = new Canvas(Config.SCREEN_WITDH * Config.TILE_SIZE, Config.SCREEN_HEIGHT * Config.TILE_SIZE);
        gc = canvas.getGraphicsContext2D();
        button.setText("save");
        button.setLayoutX(48 * 5);
        button.setLayoutY(48 * 13);
    }

    public void activeButton() {
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                search(start, 24, 0);
                save();
                new Thread(sleeper).start();
            }
        });

        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                animationTimer.stop();
                stage.setScene(menu);
            }
        });
    }

    public void start() {
        canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if ((int) (mouseEvent.getY() / Config.TILE_SIZE) <= 11 && (int) (mouseEvent.getX() / Config.TILE_SIZE) <= 24)
                    if (mouseEvent.getButton() == MouseButton.PRIMARY)
                        Map.oriMap[(int) (mouseEvent.getY() / Config.TILE_SIZE)][(int) (mouseEvent.getX() / Config.TILE_SIZE)] = 5;
                    else
                        Map.oriMap[(int) (mouseEvent.getY() / Config.TILE_SIZE)][(int) (mouseEvent.getX() / Config.TILE_SIZE)] = 0;
                mouseEvent.consume();
            }
        });

        anchorPane.getChildren().addAll(canvas, button);
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for (int i = 0; i < Map.oriMap.length; i++)
                    for (int j = 0; j < Map.oriMap[0].length; j++)
                        if (Map.oriMap[i][j] == 5) {
                            gc.drawImage(new Image("road9.png"), j * 48, i * 48, 48, 48);
                        } else if (Map.oriMap[i][j] == 0) {
                            gc.drawImage(new Image("grass4.png"), j * 48, i * 48, 48, 48);
                        } else {
                            gc.setFill(Color.RED);
                            gc.fillRect(j * 48, i * 48, 48, 48);
                        }
                gc.setFill(Color.RED);
                gc.fillRect(0, 3 * 48, 48, 48);
                gc.fillRect(24 * 48, 0, 48, 48);
            }
        };
        animationTimer.start();
        stage.setScene(creatMap);
        stage.show();
    }

    public void search(Node start, int desX, int desY) {
        Queue<Node> search_queue = new ArrayDeque<>();
        start.findNext();
        search_queue.addAll(start.getNodesNext());
        ArrayList<Node> searched = new ArrayList<>();
        Node run = start;
        while (run.getX() != desX || run.getY() != desY) {
            //System.out.println("ok");
            Node temp = search_queue.poll();
            boolean check = false;
            for (Node node : searched) {
                if (node.getX() == temp.getX() && node.getY() == temp.getY())
                    check = true;
            }
            if (check == false) {
                if (temp.getX() == desX && temp.getY() == desY) {
                    run = temp;
                } else {
                    temp.findNext();
                    search_queue.addAll(temp.getNodesNext());
                    searched.add(temp);
                }
            }
        }

        Node temp = run;
        while (temp != null) {
            Map.oriMap[temp.getY()][temp.getX()] = temp.getValue();
            if (temp.getPrevious() != null)
                if (temp.getPrevious().getX() == temp.getX() && temp.getPrevious().getY() < temp.getY())
                    temp.getPrevious().setValue(2);
                else if (temp.getPrevious().getX() == temp.getX() && temp.getPrevious().getY() > temp.getY())
                    temp.getPrevious().setValue(8);
                else if (temp.getPrevious().getX() < temp.getX() && temp.getPrevious().getY() == temp.getY())
                    temp.getPrevious().setValue(6);
                else if (temp.getPrevious().getX() > temp.getX() && temp.getPrevious().getY() == temp.getY())
                    temp.getPrevious().setValue(4);
            temp = temp.getPrevious();
        }
    }

    public void save() {

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(new File("map.txt"));
            BufferedWriter writer = new BufferedWriter(fileWriter);
            fileWriter.write(Map.oriMap.length + "\n" + Map.oriMap[0].length + "\n");
            for (int i = 0; i < Map.oriMap.length; i++) {
                for (int j = 0; j < Map.oriMap[0].length; j++)
                    fileWriter.write(Map.oriMap[i][j] + " ");
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Scene getScene() {
        return creatMap;
    }
}
