package view;

import Model.CustomButton;
import Model.CustomSubscene;
import Model.Score;
import controller.ScoreCotroller;
import controller.SoundMusic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.sql.Timestamp;

public class View {

    private static final int HEIGHT = 800;
    private static final int WIDTH = 1000;
    private AnchorPane mainPane,playPane;
    private Scene mainScene;
    private Stage mainStage;

    private CustomSubscene scoreSubscene, settingSubscene;
    
    private CustomButton scoreBackBtn,playBackBtn, settingBackBtn;
    private TableView tableView;

    private SoundMusic click;
    private SoundMusic clickSettings;
    private SoundMusic clickScore;
    private SoundMusic clickPlay;
    private SoundMusic clickOK;

    private  SoundMusic musicBackground =new SoundMusic("src/ressources/GameMusic.wav");
    private CustomButton button1= new CustomButton("music"),button2= new CustomButton("effect");
    private static String playerName;

    public View(Stage stage) throws SQLException {
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT );
        mainScene.getStylesheets().add("/style/Style.css");
        stage.setScene(mainScene);
        createBackground();
        playBtn(60,400);
        scoreBtn(740,400);
        settingBtn(380,460);
        scoreView();
        settingView();
    }

    public View(Stage stage,Scene scene) {
        MyGame myGame=new MyGame();
        mainScene=myGame.getScene();
        stage.setScene(mainScene);
    }

    public View() throws SQLException {
        AnchorPane entryPane = new AnchorPane();
        Scene entryScene = new Scene(entryPane, WIDTH, HEIGHT );
        mainStage = new Stage();
        mainStage.setScene(entryScene);
        createEntry(entryPane);
        musicBackground.Run(button1.isActive);
    }

    public View(int score, Timestamp timestamp) throws SQLException {
        ScoreCotroller scoreCotroller=new ScoreCotroller();
        Score s=new Score(playerName,score,timestamp);
        scoreCotroller.save(s);
    }
    public void createMain() throws SQLException {
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT );
        mainScene.getStylesheets().add("/style/Style.css");
        mainStage.setScene(mainScene);
        createBackground();
        playBtn(60,400);
        scoreBtn(740,400);
        settingBtn(380,460);
        scoreView();
        settingView();
    }

    public void createMain2(Stage stage) throws SQLException {
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT );
        mainScene.getStylesheets().add("/style/Style.css");
        stage.setScene(mainScene);
        createBackground();
        playBtn(60,400);
        scoreBtn(740,400);
        settingBtn(380,460);
        scoreView();
        settingView();
    }

    public void createEntry(AnchorPane pane){
        Image backgroundImage = new Image("/ressources/backgroundEntry.png");
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        pane.setBackground(new Background(background));
        ImageView title=new ImageView("/ressources/Welcome.png");
        title.setLayoutX(200);
        title.setLayoutY(40);
        Label label = new Label("Please Write Your Name :");
        label.setLayoutX(pane.getWidth()/2 -200);
        label.setLayoutY(310);
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Lato", FontWeight.BOLD, 35));
        TextField name = new TextField();
        name.setLayoutX(pane.getWidth()/2 - 180);
        name.setLayoutY(390);
        name.setStyle("-fx-background-color: transparent;-fx-font-size: 30;-fx-border-radius: 5;-fx-border-width: 2;-fx-border-color: #242b54;-fx-text-inner-color: white;");
        name.setAlignment(Pos.CENTER);
        pane.getChildren().add(title);
        pane.getChildren().add(label);
        pane.getChildren().add(name);
        ImageView entryImg=new ImageView(new Image("/ressources/Enter.png"));
        CustomButton entryBtn=new CustomButton("Enter", 400, 600,entryImg,entryImg,pane);
        entryBtn.setStyle("-fx-border-radius: 5;-fx-border-width: 2;-fx-border-color: #242b54;");
        entryBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                click = new SoundMusic("src/ressources/ClickMusic.wav");
                click.Run(button2.isActive);
                playerName=name.getText();
                if (!playerName.isEmpty()){
                    try {
                        createMain();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Please Write Your Name !");
                    alert.show();
                }
            }
        });
    }

    private void playView(){

        MyGame myGame=new MyGame();
        mainScene=myGame.getScene();
        mainStage.setScene(mainScene);
    }

    private void scoreView() throws SQLException {
        Image image=new Image("/ressources/View.png",800, 700, false, true);
        scoreSubscene=new CustomSubscene(800,700,1001,50,image);
        ImageView backImg=new ImageView(new Image("/ressources/Cancel.png"));
        scoreBackBtn=new CustomButton("Back", 1801, 49,backImg,backImg,mainPane);
        scoreBackBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                scoreSubscene.moveSubScene();
                scoreBackBtn.moveButton();
            }
        });
        ImageView title=new ImageView("/ressources/ScoreLogo.png");
        title.setLayoutX(90);
        title.setLayoutY(20);
        scoreSubscene.getPane().getChildren().add(title);
        createTable();
        mainPane.getChildren().add(scoreSubscene);
    }

    private void settingView(){
        Image image=new Image("/ressources/View.png",800, 700, false, true);
        settingSubscene =new CustomSubscene(800,700,1001,50,image);
        ImageView backImg=new ImageView(new Image("/ressources/Cancel.png"));
        settingBackBtn =new CustomButton("Back", 1801, 49,backImg,backImg,mainPane);
        settingBackBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                settingSubscene.moveSubScene();
                settingBackBtn.moveButton();
            }
        });
        ImageView okImg=new ImageView(new Image("/ressources/OK.png",110,45,false,true));
        CustomButton okBtn=new CustomButton("OK", 350, 610,okImg,okImg, settingSubscene.getPane());
        okBtn.setStyle("-fx-background-color: #f8ec6e;-fx-background-radius: 1em;");
        okBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                clickOK = new SoundMusic("src/ressources/ClickMusic.wav");
                musicBackground.Run(button1.isActive);
                clickOK.Run(button2.isActive);
                settingSubscene.moveSubScene();
                settingBackBtn.moveButton();
            }
        });
        createSetting();
        mainPane.getChildren().add(settingSubscene);
    }

    public Stage getMainStage() {
        return mainStage;
    }
    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }
    public Scene getMainScene() {
        return mainScene;
    }
    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }
    public AnchorPane getMainPane() {
        return mainPane;
    }
    public String getPlayerName() {
        return playerName;
    }

    private void playBtn(double x, double y){
        ImageView img=new ImageView(new Image("/ressources/Play.png"));
        ImageView imgPressed=new ImageView(new Image("/ressources/Play2.png"));
        CustomButton btn=new CustomButton("Back", x, y,img,imgPressed,mainPane);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                clickPlay = new SoundMusic("src/ressources/ClickMusic.wav");
                clickPlay.Run(button2.isActive);
                playView();
            }
        });
    }

    private void settingBtn(double x, double y){
        ImageView img=new ImageView(new Image("/ressources/Setting.png"));
        ImageView imgPressed=new ImageView(new Image("/ressources/Settings2.png"));
        CustomButton btn=new CustomButton("Back", x, y,img,imgPressed,mainPane);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                clickSettings = new SoundMusic("src/ressources/ClickMusic.wav");
                clickSettings.Run(button2.isActive);
                settingSubscene.moveSubScene();
                settingBackBtn.moveButton();
            }
        });
    }

    private void scoreBtn(double x,double y){
        ImageView img=new ImageView(new Image("/ressources/Score.png"));
        ImageView imgPressed=new ImageView(new Image("/ressources/Score2.png"));
        CustomButton btn=new CustomButton("Back", x, y,img,imgPressed,mainPane);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ObservableList<Score> scoreObservableList = FXCollections.observableArrayList();
                ScoreCotroller scoreCotroller=new ScoreCotroller();
                tableView.setItems(scoreObservableList);
                clickScore = new SoundMusic("src/ressources/ClickMusic.wav");
                clickScore.Run(button2.isActive);
                try {
                    scoreObservableList.addAll(scoreCotroller.findAll());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                scoreSubscene.moveSubScene();
                scoreBackBtn.moveButton();
            }
        });
    }

    private void createBackground() {
        Image backgroundImage = new Image("/ressources/background.jpg", WIDTH, HEIGHT, false, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        ImageView title=new ImageView("/ressources/Logo.png");
        title.setLayoutX(50);
        title.setLayoutY(100);
        mainPane.getChildren().add(title);
        mainPane.setBackground(new Background(background));
    }

    private void createTable() throws SQLException {

        tableView = new TableView();

        TableColumn<Score, String> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        column1.setSortable(false);
        TableColumn<Score, Integer> column2 = new TableColumn<>("Score");
        column2.setCellValueFactory(new PropertyValueFactory<>("score"));
        column2.setSortable(false);
        TableColumn<Score, String> column3 = new TableColumn<>("Date");
        column3.setCellValueFactory(new PropertyValueFactory<>("date"));
        column3.setSortable(false);


        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setPrefSize(650,500);


        VBox vBox=new VBox(tableView);
        vBox.setLayoutX(75);
        vBox.setLayoutY(170);
        scoreSubscene.getPane().getChildren().add(vBox);
    }

    private void createSetting(){

        Label music = new Label("Music");
        music.setTextFill(Color.WHITE);
        Label effect = new Label("Sound Effect");
        effect.setTextFill(Color.WHITE);
        Label level = new Label("Levels");
        level.setTextFill(Color.WHITE);

        ComboBox comboBox = new ComboBox();
        comboBox.getItems().add("Easy");
        comboBox.getItems().add("Intermediate");
        comboBox.getItems().add("Hard");

        GridPane gridPane = new GridPane();

        gridPane.add(button1, 1, 0, 1, 1);
        gridPane.add(button2, 1, 1, 1, 1);
        gridPane.add(music, 0, 0, 1, 1);
        gridPane.add(effect, 0, 1, 1, 1);
        gridPane.add(level, 0, 2, 1, 1);
        gridPane.add(comboBox, 1, 2, 1, 1);
        gridPane.setPrefSize(700,400);
        gridPane.setLayoutX(100);
        gridPane.setLayoutY(170);
        gridPane.setHgap(110);
        gridPane.setVgap(10);
        GridPane.setHalignment(button1, HPos.CENTER);
        GridPane.setHalignment(button2, HPos.CENTER);

        ImageView title=new ImageView("/ressources/SettingsLogo.png");
        title.setLayoutX(200);
        title.setLayoutY(20);
        settingSubscene.getPane().getChildren().add(title);

        settingSubscene.getPane().getChildren().add(gridPane);

    }
}
