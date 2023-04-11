import controller.SoundMusic;
import javafx.application.Application;
import javafx.stage.Stage;
import view.View;

import java.sql.SQLException;

public class Main extends Application {

    private SoundMusic musicBackground =new SoundMusic("src/ressources/GameMusic.wav");

    @Override
    public void start(Stage stage) throws Exception {
        View view=new View();
        Stage newStage = view.getMainStage();
        newStage.setResizable(false);
        newStage.show();
        //musicBackground.getClip().start();
    }

    public static void main(String[] args) throws SQLException {

        launch(args);
    }

}
