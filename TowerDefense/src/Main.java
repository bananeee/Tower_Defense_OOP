import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tower Defense");


        GameField field = new GameField();
        field.activeButton();

        GameMenu menu = new GameMenu(primaryStage, field.getScene(), field.getGc_animation(), field.getGc_background());
        menu.activeButton();
        primaryStage.setScene(menu.getScene());

        primaryStage.show();
    }

}