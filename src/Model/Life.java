package Model;
import javafx.scene.image.ImageView;
public class Life {

    static public  ImageView getLife(double x,double y , int score){
        ImageView imageView;
        if(score>40 && score<=50){
            imageView=new ImageView("ressources/5.png");
            imageView.setFitWidth(150);
            imageView.setFitHeight(60);
            imageView.setX(x);
            imageView.setY(y);

        }else if(score>30 && score<=40){
            imageView=new ImageView("ressources/4.png");
            imageView.setFitWidth(150);
            imageView.setFitHeight(60);
            imageView.setX(x);
            imageView.setY(y);

        }else if(score>20 && score<=30){
            imageView=new ImageView("ressources/3.png");
            imageView.setFitWidth(150);
            imageView.setFitHeight(60);
            imageView.setX(x);
            imageView.setY(y);
        }else if(score>10 && score<=20){
            imageView=new ImageView("ressources/2.png");
            imageView.setFitWidth(150);
            imageView.setFitHeight(60);
            imageView.setX(x);
            imageView.setY(y);
        }else{
            imageView=new ImageView("ressources/1.png");
            imageView.setFitWidth(150);
            imageView.setFitHeight(60);
            imageView.setX(x);
            imageView.setY(y);
        }
            return imageView;

    }


}
