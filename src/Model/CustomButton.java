package Model;

import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class CustomButton extends Button {

    private boolean isHidden;
    public boolean isActive;
    public CustomButton(String text) {
        ImageView img=new ImageView(new Image("/ressources/On.png"));
        ImageView imgPressed=new ImageView(new Image("/ressources/Off.png"));
        setGraphic(img);
        buttonEffect(img,imgPressed);
        setBackground(null);
        isActive=true;
    }

    public CustomButton(String text, double x, double y, ImageView img, ImageView imgPressed, AnchorPane pane) {

        setLayoutX(x);
        setLayoutY(y);
        //button.setStyle("-fx-background-color: transparent;-fx-text-fill: green;");
        //button.setFont(Font.font(50));
        setGraphic(img);
        setBackground(null);
        isHidden=true;
        buttonAction(y,img,imgPressed);
        pane.getChildren().add(this);
    }

    private void buttonAction(double y,ImageView img,ImageView imgPressed){
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)) {
                    setGraphic(imgPressed);
                    setLayoutY(y-4);
                }
            }
        });
        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)) {
                    setGraphic(img);
                    setLayoutY(y);
                }
            }
        });
    }

    public void moveButton() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);

        if (isHidden) {
            transition.setToX(-901);
            isHidden = false;

        } else {
            transition.setToX(0);
            isHidden = true ;
        }
        transition.play();
    }

    private void buttonEffect(ImageView img, ImageView imgPressed){
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (isActive) {
                    setGraphic(imgPressed);
                    isActive=false;
                } else {
                    setGraphic(img);
                    isActive=true;
                }
            }
        });
    }
}
