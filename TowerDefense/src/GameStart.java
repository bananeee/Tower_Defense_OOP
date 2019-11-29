import Database.Config;
import Database.Data;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;


public class GameStart {
    private GraphicsContext gc_animation;
    private Stage stage;
    private Scene menu;
    Media media = new Media(new File("happytune.mp3").toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);

    public GameStart(GraphicsContext gc_animation, Stage stage, Scene menu) {
        this.gc_animation = gc_animation;
        this.stage = stage;
        this.menu = menu;
        mediaPlayer.play();
        mediaPlayer.setCycleCount(Timeline.INDEFINITE);
    }

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

    KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.034),                // 30 FPS
            new EventHandler<ActionEvent>() {
                int timer = 0;

                public void handle(ActionEvent ae) {
                    gc_animation.clearRect(0, 0, Config.TILE_SIZE * Config.SCREEN_WITDH, Config.TILE_SIZE * Config.SCREEN_HEIGHT);

                    if (Data.lightningIsReady == false)
                        if (timer == 100) {
                            Data.lightningIsReady = true;
                            timer = 0;
                        } else
                            timer += Data.gameSpeed;

                    new GameRun(gc_animation).run();

                    if (Data.playerHealth <= 0) {
                        gameLoop.stop();
                        mediaPlayer.stop();
                        gc_animation.drawImage(new Image("game_over_2.png"), Config.SCREEN_WITDH / 2 * Config.TILE_SIZE - 250, Config.SCREEN_HEIGHT / 2 * Config.TILE_SIZE - 200, 500, 400);
                        System.out.println("Game Over");
                        new Thread(sleeper).start();
                    }
                }
            });

    public void start() {
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                stage.setScene(menu);
                new GameRun(gc_animation).clearData();
            }
        });
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.getKeyFrames().add(keyFrame);
        gameLoop.play();
    }


}
