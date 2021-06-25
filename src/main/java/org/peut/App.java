package org.peut;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    public static final int BOARDSIDE=7;

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Zee");
        primaryStage.setResizable(false);;

        Group bord = new Group();

        for (int i = 0; i < 49 ; i++) {
            Hokje h = new Hokje( Integer.toString(i) );
            bord.getChildren().add( h );
            System.out.println( "Hokje "+ i + " style:" + h.getStyle() );
        }
        primaryStage.setScene(new Scene(bord, BOARDSIDE*Hokje.SIDE+1, BOARDSIDE*Hokje.SIDE+1));

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}